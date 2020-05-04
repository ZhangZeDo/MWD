<template>
  <div>
      <div style="background-color:#545c64;">
          <el-menu
                  :default-active="activeIndex"
                  class="el-menu-demo"
                  mode="horizontal"
                  background-color="#545c64"
                  text-color="#fff"
                  active-text-color="#ffd04b"
                  style="padding-left: 33%;">
              <el-menu-item index="1" @click="jump('homePage')" >首页</el-menu-item>
              <el-menu-item index="2" @click="jump('search')" >发现</el-menu-item>
              <el-menu-item index="3" @click="jump('news')" >新闻公告</el-menu-item>
              <el-submenu index="5">
                  <template slot="title">个人中心</template>
                  <el-menu-item index="5-1" @click="jump('userInfo')">个人信息</el-menu-item>
                  <el-menu-item index="5-2" @click="jump('bookmark')">我的收藏</el-menu-item>
                  <el-menu-item index="5-3" @click="jump('fileUpload')">我的上传</el-menu-item>
                  <el-menu-item index="5-4" @click="loginOut">退出登录</el-menu-item>
              </el-submenu>
              <el-submenu v-if="user.roleType != 'ordinary'" index="6">
                  <template slot="title">系统管理</template>
                  <el-submenu index="6-1">
                      <template slot="title">人员管理</template>
                      <el-menu-item index="6-1-1" @click="jump('adminManage')">管理员管理</el-menu-item><!--v-if="user.roleType == 'superAdmin'"-->
                      <el-menu-item index="6-1-2" @click="jump('userManage')">用户管理</el-menu-item>
                  </el-submenu>
                  <el-submenu index="6-2">
                      <template slot="title">作品管理</template>
                      <el-menu-item index="6-2-2" @click="jump('mediaTypeManage')">作品类型</el-menu-item>
                      <el-menu-item index="6-2-3" @click="jump('mediaList')">作品列表</el-menu-item>
                  </el-submenu>
                  <el-menu-item index="6-3" @click="jump('newsList')">新闻管理</el-menu-item>
                  <el-submenu index="6-4">
                      <template slot="title">系统配置</template>
                      <el-menu-item index="6-4-1" @click="jump('timingTask')">定时任务</el-menu-item>
                      <el-menu-item index="6-4-1" @click="jump('variableConfig')">变量配置</el-menu-item>
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
