<template>
    <div class="forgetPass">
        <div style="display: flex;justify-content: center;margin-top: 150px">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" auto-complete="on" label-position="left">
                <h3 class="title" align="center">忘记密码</h3>
                <el-card style="width: 450px;margin-top: 20px">
                    <el-form-item prop="userAccount">
                        <el-input style="margin-top: 20px" v-model="ruleForm.userAccount" placeholder="学号或工号"></el-input>
                    </el-form-item>
                    <el-form-item prop="userName">
                        <el-input placeholder="请输入验证码" v-model="ruleForm.code" class="input-with-select">
                            <el-button slot="append" @click="getCode">获取验证码</el-button>
                        </el-input>
                    </el-form-item>
                    <el-form-item prop="userPassword">
                        <el-input style="margin-top: 10px" v-model="ruleForm.userPassword" placeholder="请输入新密码" show-password></el-input>
                    </el-form-item>
                    <el-form-item prop="reUserPassWord">
                        <el-input style="margin-top: 10px" v-model="reUserPassWord" placeholder="请确认你的密码" show-password></el-input>
                    </el-form-item>
                    <el-button style="margin-top: 20px;width: 100%" type="primary" @click="submitForm">保存</el-button>
                </el-card>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "forgetPass",
        data() {
            return{
                ruleForm:{
                    userAccount:'',
                    code:'',
                    userPassword:'',
                },
                reUserPassWord:'',
                rules:{
                    userAccount: [
                        { required: true, message: '请输入账号', trigger: 'blur' },
                        { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
                    ],
                    userPassword: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 6, max: 10, message: '长度在 6 到 10 个字符', trigger: 'blur' }
                    ],
                }
            }
        },
        methods:{
            submitForm(){
                if (!this.ruleForm.code){
                    this.$message.error("请输入验证码")
                }
                else if (this.ruleForm.userPassword != this.reUserPassWord){
                    this.$message.error("输入的2次密码不一致")
                }else{
                    this.$axios.post('/user/resetPassword',{
                        userAccount:this.ruleForm.userAccount,
                        userPassword:this.ruleForm.userPassword,
                        code:this.ruleForm.code,
                    }).then(resp=>{
                        if (resp.code == 200) {
                            this.$message.success("密码重置成功");
                            this.$router.push({name: 'login'});
                        }
                    });
                }
            },
            getCode(){
                this.$axios.post('/user/getCode',{
                    userAccount:this.ruleForm.userAccount,
                }).then(resp=>{
                    if (resp.code == 200){
                        this.$message.success("验证码发送成功，2分钟内有效，请注意查收！")
                    }
                })
            }
        }
    }
</script>

<style>
    .forgetPass{
        background-image: url("../static/img/background.png");
        background-size: cover;
        height: 650px;
    }
</style>
