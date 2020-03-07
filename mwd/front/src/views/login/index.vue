<template>
    <div>
        <button class="btn" @click="jump('index')">登录</button>
    </div>
</template>

<script>
    export default {
        name: "login",
        data(){
            return{

            }
        },
        methods:{
          jump(path){
            this.$router.push({name:path});
          }
        }
    }
</script>

<style scoped>
div{
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

btn{
    width: 150px;
    height: 20px;
}
</style>
