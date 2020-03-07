<template>
    <div>
        我的名字是{{userName}}
    </div>
</template>

<script>
    export default {
        name: "center",
        data(){
            return{
                userName:'',
            }
        },
        created() {
            this.userName = this.$route.query.modelName
        }
    }
</script>

<style scoped>

</style>
