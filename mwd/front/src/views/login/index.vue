<template>
    <div class="login">
        <div style="display: flex;justify-content: center;margin-top: 150px;">
                <el-form auto-complete="on" label-position="left">
                    <h3 class="title" align="center">欢迎使用多媒体展示平台</h3>
                    <el-card style="width: 450px;margin-top: 20px">
                        <el-input style="margin-top: 30px" v-model="userAccount" placeholder="请输入用户号"></el-input>
                        <el-input style="margin-top: 20px" v-model="userPassword" placeholder="请输入密码" show-password @keyup.enter.native="doLogin"></el-input>
                        <el-input style="margin-top: 20px"
                                class="log-input"
                                v-model="userCode"
                                placeholder="请输入验证码"
                        >
                            <span @click="createCode"  slot="append">{{checkCode}}</span>
                        </el-input>

                        <el-button style="margin-top: 20px;width: 100%" type="primary" @click="doLogin">登录</el-button>
                        <el-link style="margin-top: 10px;margin-bottom:10px;float: left"  type="primary" @click="forgetPass">忘记密码</el-link>
                        <el-link style="margin-top: 10px;margin-bottom:10px;float: right"  type="primary" @click="doRegister">注册</el-link>
                    </el-card>
                </el-form>
        </div>
    </div>
</template>
<script>
    export default {
        name: "login",
        data() {
            return{
                userAccount: '',
                userPassword: '',
                userCode:'',
                checkCode:'',
            }
        },
        mounted () {
            this.createCode();
        },
        methods:{
            createCode() {
                var code = '';
                var codeLength = 4; //验证码的长度
                var random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                    'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //随机数
                for(let i = 0; i < codeLength; i++) { //循环操作
                    let index = Math.floor(Math.random() * 36); //取得随机数的索引（0~35）
                    code = code + random[index]; //根据索引取得随机数加到code上
                }
                this.checkCode = code; //把code值赋给验证码
            },
            doLogin(){
                if (!this.userAccount || !this.userPassword || !this.userCode) {
                    this.$message.error("请输入登录信息")
                }else if (this.userCode != this.checkCode){
                    this.$message.error("验证码输入错误")
                } else{
                    this.$axios.post('/user/login',
                        {
                            userAccount:this.userAccount,
                            userPassword:this.userPassword
                        }).then(resp=>{
                        if (resp.code == 200){
                            this.$router.push({name: 'index'});
                        }
                    });
                }
            },
            doRegister(){
                this.$router.push({name: 'register'});
            },

            forgetPass(){
                this.$router.push({name: 'forgetPass'});
            }
        }
    }
</script>
<style>
    .login{
        background-image: url("../static/img/background.png");
        background-size: cover;
        height: 650px;
    }
</style>

