<template>
    <div>
        <div class="card-header" style="margin-top: 20px;margin-left: 100px">
            <h3>我的收藏</h3>
        </div>
        <el-card style="margin:20px 100px;">
            <div slot="header" style="height: 30px">
                <div style="float: right">
                    <el-button size="small" @click="queryData">搜索</el-button>
                </div>
                <div style="float: right;margin-right: 10px">
                    <el-input size="small" placeholder="搜索标题/描述" v-model="queryForm.searchInput" clearable>
                        <i slot="prefix" class="el-input__icon el-icon-search"></i>
                    </el-input>
                </div>
                <div style="float: right;margin-right: 10px">
                    <el-select size="small" v-model="queryForm.mediaType" clearable placeholder="请选择作品类型">
                        <el-option
                                v-for="item in mediaTypeList"
                                :key="item.id"
                                :label="item.typeName"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </div>
            </div>
            <div>
                <el-row style="padding: 0 20px">
                    <el-row>
                        <el-col :span="8" v-for="index in mediaWorks" :key="index.id" >
                            <el-card style="margin:10px 20px;width: 500px;height: 150px;float: left">
                                <div style="float: left">
                                    <img style="width: 150px;height: 110px" :src="index.mediaCover" class="image" @click="showDetail(index.id)"/>
                                </div>
                                <div style="float: left;width: 250px;padding-left: 5px">
                                    <span>{{index.mediaName}}</span><br><br>
                                    <span>评分:{{index.mediaGrade}}</span><br><br>
                                    <span v-if="index.mediaRemark.length < 35" style="font-size: x-small">{{index.mediaRemark}}</span>
                                    <span v-else style="font-size: x-small">{{index.mediaRemark.substring(0,35)}}...</span>
                                </div>
                                <div style="float: right;padding-top: 90px">
                                    <el-button type="text" @click="open(index.id)">移除</el-button>
                                </div>
                            </el-card>
                        </el-col>
                    </el-row>
                </el-row>
            </div>
            <div align="center" style="margin-bottom: 10px">
                <div class="block" style="padding: 0 32px">
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page="queryForm.page"
                            :page-size="queryForm.pageSize"
                            :page-sizes="[6,9,12,15]"
                            layout="total, sizes, prev,pager, next, jumper"
                            :total="total">
                    </el-pagination>
                </div>
            </div>
        </el-card>
    </div>
</template>

<script>
    export default {
        name: "bookmark",
        data() {
            return {
                mediaTypeList:[],
                mediaWorks:[],
                queryForm:{
                    mediaType:'',
                    searchInput:'',
                    pageSize:6,
                    page:1,
                },
                total:0,
            };
        },
        created(){
            this.getAllMediaType();
            this.queryData();
        },
        methods: {
            open(index) {
                this.$confirm('此操作将永久删除该作品, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios.post('/bookmark/removeBookmark', {
                        mediaId:index,
                    }).then(resp =>{
                        if (resp.code == 200){
                            this.$message.success("操作成功");
                            this.queryData()
                        }
                    });
                })
            },
            handleSizeChange(val) {
                this.queryForm.pageSize = val
                this.queryData()
            },
            handleCurrentChange(val) {
                this.queryForm.page = val
                this.queryData()
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
            queryData(){
                this.$axios.post('/bookmark/getBookMediaList',{
                    mediaType:this.queryForm.mediaType,
                    searchInput:this.queryForm.searchInput,
                    pageSize:this.queryForm.pageSize,
                    page:this.queryForm.page
                }).then(resp=>{
                    if (resp.code == 200) {
                        this.mediaWorks = resp.data.items;
                        this.total = resp.data.total;
                    }
                });
            },
            showDetail(val){
                this.$router.push({path:'/mediaDetail',query:{"mediaId":val}})
            }
        }
    }
</script>

<style scoped>
    .image {
        width: 100%;
        display: block;
    }
</style>
