<template>
    <div>
        <div style="width: 1000px;height:500px;margin: 0 auto;padding-top: 20px">
            <div style="width: 930px;float: left">
                <div style="height: 80px;width: 930px;">
                    <div v-if="mediaWork.mediaRemark" style="height: 80px;width: 250px;float: left">
                        <span style="">{{mediaWork.mediaName}}</span><br><br>
                        <span v-if="mediaWork.mediaRemark.length < 35" style="font-size: x-small">{{mediaWork.mediaRemark}}</span>
                        <span v-else style="font-size: x-small" >{{mediaWork.mediaRemark.substring(0,35)}}...</span>
                    </div>
                </div>
                <div>
                    <video-player class="video-player vjs-custom-skin"
                                  style="width: 930px;height: 300px"
                                  ref="videoPlayer"
                                  :playsinline="true"
                                  :options="playerOptions"
                                   @ended="onPlayerEnded()">
                    </video-player>
                </div>
            </div>
            <div style="float: right;width: 40px;height:200px;padding-left: 30px;padding-top: 415px">
                <img style="width: 40px;height:40px;padding-top: 20px" src="../static/img/discuss.svg" @click="focusclick">
                <img v-if="isInBookMark" style="width: 40px;height:40px;padding-top: 20px" src="../static/img/bookmark.svg" @click="removeBookmark">
                <img v-else style="width: 40px;height:40px;padding-top: 20px" src="../static/img/notBookmark.svg" @click="addBookmark">
                <img v-if="isInRecommend" style="width: 40px;height:40px;padding-top: 20px" src="../static/img/recommend.svg" @click="removeRecommend">
                <img v-else style="width: 40px;height:40px;padding-top: 20px" src="../static/img/notRecommend.svg" @click="addRecommend">
            </div>
        </div>
        <div style="padding-top: 140px;margin: 0 auto;width: 1000px" >
            <div>共{{this.total}} 评论 </div>
            <div style="width: 1000px;padding-top: 20px">
                <el-input id="contentInput" style="float: left;width: 885px" placeholder="发表你的评论"  v-model="content" clearable @blur="focusState = false" v-focus="focusState"></el-input>
                <el-button style="float: left;margin-left: 15px;" type="info" @click="sendDiscuss">发表评论</el-button>
            </div>
        </div>
        <div style="padding-top: 50px;margin: 0 auto;width: 1000px">
            <div style="padding-top: 10px">
                <el-row v-for="index in discussList" :key="index.id" style="height: 100px;width:1000px;">
                    <el-col style="width: 70px">
                        <el-avatar style="width: 65px;height: 60px" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                    </el-col>
                    <el-col style="width: 920px ;padding-left: 10px;margin-top: 10px">
                        <span>{{index.discussUser}}</span><br><br>
                        <span style="font-size: x-small">{{index.content}}</span>
                        <el-divider content-position="left" style="margin-bottom: 0px"></el-divider>
                    </el-col>
                </el-row>
                <el-row style="height: 100px;width:1000px;padding-top: 30px">
                    <el-col>
                        <el-pagination
                                @size-change="handleSizeChange"
                                @current-change="handleCurrentChange"
                                :current-page="page"
                                :page-sizes="[5, 10, 15, 20]"
                                :page-size="pageSize"
                                layout="total, sizes, prev, pager, next, jumper"
                                :total="total">
                        </el-pagination>
                    </el-col>
                </el-row>
                <br>
            </div>
        </div>
        <el-dialog
                title="打个分吧"
                :visible.sync="addScoreDialog"
                width="20%"
                center>
            <div align="center">
                <el-rate
                        v-model="score"
                        show-score allow-half>
                </el-rate>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addScoreDialog = false" size="mini">不用了,下次吧!</el-button>
                <el-button type="primary" @click="submitForm" size="mini" >确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import { videoPlayer } from 'vue-video-player'
    import 'video.js/dist/video-js.css'
    export default {
        name: "mediaDetail",
        components: {
            videoPlayer
        },
        data(){
            return {
                playerOptions: {
                    playbackRates: [0, 5, 1.0, 1.5, 2.0],
                    // 如果为true,浏览器准备好时开始回放。
                    autoplay: false,
                    // 默认情况下将会消除任何音频。
                    muted: false,
                    // 是否视频一结束就重新开始。
                    loop: false,
                    // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
                    preload: 'auto',
                    language: 'zh-CN',
                    // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
                    aspectRatio: '16:9',
                    // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
                    fluid: true,
                    sources: [{
                        // type: "video/mp4", // 类型
                        src: '' // url地址
                    }],
                    // 封面地址
                    poster: '',
                    // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
                    notSupportedMessage: '此视频暂无法播放，请稍后再试',
                    controlBar: {
                        timeDivider: true, // 当前时间和持续时间的分隔符
                        durationDisplay: true, // 显示持续时间
                        remainingTimeDisplay: false, // 是否显示剩余时间功能
                        fullscreenToggle: true // 是否显示全屏按钮
                    }
                },
                mediaId:'',
                mediaWork:'',
                isInBookMark:false,
                isInRecommend:false,
                content:'',
                discussList:[],
                page:1,
                pageSize:5,
                total:0,
                focusState:false,
                addScoreDialog:false,
                score:'',
            }
        },
        created() {
            this.mediaId = this.$route.query.mediaId;
            this.addMediaPopular();
            this.queryMediaWork();
            this.judgeBookmark();
            this.judgeRecommend();
            this.queryDiscuss();
        },
        directives: {
            focus: {
                update: function (el, {value}) {
                    if (value) {
                        el.focus()
                    }
                }
            }
        },
        methods:{
            handleSizeChange(val) {
                this.pageSize = val
                this.queryDiscuss()
            },
            handleCurrentChange(val) {
                this.page = val
                this.queryDiscuss()
            },
            queryMediaWork(){
                this.$axios.post('/mediaWork/queryMediaWorkById',{
                    id:this.mediaId
                }).then(resp =>{
                    this.mediaWork = resp.data
                    this.playerOptions.poster = this.mediaWork.mediaCover
                    this.playerOptions.sources[0].src = this.mediaWork.mediaUrl
                })
            },
            judgeBookmark(){
                this.$axios.post('/bookmark/isInBookmark',{
                    mediaId:this.mediaId
                }).then(resp =>{
                    this.isInBookMark = resp.data
                })
            },
            judgeRecommend(){
                this.$axios.post('/recommend/isInRecommend',{
                    mediaId:this.mediaId
                }).then(resp =>{
                    this.isInRecommend = resp.data
                })
            },
            addBookmark(){
                this.$axios.post('/bookmark/addBookmark',{
                    mediaId:this.mediaId
                }).then(resp =>{
                    if (resp.code == 200){
                        this.judgeBookmark()
                        this.$message.success("收藏成功")
                    }
                })
            },
            removeBookmark(){
                this.$axios.post('/bookmark/removeBookmark',{
                    mediaId:this.mediaId
                }).then(resp =>{
                    if (resp.code == 200){
                        this.judgeBookmark()
                        this.$message.success("已从收藏夹移除")
                    }
                })
            },
            addRecommend(){
                this.$axios.post('/recommend/addRecommend',{
                    mediaId:this.mediaId
                }).then(resp =>{
                    if (resp.code == 200){
                        this.judgeRecommend()
                        this.$message.success("你称赞了该作品")
                    }
                })
            },
            removeRecommend(){
                this.$axios.post('/recommend/removeRecommend',{
                    mediaId:this.mediaId
                }).then(resp =>{
                    if (resp.code == 200){
                        this.judgeRecommend()
                    }
                })
            },
            sendDiscuss(){
                this.$axios.post('/discuss/sendDiscuss',{
                    mediaId:this.mediaId,
                    content:this.content
                }).then(resp =>{
                    if (resp.code == 200){
                        this.$message.success("发表成功");
                        this.content = '';
                        this.queryDiscuss();
                    }
                })
            },
            queryDiscuss(){
                this.$axios.post('/discuss/selectDiscussList',{
                    mediaId:this.mediaId,
                    page:this.page,
                    pageSize:this.pageSize,
                    status:1,
                }).then(resp =>{
                    if (resp.code == 200){
                        this.discussList = resp.data.items;
                        this.total = resp.data.total;
                    }
                })
            },
            focusclick () {
                let x = document.getElementById('contentInput');
                x.focus()
                window.scrollTo(0,300)
            },
            addMediaPopular(){
                this.$axios.post('/mediaWork/addMediaPopular',{
                    id:this.mediaId,
                })
            },
            onPlayerEnded(){
                this.$axios.post('/scoreRecord/isInScoreRecord',{
                    mediaId:this.mediaId,
                }).then(resp=>{
                    if (resp.data == false){
                        this.addScoreDialog = true
                    }
                })
            },
            submitForm(){
                this.$axios.post('/scoreRecord/addScoreRecord',{
                    mediaId:this.mediaId,
                    score:this.score
                }).then(resp=>{
                    if (resp.code == 200){
                        this.$message.success("谢谢你的评分！")
                        this.addScoreDialog = false
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>
