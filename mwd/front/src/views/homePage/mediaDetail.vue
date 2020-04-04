<template>
    <div>
        <div style="width: 1000px;margin: 0 auto;padding-top: 20px">
            <div style="width: 930px;float: left">
                <div style="height: 80px;width: 250px">
                    <span style="">{{mediaWork.mediaName}}</span><br><br>
                    <span v-if="mediaWork.mediaRemark.length < 35" style="font-size: x-small">{{mediaWork.mediaRemark}}</span>
                    <span v-else style="font-size: x-small" >{{mediaWork.mediaRemark.substring(0,35)}}...</span>
                </div>
                <div>
                    <video-player class="video-player vjs-custom-skin"
                                  style="width: 930px;height: 300px"
                                  ref="videoPlayer"
                                  :playsinline="true"
                                  :options="playerOptions">
                    </video-player>
                </div>
            </div>
            <div style="float: right;width: 40px;height:300px;padding-left: 30px;padding-top: 415px">
                <img style="width: 40px;height:40px;padding-top: 20px" src="../static/img/discuss.svg" @click="addBookmark">
                <img v-if="isInBookMark" style="width: 40px;height:40px;padding-top: 20px" src="../static/img/bookmark.svg" @click="addBookmark">
                <img v-else style="width: 40px;height:40px;padding-top: 20px" src="../static/img/notBookmark.svg" @click="addBookmark">
                <img v-if="isInRecommend" style="width: 40px;height:40px;padding-top: 20px" src="../static/img/recommend.svg" @click="addBookmark">
                <img v-else style="width: 40px;height:40px;padding-top: 20px" src="../static/img/notRecommend.svg" @click="addBookmark">
            </div>
        </div>

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
                        src: 'https://video.pearvideo.com/mp4/short/20200209/cont-1650197-14888002-hd.mp4' // url地址
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

            }
        },
        created() {
            this.mediaId = this.$route.query.mediaId;
            this.queryMediaWork()
            this.judgeBookmark()
        },
        methods:{
            queryMediaWork(){
                this.$axios.post('/mediaWork/queryMediaWorkById',{
                    id:this.mediaId
                }).then(resp =>{
                    this.mediaWork = resp.data
                    this.playerOptions.poster = this.mediaWork.mediaCover
                    //this.playerOptions.sources[0].src = this.mediaWork.mediaUrl
                })
            },
            judgeBookmark(){
                this.$axios.post('/bookmark/isInBookmark',{
                    mediaId:this.mediaId
                }).then(resp =>{
                    this.isInBookMark = resp.data
                    window.console.info(this.isInBookMark)
                })
            },

            addBookmark(){
                this.$axios.post('/bookmark/addBookmark',{
                    mediaId:this.mediaId
                }).then(resp =>{
                    if (resp.code == 200){
                        this.judgeBookmark()
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>
