<template>
    <div style="padding-left: 35%;padding-top: 200px;width: 500px">
        <el-form  size="mini" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="140px" class="demo-ruleForm">
            <el-form-item label="用户账号">
                <el-input v-model="ruleForm.userAccount" style="width: 300px;" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="用户姓名">
                <el-input v-model="ruleForm.userName" style="width: 300px;"></el-input>
            </el-form-item>
            <el-form-item label="用户邮箱">
                <el-input v-model="ruleForm.userMail" style="width: 300px;"></el-input>
            </el-form-item>
            <el-form-item label="用户联系方式">
                <el-input v-model="ruleForm.userPhone" style="width: 300px;"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="success" size="mini" style="width: 100px;float: left" @click="changePassword" >修改密码</el-button>
                <el-button type="success" size="mini" style="width: 100px;margin-left: 100px" @click="submitForm" >保存</el-button>
            </el-form-item>
            <div>

            </div>
        </el-form>
        <el-dialog
                title="修改密码"
                :visible.sync="changeDialog"
                width="40%"
                center>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="旧密码">
                    <el-input type="password" v-model="ruleForm.userPassword1" placeholder="请输入旧密码"></el-input>
                </el-form-item>
                <el-form-item label="新密码">
                    <el-input type="password" v-model="ruleForm.userPassword2" placeholder="请输入新密码"></el-input>
                </el-form-item>
                <el-form-item label="请确认新密码">
                    <el-input type="password" v-model="ruleForm.userPassword3" placeholder="请确认新密码"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="changeDialog = false">取 消</el-button>
                <el-button type="primary" @click="submitChangeDialog">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "userInfo",
        data(){
            return{
                ruleForm: {
                    id:'',
                    userAccount:'',
                    userName:'',
                    userMail:'',
                    userPhone:'',
                    userPassword:'',
                    userPassword1:'',
                    userPassword2:'',
                    userPassword3:'',
                },
                changeDialog:false,
                rules:{
                    userName: [
                        { required: true, message: '请输入用户名称', trigger: 'change' },
                    ],
                    userPassword1: [
                        { required: true, message: '请输入用户名称', trigger: 'change' },
                    ],
                    userPassword2: [
                        { required: true, message: '请输入用户名称', trigger: 'change' },
                    ],
                    userPassword3: [
                        { required: true, message: '请输入用户名称', trigger: 'change' },
                    ],

                }
            }
        },
        created() {
            this.getLoginInfo()
        },
        methods:{
            getLoginInfo(){
                this.$axios.post('/user/getLoginInfo',{

                }).then(resp=>{
                    if (resp.code == 200) {
                        this.ruleForm = resp.data
                    }else{
                        this.$message.error(resp.message);
                    }
                });
            },
            submitForm(){
                if (this.ruleForm.userName ==null || this.ruleForm.userName == ''){
                    this.$message.warning("用户姓名不能为空");
                }else{
                    this.$axios.post('/user/updateUserInfo',{
                        id:this.ruleForm.id,
                        userAccount:this.ruleForm.userAccount,
                        userName:this.ruleForm.userName,
                        userMail:this.ruleForm.userMail,
                        userPhone:this.ruleForm.userPhone
                    }).then(resp=>{
                        if (resp.code == 200) {
                            this.$message.success("更新成功");
                        }else{
                            this.$message.error(resp.message);
                        }
                    });
                }
            },
            changePassword(){
                this.changeDialog = true
            },
            submitChangeDialog(){
                if (this.ruleForm.userPassword != this.ruleForm.userPassword1){
                    this.$message.error("密码不正确，请重新输入");
                }else if (!this.ruleForm.userPassword2){
                    this.$message.error("新密码不能为空");
                } else if (this.ruleForm.userPassword2 != this.ruleForm.userPassword3) {
                    this.$message.error("新密码2次输入不一致，请确认");
                }else {
                    this.$axios.post('/user/changeUserPassword', {
                        userAccount: this.ruleForm.userAccount,
                        userPassword: this.ruleForm.userPassword2,
                    }).then(resp => {
                        if (resp.code == 200) {
                            this.$message.success("更新成功，请重新登录");
                            this.$router.push({name: 'login'});
                        } else {
                            this.$message.error(resp.message);
                            this.changeDialog = false;
                        }
                    });
                }
            },
        }
    }
</script>

<style scoped>

</style>
