<template>
    <div>
        <div class="card-header" style="margin-top: 20px;margin-left: 100px">
            <h3>管理员管理</h3>
        </div>
        <el-card style="margin:20px 100px;">
            <div slot="header" style="height: 30px">
                <div style="float: left;margin-right: 10px;">
                   <label style="">账号或名称:</label>
                </div>
                <div style="float: left;margin-right: 10px">
                    <el-input size="small" placeholder="搜索账号或名称" v-model="queryForm.searchInput" clearable @change="queryData()">
                        <i slot="prefix" class="el-input__icon el-icon-search"></i>
                    </el-input>
                </div>
                <div style="float: left;margin-right: 10px;padding-left: 20px">
                    <label style="">管理员状态:</label>
                </div>
                <div style="float: left;margin-right: 10px">
                    <el-select size="small" v-model="queryForm.status" clearable placeholder="请选择管理员状态" @change="queryData()">
                        <el-option value="1" label="有效">有效</el-option>
                        <el-option value="0" label="无效">无效</el-option>
                    </el-select>
                </div>
                <div style="float: right;margin-right: 10px">
                    <el-button @click="showCreateDialog"  type="primary" size="small">新增</el-button>
                </div>
            </div>
            <div>
                <el-table :data="userList" style="width: 100%" stripe>
                    <el-table-column prop="userAccount" label="管理员账号" style="width: 15%"></el-table-column>
                    <el-table-column prop="userName" label="管理员名称" style="width: 15%"></el-table-column>
                    <el-table-column prop="userMail" label="邮箱地址" style="width: 15%"></el-table-column>
                    <el-table-column prop="userPhone" label="联系方式" style="width: 15%"></el-table-column>
                    <el-table-column  label="创建时间" style="width: 15%" >
                        <template slot-scope="scope">
                            {{parseTime(scope.row.createDatetime) }}
                        </template>
                    </el-table-column>
                    <el-table-column label="状态" style="width: 15%">
                        <template slot-scope="scope">
                            {{parseStatus(scope.row.status) }}
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" style="width: 10%">
                        <template slot-scope="scope">
                            <el-button v-if="scope.row.status == 0" @click="changeUserStatus(scope.row,'启用')"  type="primary" size="mini">启用</el-button>
                            <el-button v-else @click="changeUserStatus(scope.row,'禁用')"  type="danger" size="mini">禁用</el-button>
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
                            layout="total,sizes, prev ,pager, next, jumper"
                            :total="total">
                    </el-pagination>
                </div>
            </div>
            <el-dialog
                    title="管理员新建"
                    :visible.sync="createDialog"
                    width="40%"
                    center>
                <el-form :model="userForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="管理员账号" prop="mediaName">
                        <el-input v-model="userForm.userAccount" placeholder="请输入管理员账号"></el-input>
                    </el-form-item>
                    <el-form-item label="管理员名称" prop="mediaType">
                        <el-input v-model="userForm.userName" placeholder="请输入管理员名称"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                <el-button @click="createDialog = false">取 消</el-button>
                <el-button type="primary" @click="submitForm">确 定</el-button>
            </span>
            </el-dialog>
        </el-card>
    </div>
</template>

<script>
    export default {
        name: "AdminManage",
        data(){
            return {
                queryForm:{
                    searchInput:'',
                    status:'',
                    pageSize:5,
                    page:1,
                    roleType:'admin',
                },
                userList:[],
                total:0,
                createDialog:false,
                userForm:{
                    userName:'',
                    userAccount:'',
                    roleType:'admin',
                    userPassword:'000000',
                },
                rules: {
                    userAccount: [
                        { required: true, message: '请输入用户账号', trigger: 'change' },
                    ],
                    userName: [
                        { required: true, message: '请输入用户名称', trigger: 'change' }
                    ],
                }
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
            parseStatus(status){
              if (status == 0){
                  return "无效";
              }else{
                  return "有效"
              }
            },
            queryData(){
                this.$axios.post('/user/queryUserList',{
                    searchInput:this.queryForm.searchInput,
                    status:this.queryForm.status,
                    pageSize:this.queryForm.pageSize,
                    page:this.queryForm.page,
                    roleType:this.queryForm.roleType
                }).then(resp=>{
                    if (resp.code == 200) {
                        this.userList = resp.data.items;
                        this.total = resp.data.total;
                    }
                });
            },
            changeUserStatus(row,operate){
                this.$confirm('此操作将'+operate+'该管理员, 是否继续?', '提示', {
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
                this.$axios.post('/user/register',{
                    userName:this.userForm.userName,
                    userAccount:this.userForm.userAccount,
                    roleType:this.userForm.roleType,
                    userPassword:this.userForm.userPassword
                }).then(resp=>{
                    if (resp.code == 200) {
                        this.$message.success("管理员创建成功,初始密码为000000,请联系该管理员完善信息");
                    }
                    this.queryData()
                    this.createDialog = false
                });
            },
            showCreateDialog(){
                this.createDialog = true;
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
