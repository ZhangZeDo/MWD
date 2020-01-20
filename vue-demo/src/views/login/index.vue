<template>
    <div>
        <button @click="jump('homePage')">登录</button>
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        name: "index",
        data(){
            return{

            }
        },
        methods:{
          jump(path){
            this.$router.push(path);
          }
        }
    }
</script>

<style scoped>

</style>
