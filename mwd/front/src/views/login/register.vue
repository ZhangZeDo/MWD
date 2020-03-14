<template>
    <div >
        <div style="display: flex;justify-content: center;margin-top: 150px">
            <el-form auto-complete="on" label-position="left">
                <h3 class="title" align="center">用户注册</h3>
                <el-card style="width: 450px;margin-top: 20px">
                    <el-input style="margin-top: 20px" v-model="ruleForm.userAccount" placeholder="学号或工号"></el-input>
                    <el-input style="margin-top: 10px" v-model="ruleForm.userName" placeholder="请输入昵称"></el-input>
                    <el-input style="margin-top: 10px" v-model="ruleForm.userPassword" placeholder="请输入密码" show-password></el-input>
                    <el-input style="margin-top: 10px" v-model="reUserPassWord" placeholder="请确认你的密码" show-password></el-input>
                    <el-input style="margin-top: 10px" v-model="ruleForm.userMail" placeholder="请输入邮箱"></el-input>
                    <el-input style="margin-top: 20px" v-model="ruleForm.userPhone" placeholder="请输入手机号"></el-input>
                    <el-button style="margin-top: 20px;width: 100%" type="primary" @click="doRegister">注册</el-button>
                </el-card>
            </el-form>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
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
            }
        },
        methods:{
            doRegister(){
                axios({
                    method: 'POST',
                    url: 'http://localhost:8083/user/register',
                    data: this.ruleForm
                }).then(resp=>{
                    if (resp.data.code == 200) {
                        this.$message.success("注册成功");
                        this.$router.push({name: 'login'});
                    }else{
                        this.$message.error(resp.data.message);
                    }
                });
            }
        }
    }
</script>

<style scoped>

</style>
