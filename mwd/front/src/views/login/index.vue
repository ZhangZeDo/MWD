<template>
    <div >
        <div style="display: flex;justify-content: center;margin-top: 150px">
                <el-form auto-complete="on" label-position="left">
                    <h3 class="title" align="center">欢迎使用多媒体展示平台</h3>
                    <el-card style="width: 450px;margin-top: 20px">
                        <el-input style="margin-top: 30px" v-model="userAccount" placeholder="请输入用户号"></el-input>
                        <el-input style="margin-top: 20px" v-model="userPassword" placeholder="请输入密码" show-password @keyup.enter.native="doLogin"></el-input>
                        <el-button style="margin-top: 20px;width: 100%" type="primary" @click="doLogin">登录</el-button>
                        <el-link style="margin-top: 10px;margin-bottom:10px;float: left"  type="primary">联系我们</el-link>
                        <el-link style="margin-top: 10px;margin-bottom:10px;float: right"  type="primary" @click="doRegister">注册</el-link>
                    </el-card>
                </el-form>
        </div>
    </div>
</template>
<script>
    /*import axios from 'axios'*/
    export default {
        name: "login",
        data() {
            return{
                userAccount: '',
                userPassword: '',
            }
        },
        methods:{
            doLogin(){
                this.$axios.post(
                    '/user/login',
                    {
                        userAccount:this.userAccount,
                        userPassword:this.userPassword
                    }).then(resp=>{
                        window.console.info(resp)
                        this.$router.push({name: 'index'});
                });
            },
            doRegister(){
                this.$router.push({name: 'register'});
            }
        }
    }
</script>
