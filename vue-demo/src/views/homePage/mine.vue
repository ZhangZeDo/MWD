<template>
    <div>
        我的名字是{{this.userName}}
    </div>
</template>

<script>
    export default {
        name: "mine",
        data(){
            return{
                userName:'',
            }
        },
        created() {
            this.userName = this.$route.query.userName
        }
    }
</script>

<style scoped>

</style>
