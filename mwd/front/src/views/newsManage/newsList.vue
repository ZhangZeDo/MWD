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
                    <el-button type="primary" size="mini">新增</el-button>
                </div>
            </div>
            <div>
                <el-table :data="newsList" style="width: 100%" stripe>
                    <el-table-column prop="userAccount" label="管理员账号" style="width: 15%"></el-table-column>
                    <el-table-column prop="userName" label="管理员名称" style="width: 15%"></el-table-column>
                    <el-table-column prop="userMail" label="邮箱地址" style="width: 15%"></el-table-column>
                    <el-table-column prop="userPhone" label="联系方式" style="width: 15%"></el-table-column>
                    <el-table-column  label="创建时间" style="width: 15%" >
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
                axios({
                    method: 'POST',
                    url: 'http://localhost:8083/news/queryNewsList',
                    data:this.queryForm
                }).then(resp=>{
                    if (resp.data.code == 200) {
                        this.newsList = resp.data.data.items;
                        this.total = resp.data.data.total;
                    }else{
                        this.$message.error(resp.data.message);
                    }
                });
            },
            deleteNew(row){
                this.$confirm('此操作将删除该新闻, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios.post('/user/changeUserStatus',{
                        id:row.id,
                        status: row.status
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
                axios({
                    method: 'POST',
                    url: 'http://localhost:8083/user/register',
                    data:this.userForm
                }).then(resp=>{
                    if (resp.data.code == 200) {
                        this.$message.success("管理员创建成功,初始密码为000000,请联系管理员完善信息");
                    }
                    this.createDialog = false
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
        }

    }
</script>

<style scoped>

</style>
