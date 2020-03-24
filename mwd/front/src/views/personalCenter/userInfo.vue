<template>
    <div style="padding-left: 35%;padding-top: 200px;width: 500px">
        <el-form  size="mini" :model="ruleForm" ref="ruleForm" label-width="140px" class="demo-ruleForm">
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
            <el-button type="success" size="mini" style="margin-left: 340px;width: 100px" @click="submitForm" >保存</el-button>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "userInfo",
        data(){
            return{
                ruleForm: {
                    userAccount:'',
                    userName:'',
                    userMail:'',
                    userPhone:''
                },
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
                } else{
                    this.$axios.post('/user/updateUserInfo',{
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
        }
    }
</script>

<style scoped>

</style>
