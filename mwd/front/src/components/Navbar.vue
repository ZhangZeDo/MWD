<template>
  <div>
      <div>
          <el-menu
                  :default-active="activeIndex"
                  class="el-menu-demo"
                  mode="horizontal"
                  background-color="#545c64"
                  text-color="#fff"
                  active-text-color="#ffd04b"
                  style="padding-left: 38%">
              <el-menu-item index="1" @click="jump('homePage')" >首页</el-menu-item>
              <el-menu-item index="2" @click="jump('homePage')" >新闻公告</el-menu-item>
              <el-menu-item index="3">消息中心</el-menu-item>
              <el-menu-item index="4" @click="jump('order')">订单管理</el-menu-item>
              <el-submenu index="5">
                  <template slot="title">个人中心</template>
                  <el-menu-item index="5-1" @click="jump('userInfo')">个人信息</el-menu-item>
                  <el-menu-item index="5-2" @click="jump('bookmark')">我的收藏</el-menu-item>
                  <el-menu-item index="5-3" @click="jump('fileUpload')">我的上传</el-menu-item>
                  <el-menu-item index="5-4" @click="loginOut">退出登录</el-menu-item>
              </el-submenu>
              <el-submenu v-if="user.roleType != 'ordinary'" index="6">
                  <template slot="title">系统管理</template>
                  <el-menu-item index="6-1" style="" >选项1</el-menu-item>
                  <el-menu-item index="6-2">选项2</el-menu-item>
                  <el-menu-item index="6-3">选项3</el-menu-item>
                  <el-submenu index="6-4">
                      <template slot="title">选项4</template>
                      <el-menu-item index="6-4-1">选项1</el-menu-item>
                      <el-menu-item index="2-4-2">选项2</el-menu-item>
                      <el-menu-item index="2-4-3">选项3</el-menu-item>
                  </el-submenu>
              </el-submenu>
          </el-menu>
      </div>

  </div>
</template>

<script>
    export default {
        name: 'Navbar',
        data() {
            return {
                activeIndex: '1',
                user:'',
            };
        },
        created() {
            this.getLoginInfo()
        },
        methods: {
            jump(path){
                this.$router.push({path:path})
            },

            getLoginInfo(){
                this.$axios.post('/user/getLoginInfo',{
                }).then(resp=>{
                    if (resp.code == 200) {
                        this.user = resp.data
                    }else{
                        this.$message.error(resp.message);
                    }
                });
            },

            loginOut(){
                this.$axios.post('/user/loginOut',{

                }).then(
                    this.$router.push({name: 'login'})
                );
            }
        }
    }
</script>

<style scoped>
</style>
