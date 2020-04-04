<template>
    <div>
        订单管理 {{userName}}
    </div>

</template>

<script>
    import axios from 'axios'
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
                axios({
                    method: 'GET',
                    url: 'http://localhost:8083/user/selectUserByAccount',
                    data: {

                    }
                }).then(resp=>{
                    this.userName = resp.data.data.userName;
                });
            }
        }
    }
</script>

<style scoped>

</style>
