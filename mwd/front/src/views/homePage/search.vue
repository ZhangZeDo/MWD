<template>
    <div >
        <div style="width: 1400px; height: 100px;padding-top:40px; margin: 0 auto">
            <div align="center">
                <el-input size="big" placeholder="请输入作品标题或备注" v-model="searchInput" class="input-with-select" style="width: 800px">
                    <el-select v-model="mediaType" slot="prepend" placeholder="请选择" style="width: 120px">
                        <el-option value="" label="所有类型"></el-option>
                        <el-option
                                v-for="item in mediaTypeList"
                                :key="item.id"
                                :label="item.typeName"
                                :value="item.id">
                        </el-option>
                    </el-select>
                    <el-button slot="append" icon="el-icon-search" @click="queryMediaWorksList"></el-button>
                </el-input>
            </div>
        </div>
        <div style="width: 1422px;padding-top: 0px; margin: 0 auto">
            <div style="padding-top: 5px;padding-left: 10px">
                <el-row>
                    <el-col :span="8" v-for="index in mediaWorks" :key="index.id" >
                        <el-card style="padding-top:5px; margin-top:15px;width: 426px;height: 350px;float: left">
                            <div style="float: left">
                                <img style="width: 395px;height: 200px" :src="index.mediaCover" class="image" @click="showDetail(index.id)"/>
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
                                <div style="width: 100px;height: 20px;float: right" >
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
        name: "search",
        data(){
            return{
                mediaWorks:[],
                pageSize:6,
                page:1,
                total:0,
                searchInput:'',
                mediaType:'',
                mediaTypeList:[],
            }
        },
        created() {
            this.getAllMediaType()
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
            getAllMediaType(){
                this.$axios.post('/mediaType/getAllMediaType',{
                    status:'1'
                }).then(resp=>{
                    if (resp.code == 200) {
                        this.mediaTypeList = resp.data
                    }
                });
            },

            queryMediaWorksList(){
                this.$axios.post('/mediaWork/mediaWorkList',{
                    status:1,
                    page:this.page,
                    pageSize:this.pageSize,
                    order:this.order,
                    mediaType: this.mediaType,
                    searchInput:this.searchInput
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

    .el-select .el-input {
        width: 130px;
    }
    .input-with-select .el-input-group__prepend {
        background-color: #fff;
    }
</style>
