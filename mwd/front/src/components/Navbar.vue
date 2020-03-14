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
                  style="padding-left: 40%">
              <el-menu-item index="1" @click="jump('homePage')" >首页</el-menu-item>
              <el-submenu index="2">
                  <template slot="title">我的工作台</template>
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
              <el-menu-item index="3">消息中心</el-menu-item>
              <el-menu-item index="4" @click="jump('order')">订单管理</el-menu-item>
              <el-menu-item index="5" @click="jump('personalCenter')">个人中心</el-menu-item>
          </el-menu>
      </div>
  </div>
</template>

<script>

export default {
  name: 'Navbar',
  props: {
    msg: String
  },
    data() {
        return {
            activeIndex: '1',
        };
    },
    created() {
        this.getUser()
    },
    methods: {
        jump(path){
            this.$router.push({path:path})
        },

        getUser(){
          //this.user = this.$route.params.user
            window.console.info(this.$route.params)
        }
    }
}
</script>

<style scoped>
</style>
