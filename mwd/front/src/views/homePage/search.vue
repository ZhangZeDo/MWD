<template>
    <div >
        <div style="width: 1400px; height: 100px;padding-top:40px; margin: 0 auto">
            <div align="center">
                <el-input size="big" placeholder="请输入作品标题或备注" v-model="searchInput" class="input-with-select" style="width: 800px">
                    <el-select v-model="mediaType" slot="prepend" placeholder="请选择" style="width: 100px">
                        <el-option label="餐厅名" value="1"></el-option>
                        <el-option label="订单号" value="2"></el-option>
                        <el-option label="用户电话" value="3"></el-option>
                    </el-select>
                    <el-button slot="append" icon="el-icon-search"></el-button>
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
                            <div style="float: left;width: 250px;padding-left: 5px">
                                <span >{{index.mediaName}}</span><br>
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
                underRankMedias:[],
                mediaWorks:[],
                order:'popular_num',
                pageSize:6,
                page:1,
                total:0,
                searchInput:'',
                mediaType:'',
            }
        },
        created() {
            this.queryUnderRankList()
            this.queryMediaWorksList()
        },
        methods:{
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

    .el-select .el-input {
        width: 130px;
    }
    .input-with-select .el-input-group__prepend {
        background-color: #fff;
    }
</style>
