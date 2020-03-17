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
              <el-menu-item index="5" @click="jump('personalCenter')">个人中心</el-menu-item>
              <el-submenu v-if="user.roleType != 'ordinary'" index="6">
                  <template slot="title">系统管理</template>
                  <el-menu-item index="2-1">选项1</el-menu-item>
                  <el-menu-item index="2-2">选项2</el-menu-item>
                  <el-menu-item index="2-3">选项3</el-menu-item>
                  <el-submenu index="2-4">
                      <template slot="title">选项4</template>
                      <el-menu-item index="2-4-1">选项1</el-menu-item>
                      <el-menu-item index="2-4-2">选项2</el-menu-item>
                      <el-menu-item index="2-4-3">选项3</el-menu-item>
                  </el-submenu>
              </el-submenu>
          </el-menu>
      </div>
  </div>
</template>

<script>
    import axios from 'axios'
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
                axios({
                    method: 'GET',
                    url: 'http://localhost:8083/user/getLoginInfo',
                }).then(resp=>{
                    if (resp.data.code == 200) {
                        this.user = resp.data.data
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
