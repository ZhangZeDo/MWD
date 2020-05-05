<template>
    <div >
        <div style="width: 1382px;padding-top: 30px; margin: 0 auto">
            <div style="padding-top: 5px;padding-left: 30px">
                <el-row>
                    <el-col :span="8" v-for="index in newsList" :key="index.id" >
                        <el-card style="padding-top:5px; margin-top:15px;width: 426px;height: 350px;float: left">
                            <div slot="header">
                                <span>{{index.title}}</span>
                                <span style="font-size: x-small;float: right;margin-top: 20px">{{parseTime(index.createDatetime)}}</span>
                            </div>
                            <div style="float: left;width: 380px;height:240px;padding-left: 5px;">
                                <el-input type="textarea" :rows="10" v-model="index.content" readonly=""></el-input>
                            </div>
                            <div>
                                <el-link v-if="index.duration" @click="downLoadFile(index)" type="primary" style="float: right">附件下载</el-link>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
                <div align="center" style="padding-top: 20px">
                    <div class="block">
                        <el-pagination
                                @size-change="handleSizeChange"
                                @current-change="handleCurrentChange"
                                :current-page="queryForm.page"
                                :page-size="queryForm.pageSize"
                                :page-sizes="[6,9,12,15]"
                                layout="total, sizes,prev, pager, next, jumper"
                                :total="total">
                        </el-pagination>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "news",
        data(){
            return {
                queryForm:{
                    pageSize:6,
                    page:1,
                },
                total:0,
                newsList:[],
            }
        },
        created() {
            this.queryData();
        },
        methods:{
            parseTime(dateObj) {
                var date = new Date(dateObj);
                var year = date.getFullYear();
                var month = ("0" + (date.getMonth() + 1)).slice(-2);
                var day = ("0" + date.getDate()).slice(-2);
                var hour = ("0" + date.getHours()).slice(-2);
                var min = ("0" + date.getMinutes()).slice(-2);
                var second = ("0" + date.getSeconds()).slice(-2);
                return year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + second;
            },
            queryData(){
                this.$axios.post('/news/queryNewsList',{
                    pageSize:this.queryForm.pageSize,
                    page:this.queryForm.page
                }).then(resp=>{
                    if (resp.code == 200) {
                        this.newsList = resp.data.items;
                        this.total = resp.data.total;
                    }
                });
            },
            handleSizeChange(val) {
                this.queryForm.pageSize = val;
                this.queryData()
            },
            handleCurrentChange(val) {
                this.queryForm.page = val;
                this.queryData()
            },
            downLoadFile(row){
                var fileUrl = row.duration;
                var fileName = fileUrl.substring(fileUrl.lastIndexOf("/")+1,fileUrl.length);
                this.$axios.postWithBlob('/news/downloadFile',{
                    duration:row.duration,
                }).then(resp=>{
                    window.console.info(resp)
                    const content = resp;
                    const blob = new Blob([content])
                    if ('download' in document.createElement('a')) { // 非IE下载
                        const elink = document.createElement('a')
                        elink.download = fileName
                        elink.style.display = 'none'
                        elink.href = URL.createObjectURL(blob)
                        document.body.appendChild(elink)
                        elink.click()
                        URL.revokeObjectURL(elink.href) // 释放URL 对象
                        document.body.removeChild(elink)
                    } else { // IE10+下载
                        navigator.msSaveBlob(blob, fileName)
                    }
                })

            }
        }

    }
</script>

<style scoped>

</style>
