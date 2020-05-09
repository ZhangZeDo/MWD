<template>
    <div>
        <div class="card-header" style="margin-top: 20px;margin-left: 100px">
            <h3>新闻管理</h3>
        </div>
        <el-card style="margin:20px 100px;">
            <div slot="header" style="height: 30px">
                <div style="float: left;margin-right: 10px;">
                    <label style="">标题:</label>
                </div>
                <div style="float: left;margin-right: 10px">
                    <el-input size="small" placeholder="搜索新闻标题" v-model="queryForm.searchInput" clearable @change="queryData()">
                        <i slot="prefix" class="el-input__icon el-icon-search"></i>
                    </el-input>
                </div>
                <div style="float: right;margin-right: 10px">
                    <el-button type="primary" size="mini" @click="showCreateNewsDialog()">新增</el-button>
                </div>
            </div>
            <div>
                <el-table :data="newsList" style="width: 100%" stripe>
                    <el-table-column prop="title" label="新闻标题" style="width: 15%"></el-table-column>
                    <el-table-column prop="content" label="具体内容" style="width: 15%" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="publishUser" label="发布人" style="width: 15%"></el-table-column>
                    <el-table-column  label="发布时间" style="width: 15%" >
                        <template slot-scope="scope">
                            {{parseTime(scope.row.createDatetime) }}
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" style="width: 10%">
                        <template slot-scope="scope">
                            <el-button @click="deleteNew(scope.row)"  type="danger" size="mini">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div style="padding-top: 20px">
                <div class="block">
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page="queryForm.page"
                            :page-size="queryForm.pageSize"
                            :page-sizes="[5,10,15,20]"
                            layout="total, sizes,prev, pager, next, jumper"
                            :total="total">
                    </el-pagination>
                </div>
            </div>
        </el-card>
        <el-dialog
                title="新建新闻公告"
                :visible.sync="createNewsDialog"
                width="40%"
                center>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="新闻标题" prop="title">
                    <el-input v-model="ruleForm.title" placeholder="请输入新闻标题"></el-input>
                </el-form-item>
                <el-form-item label="新闻内容" prop="content">
                    <el-input type="textarea" v-model="ruleForm.content"></el-input>
                </el-form-item>
                <el-form-item>
                    <div style="float: left;">
                        <el-upload
                                class="upload-demo"
                                ref="upload"
                                action=""
                                :on-preview="handlePreview"
                                :on-remove="handleRemove"
                                :before-remove="beforeRemove"
                                multiple
                                :limit="1"
                                :on-exceed="handleExceed"
                                :auto-upload="false"
                                :on-change="onChangeFile"
                                :file-list="newsFileList">
                            <el-button size="small" type="primary">点击上传附件</el-button>
                        </el-upload>
                    </div>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="createNewsDialog = false">取 消</el-button>
                <el-button type="primary" @click="submitForm">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "newsList",
        data(){
            return {
                queryForm:{
                    searchInput:'',
                    pageSize:5,
                    page:1,
                },
                newsList:[],
                total:0,
                createNewsDialog:false,
                ruleForm:{
                    title:'',
                    content:'',
                },
                rules: {
                    title: [
                        { required: true, message: '请输入新闻主题', trigger: 'blur' },
                        { min: 3, max: 20, message: '长度在 3 到 20个字符', trigger: 'blur' }
                    ],
                    content: [
                        { required: true, message: '请输入新闻内容', trigger: 'blur' }
                    ],
                },
                newsFileList:[],
            }
        },
        created() {
            this.queryData();
        },
        methods:{
            parseTime(dateObj) {
                var date = new Date(dateObj);
                var year = date.getFullYear();
                var month = ("0" + (date.getMonth() + 1)).slice(-2);
                var day = ("0" + date.getDate()).slice(-2);
                var hour = ("0" + date.getHours()).slice(-2);
                var min = ("0" + date.getMinutes()).slice(-2);
                var second = ("0" + date.getSeconds()).slice(-2);
                return year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + second;
            },
            queryData(){
                this.$axios.post('/news/queryNewsList',{
                    searchInput:this.queryForm.searchInput,
                    pageSize:this.queryForm.pageSize,
                    page:this.queryForm.page
                }).then(resp=>{
                    if (resp.code == 200) {
                        this.newsList = resp.data.items;
                        this.total = resp.data.total;
                    }
                });
            },
            deleteNew(row){
                this.$confirm('此操作将删除该新闻, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios.post('/news/deleteNewsById',{
                        id:row.id,
                    }).then(resp=>{
                        if (resp.code == 200){
                            this.$message.success("操作成功");
                            this.queryData();
                        }
                    })
                }).catch(() => {

                });
            },
            submitForm(){
                let formData = new FormData();
                if (this.newsFileList.length>0){
                    formData.append("newsFile",this.newsFileList[0].raw);
                }
                formData.append("title",this.ruleForm.title);
                formData.append("content",this.ruleForm.content);
                axios({
                    method: 'POST',
                    url: 'http://129.204.251.179:8083/news/addNews',
                    data:formData
                }).then(resp=>{
                    if (resp.data.code == 200) {
                        this.$message.success("新建成功");
                        this.createNewsDialog = false
                        this.queryData()
                    }
                });
            },
            handleSizeChange(val) {
                this.queryForm.pageSize = val;
                this.queryData()
            },
            handleCurrentChange(val) {
                this.queryForm.page = val;
                this.queryData()
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
            onChangeFile(newsFileList,fileList){
                this.newsFileList = fileList
            },
            showCreateNewsDialog(){
                this.createNewsDialog = true;
            },
            downLoadFile(){
                window.console.info("=============")
            }
        }

    }
</script>

<style scoped>

</style>
