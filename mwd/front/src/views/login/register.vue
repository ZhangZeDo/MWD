<template>
    <div class="register">
        <div style="display: flex;justify-content: center;margin-top: 150px">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" auto-complete="on" label-position="left">
                <h3 class="title" align="center">用户注册</h3>
                <el-card style="width: 450px;margin-top: 20px">
                    <el-form-item prop="userAccount">
                        <el-input style="margin-top: 20px" v-model="ruleForm.userAccount" placeholder="学号或工号"></el-input>
                    </el-form-item>
                    <el-form-item prop="userName">
                        <el-input style="margin-top: 10px" v-model="ruleForm.userName" placeholder="请输入昵称"></el-input>
                    </el-form-item>
                    <el-form-item prop="userPassword">
                        <el-input style="margin-top: 10px" v-model="ruleForm.userPassword" placeholder="请输入密码" show-password></el-input>
                    </el-form-item>
                    <el-form-item prop="reUserPassWord">
                        <el-input style="margin-top: 10px" v-model="reUserPassWord" placeholder="请确认你的密码" show-password></el-input>
                    </el-form-item>
                    <el-form-item prop="userMail">
                        <el-input style="margin-top: 10px" v-model="ruleForm.userMail" placeholder="请输入邮箱"></el-input>
                    </el-form-item>
                    <el-form-item prop="userPhone">
                        <el-input style="margin-top: 20px" v-model="ruleForm.userPhone" placeholder="请输入手机号"></el-input>
                    </el-form-item>
                    <el-button style="margin-top: 20px;width: 100%" type="primary" @click="doRegister">注册</el-button>
                </el-card>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "register",
        data() {
            return{
                ruleForm:{
                    userAccount:'',
                    userName:'',
                    userPassword:'',
                    userMail:'',
                    userPhone:'',
                    roleType:'ordinary',
                },
                reUserPassWord:'',
                rules:{
                    userAccount: [
                        { required: true, message: '请输入账号', trigger: 'blur' },
                        { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
                    ],
                    userName: [
                        { required: true, message: '请输入名称', trigger: 'blur' },
                        { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
                    ],
                    userPassword: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 6, max: 10, message: '长度在 6 到 10 个字符', trigger: 'blur' }
                    ],
                    userMail:[
                        { required: true, message: '请输入邮箱', trigger: 'blur' },
                        { min: 1, max: 30, message: '请输入正确的邮箱', trigger: 'blur' }
                    ]
                }
            }
        },
        methods:{
            doRegister(){
                if (this.ruleForm.userPassword != this.reUserPassWord){
                    this.$message.error("输入的2次密码不一致")
                }else if (!this.ruleForm.userMail){
                    this.$message.error("请输入邮箱信息")
                }else{
                    this.$axios.post('/user/register',{
                        userAccount:this.ruleForm.userAccount,
                        userName:this.ruleForm.userName,
                        userPassword:this.ruleForm.userPassword,
                        userMail:this.ruleForm.userMail,
                        userPhone:this.ruleForm.userPhone,
                        roleType:this.ruleForm.roleType,
                    }).then(resp=>{
                        if (resp.code == 200) {
                            this.$message.success("注册成功");
                            this.$router.push({name: 'login'});
                        }
                    });
                }
            }
        }
    }
</script>

<style>
    .register{
        background-image: url("../static/img/background.png");
        background-size: cover;
        height: 650px;
    }
</style>
