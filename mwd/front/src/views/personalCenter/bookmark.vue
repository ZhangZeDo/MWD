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
                    <el-input size="small" placeholder="搜索标题/描述" v-model="queryForm.searchInput">
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
                                    <img style="width: 150px;height: 110px" :src="index.mediaCover" class="image">
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
                            layout="total, prev, pager, next, jumper"
                            :total="total">
                    </el-pagination>
                </div>
            </div>
        </el-card>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "bookmark",
        data() {
            return {
                mediaTypeList:[],
                mediaWorks:[],
                queryForm:{
                    mediaType:'',
                    searchInput:'',
                    pageSize:12,
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
            onChangeCover(coverFileList,fileList){
                this.coverFileList = fileList;
            },
            onChangeMedia(mediaFileList,fileList){
                this.mediaFileList = fileList;
            },
            getAllMediaType(){
                axios({
                    method: 'POST',
                    url: 'http://localhost:8083/mediaType/getAllMediaType',
                    data:{
                        status:'1'
                    }
                }).then(resp=>{
                    if (resp.data.code == 200) {
                        this.mediaTypeList = resp.data.data
                    }else{
                        this.$message.error(resp.data.message);
                    }
                });
            },
            submitForm(){
                let formData = new FormData();
                formData.append("coverFile",this.coverFileList[0].raw);
                formData.append("mediaFile",this.mediaFileList[0].raw);
                formData.append("mediaName",this.ruleForm.mediaName);
                formData.append("mediaRemark",this.ruleForm.mediaRemark);
                formData.append("mediaType",this.ruleForm.mediaType);
                axios({
                    method: 'POST',
                    url: 'http://localhost:8083/mediaWork/uploadMediaWork',
                    data:formData
                }).then(resp=>{
                    if (resp.data.code == 200) {
                        this.$message.success("上传成功");
                        this.centerDialogVisible = false
                    }else{
                        this.$message.error(resp.data.message);
                    }
                });
            },
            queryData(){
                axios({
                    method: 'POST',
                    url: 'http://localhost:8083/bookmark/getBookMediaList',
                    data:this.queryForm
                }).then(resp=>{
                    if (resp.data.code == 200) {
                        this.mediaWorks = resp.data.data.items;
                        this.total = resp.data.data.total;
                    }else{
                        this.$message.error(resp.data.message);
                    }
                });
            },
            handleRemove(file) {
                window.console.log(file);
            },
            handlePreview(file) {
                window.console.log(file);
            },
            handleExceed() {
                this.$message.warning(`只能选择一个文件`);
            },
            beforeRemove(file) {
                window.console.log(file);
                return this.$confirm(`确定移除 ${ file.name }？`);
            }
        }
    }
</script>

<style scoped>
    .avatar-uploader .el-upload {
        border: 1px dashed #363a45;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
    .time {
        font-size: 13px;
        color: #999;
    }

    .bottom {
        margin-top: 13px;
        line-height: 12px;
    }

    .button {
        padding: 0;
        float: right;
    }

    .image {
        width: 100%;
        display: block;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }
</style>
