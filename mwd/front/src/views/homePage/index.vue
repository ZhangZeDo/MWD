<template>
    <div >
        <div style="width: 1000px; height: 300px;padding-top: 20px; margin: 0 auto">
            <el-carousel trigger="click">
                <el-carousel-item v-for="item in underRankMedias.slice(0,7)" :key="item.id">
                    <img style="width: 1000px; height: 300px;" :src="item.mediaCover" @click="showDetail(item.id)"/>
                </el-carousel-item>
            </el-carousel>
        </div>
        <div style="width: 1022px; height: 200px;padding-top: 20px; margin: 0 auto">
            <el-row>
                <el-col :span="8" v-for="item in underRankMedias.slice(7,10)" :key="item.id" >
                    <img style="height: 200px;width: 326px; padding-left: 11px" :src="item.mediaCover" @click="showDetail(item.id)"/>
                </el-col>
            </el-row>
        </div>
        <div style="width: 1022px;padding-top: 30px; margin: 0 auto">
            <div>
                <el-tabs v-model="order" style="width: 200px;margin: auto;" :stretch="true" @tab-click="changeOrder">
                    <el-tab-pane  label="首页推荐" name="popular_num"></el-tab-pane>
                    <el-tab-pane label="最新发布" name="create_datetime"></el-tab-pane>
                </el-tabs>
            </div>
            <div style="padding-top: 5px;padding-left: 10px">
                <el-row>
                    <el-col :span="8" v-for="index in mediaWorks" :key="index.id" >
                        <el-card style="padding-top:5px; margin-top:15px;width: 326px;height: 350px;float: left">
                            <div style="float: left">
                                <img style="width: 295px;height: 200px" :src="index.mediaCover" class="image" @click="showDetail(index.id)"/>
                            </div>
                            <div style="float: left;width: 288px;height:95px;padding-left: 5px;">
                                <span style="font-weight: bolder">{{index.mediaName}}</span><br>
                                <span style="font-size: xx-small">{{parseString(index.mediaRemark) }}</span><br><br>
                            </div>
                            <div style="float: left;width: 288px;padding-left: 5px;">
                                <div style="width: 50px;height: 20px;float: left">
                                    <img style="width: 30px;height: 20px;float: left" src="../static/img/homePageEye.svg"/>
                                    <span style="float: left;margin-top: 2px">{{index.popularNum}}</span>
                                </div>
                                <div style="width: 50px;height: 20px;float: left">
                                    <img style="width: 30px;height: 20px;float: left" src="../static/img/hoemPageMsg.svg"/>
                                    <span style="float: left;margin-top: 2px;padding-left: 3px">{{index.discussNum}}</span>
                                </div>
                                <div style="width: 50px;height: 20px;float: left" >
                                    <img style="width: 30px;height: 20px;float: left" src="../static/img/homePageGood.svg"/>
                                    <span style="float: left;margin-top: 2px;padding-left: 3px">{{index.recommendNum}}</span>
                                </div>
                                <div style="width: 130px;height: 20px;float: right" >
                                    <span style="float: right;margin-top: 1px;padding-left: 3px">up:{{parseUp(index.uploadUser)}}</span>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
                <div align="center" style="margin-bottom: 10px;padding-top: 20px">
                    <div class="block" style="padding: 0 32px">
                        <el-pagination
                                @size-change="handleSizeChange"
                                @current-change="handleCurrentChange"
                                :current-page="page"
                                :page-sizes="[6, 9, 15, 21]"
                                :page-size="pageSize"
                                layout="total, sizes, prev, pager, next, jumper"
                                :total="total">
                        </el-pagination>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "home",
        data(){
            return{
                underRankMedias:[],
                mediaWorks:[],
                order:'popular_num',
                pageSize:6,
                page:1,
                total:0,
            }
        },
        created() {
            this.queryUnderRankList()
            this.queryMediaWorksList()
        },
        methods:{
           parseString(val){
                if (val.length>60){
                    val = val.substring(0,60)+"...";
                }
                return val
           },
            parseUp(val){
                if (val.length>5){
                    val = val.substring(0,5)+"...";
                }
                return val
            },
           queryUnderRankList(){
               this.$axios.post('/underRank/queryAllUnderMedia',{
                    status:1
               }).then(resp =>{
                   this.underRankMedias = resp.data
               })
           },

           queryMediaWorksList(){
               this.$axios.post('/mediaWork/mediaWorkList',{
                   status:1,
                   page:this.page,
                   pageSize:this.pageSize,
                   order:this.order
               }).then(resp=>{
                   if (resp.code == 200) {
                       this.mediaWorks = resp.data.items;
                       this.total = resp.data.total;
                   }else{
                       this.$message.error(resp.data.message);
                   }
               });
           },

            handleSizeChange(val) {
                this.pageSize = val
                this.queryMediaWorksList()
            },
            handleCurrentChange(val) {
                this.page = val
                this.queryMediaWorksList()
            },
            changeOrder(val){
                this.order = val.name
                this.queryMediaWorksList()
            },
            showDetail(val){
               this.$router.push({path:'/mediaDetail',query:{"mediaId":val}})
            }
        }
    }
</script>

<style lang="css" scoped>
    .el-carousel__item h3 {
        color: #475669;
        font-size: 18px;
        opacity: 0.75;
        line-height: 300px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }
</style>
