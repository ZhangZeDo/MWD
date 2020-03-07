<template>
    <div>
        <Navbar/>
        <router-view/>
    </div>
</template>

<script>
    import Navbar from "../components/Navbar";
    // import HomePage from "../views/homePage/index";
    // import PersonalCenter from  "../views/personalCenter/index";
    export default {
        name: "index",
        components: {
            Navbar,
            // HomePage,
            // PersonalCenter,
        },
        data(){
            return{
                modelName:'HomePage',
            }
        },
        created(){
          this.modelName = this.$route.query.modelName;
        },
        methods:{

        }
    }
</script>

<style scoped>

</style>
