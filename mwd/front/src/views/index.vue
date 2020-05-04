<template>
    <div>
        <div >
            <Navbar/>
        </div>
        <div>
            <router-view/>
        </div>

    </div>
</template>

<script>
    import Navbar from "../components/Navbar";
    export default {
        name: "index",
        components: {
            Navbar,
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
