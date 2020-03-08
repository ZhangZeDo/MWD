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
            this.getUser()
        },
        methods:{
            getUser(){
                this.axios({
                    method: 'get',
                    url: '/user/selectUserByAccount',
                    data: {
                        firstName: 'Fred',
                        lastName: 'Flintstone'
                    }
                });
            }
        }
    }
</script>

<style scoped>

</style>
