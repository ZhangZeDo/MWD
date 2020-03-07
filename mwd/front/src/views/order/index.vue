<template>
    <div>
        订单管理 {{userName}}
    </div>

</template>

<script>

    export default {
        name: "order",
        data(){
            return{
                userName:'',
            }
        },
        created() {
            this.$axios.get('http://localhost:8081/user/selectUserByAccount').then(function (resp) {
                this.userName = resp.data.userName
            })

        }
    }
</script>

<style scoped>

</style>
