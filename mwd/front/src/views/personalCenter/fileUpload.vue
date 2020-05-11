<template>
    <div>
        <div class="card-header" style="margin-top: 20px;margin-left: 100px">
            <h3>我的上传</h3>
        </div>
        <el-card style="margin:20px 100px;">
            <div slot="header" style="height: 30px">
                <el-button type="primary" style="float: left" @click="centerDialogVisible = true">新建上传</el-button>
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
                            layout="total,sizes , prev, pager, next, jumper"
                            :total="total">
                    </el-pagination>
                </div>
            </div>
        </el-card>
        <el-dialog
                title="新建上传"
                :visible.sync="centerDialogVisible"
                width="40%"
                center>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="作品名称" prop="mediaName">
                    <el-input v-model="ruleForm.mediaName" placeholder="请输入作品名称"></el-input>
                </el-form-item>
                <el-form-item label="作品类型" prop="mediaType">
                    <el-select size="small" v-model="ruleForm.mediaType" placeholder="请选择作品类型">
                        <el-option
                                v-for="item in mediaTypeList"
                                :key="item.id"
                                :label="item.typeName"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="作品描述" prop="mediaRemark">
                    <el-input type="textarea" v-model="ruleForm.mediaRemark"></el-input>
                </el-form-item>
                <el-form-item>
                    <div style="float: left;padding-left: 100px">
                        <el-upload
                                class="upload-demo"
                                ref="upload"
                                action=""
                                :on-preview="handlePreview"
                                :on-remove="handleRemove"
                                :before-remove="beforeRemove"
                                multiple
                                :limit="1"
                                accept="image/jpeg,image/gif,image/png,image/bmp"
                                :on-exceed="handleExceed"
                                :auto-upload="false"
                                :on-change="onChangeCover"
                                :file-list="coverFileList">
                            <el-button size="small" type="primary">点击上传封面</el-button>
                            <div slot="tip" class="el-upload__tip">只能上传png/gif/png/bmp文件</div>
                        </el-upload>
                    </div>
                    <div style="float: left;padding-left: 100px">
                        <el-upload
                                class="upload-demo"
                                ref="upload"
                                action=""
                                :on-preview="handlePreview"
                                :on-remove="handleRemove"
                                :before-remove="beforeRemove"
                                multiple
                                :limit="1"
                                accept="audio/mpeg,video/mp4"
                                :on-exceed="handleExceed"
                                :auto-upload="false"
                                :on-change="onChangeMedia"
                                :file-list="mediaFileList">
                            <el-button size="small" type="primary">点击上传视频</el-button>
                            <div slot="tip" class="el-upload__tip">只能上传mp3/mp4文件</div>
                        </el-upload>
                    </div>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="centerDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitForm">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "fileUpload",
        data() {
            return {
                centerDialogVisible: false,
                ruleForm: {
                    mediaName: '',
                    mediaRemark: '',
                    mediaType:'',
                },
                mediaTypeList:[],
                coverFileList:[],
                mediaFileList:[],
                mediaWorks:[],
                queryForm:{
                    status:1,
                    mediaType:'',
                    searchInput:'',
                    pageSize:6,
                    page:1,
                    belongTo:true,
                },
                total:0,
                rules: {
                    mediaName: [
                        { required: true, message: '请输入作品名称', trigger: 'blur' },
                        { min: 1, max: 20, message: '长度在 1 到 20个字符', trigger: 'blur' }
                    ],
                    mediaType: [
                        { required: true, message: '请选择作品类型', trigger: 'change' }
                    ],
                    mediaRemark: [
                        { required: true, message: '请填写作品描述', trigger: 'blur' }
                    ]
                }
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
                    this.$axios.post('/mediaWork/changeMediaWorkStatus', {
                            id:index,
                            status:0
                        }).then(resp=>{
                            if (resp.code == 200) {
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
                this.$axios.post('/mediaType/getAllMediaType',{
                    status:'1'
                }).then(resp=>{
                    if (resp.code == 200) {
                        this.mediaTypeList = resp.data
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
                    url: 'http://129.204.251.179:8083/mediaWork/uploadMediaWork',
                    data:formData,
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    },
                    timeout:180000
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
                this.$axios.post('/mediaWork/mediaWorkList',{
                    status:this.queryForm.status,
                    mediaType:this.queryForm.mediaType,
                    searchInput:this.queryForm.searchInput,
                    pageSize:this.queryForm.pageSize,
                    page:this.queryForm.page,
                    belongTo:this.queryForm.belongTo
                }).then(resp=>{
                    if (resp.code == 200) {
                        this.mediaWorks = resp.data.items;
                        this.total = resp.data.total;
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
            },
            showDetail(val){
                this.$router.push({path:'/mediaDetail',query:{"mediaId":val}})
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
