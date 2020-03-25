<template>
    <div >
        <div style="width: 1000px; height: 300px;padding-top: 20px; margin: 0 auto">
            <el-carousel trigger="click">
                <el-carousel-item v-for="item in 4" :key="item">
                    <img style="width: 1000px; height: 300px;" src="http://localhost:8083/cover/20200324/a.png"/>
                </el-carousel-item>
            </el-carousel>
        </div>
        <div style="width: 1022px; height: 200px;padding-top: 20px; margin: 0 auto">
            <img style="height: 200px;width: 326px; padding-left: 11px" src="http://localhost:8083/cover/20200324/a.png"/>
            <img style="height: 200px;width: 326px; padding-left: 11px" src="http://localhost:8083/cover/20200324/a.png"/>
            <img style="height: 200px;width: 326px; padding-left: 11px" src="http://localhost:8083/cover/20200324/a.png"/>
        </div>
        <div style="width: 1022px; height: 200px;padding-top: 30px; margin: 0 auto">
            <div>
                <el-tabs v-model="activeName" @tab-click="handleClick" stretch="true" style="width: 200px;margin: auto">
                    <el-tab-pane  label="首页推荐" name="first"></el-tab-pane>
                    <el-tab-pane label="最新发布" name="second"></el-tab-pane>
                </el-tabs>
            </div>
            <div style="padding-top: 10px">
                <img style="height: 200px;width: 326px; padding-left: 11px" src="http://localhost:8083/cover/20200324/a.png"/>
                <img style="height: 200px;width: 326px; padding-left: 11px" src="http://localhost:8083/cover/20200324/a.png"/>
                <img style="height: 200px;width: 326px; padding-left: 11px" src="http://localhost:8083/cover/20200324/a.png"/>
            </div>
            <!--<img style="height: 200px;width: 326px; padding-left: 11px" src="http://localhost:8083/cover/20200324/a.png"/>
            <img style="height: 200px;width: 326px; padding-left: 11px" src="http://localhost:8083/cover/20200324/a.png"/>
            <img style="height: 200px;width: 326px; padding-left: 11px" src="http://localhost:8083/cover/20200324/a.png"/>-->
        </div>
    </div>
</template>

<script>
    export default {
        name: "home"
    }
</script>

<style lang="css" scoped>
    .el-carousel__item h3 {
        color: #475669;
        font-size: 18px;
        opacity: 0.75;
        line-height: 300px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }

    .img{
        background-image: ;
    }
</style>
