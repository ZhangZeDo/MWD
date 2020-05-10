<template>
    <div>
        <div class="card-header" style="margin-top: 20px;margin-left: 100px">
            <h3>作品类型管理</h3>
        </div>
        <el-card style="margin:20px 100px;">
            <div slot="header" style="height: 30px">
                <div style="float: left;margin-right: 10px;">
                    <label style="">类型名称:</label>
                </div>
                <div style="float: left;margin-right: 10px">
                    <el-input size="small" placeholder="搜索作品类型名称" v-model="queryForm.searchInput" clearable @change="queryData()">
                        <i slot="prefix" class="el-input__icon el-icon-search"></i>
                    </el-input>
                </div>
                <div style="float: left;margin-right: 10px;padding-left: 20px">
                    <label style="">类型状态:</label>
                </div>
                <div style="float: left;margin-right: 10px">
                    <el-select size="small" v-model="queryForm.status" clearable placeholder="请选择作品类型状态" @change="queryData()">
                        <el-option value="1" label="有效">有效</el-option>
                        <el-option value="0" label="无效">无效</el-option>
                    </el-select>
                </div>
                <div style="float: right;margin-right: 10px">
                   <el-button size="small" type="primary" @click="createMediaType">新增</el-button>
                </div>
            </div>
            <div>
                <el-table :data="meidaTypeList" style="width: 100%" stripe>
                    <el-table-column prop="typeName" label="类型名称" style="width: 15%"></el-table-column>
                    <el-table-column label="状态" style="width: 15%">
                        <template slot-scope="scope">
                            {{parseStatus(scope.row.status) }}
                        </template>
                    </el-table-column>
                    <el-table-column  label="创建时间" style="width: 15%" >
                        <template slot-scope="scope">
                            {{parseTime(scope.row.createDatetime) }}
                        </template>
                    </el-table-column>
                    <el-table-column  label="更新时间" style="width: 15%" >
                        <template slot-scope="scope">
                            {{parseTime(scope.row.updateDatetime) }}
                        </template>
                    </el-table-column>
                    <el-table-column prop="updateBy" label="更新人" style="width: 15%"></el-table-column>
                    <el-table-column label="操作" style="width: 10%">
                        <template slot-scope="scope">
                            <el-button v-if="scope.row.status == 0" @click="changeMediaTypeStatus(scope.row,'启用')"  type="primary" size="mini">启用</el-button>
                            <el-button v-else @click="changeMediaTypeStatus(scope.row,'禁用')"  type="danger" size="mini">禁用</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div style="padding-top: 20px">
                <div class="block">
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page="queryForm.page"
                            :page-size="queryForm.pageSize"
                            :page-sizes="[5,10,15,20]"
                            layout="total,sizes ,prev, pager, next, jumper"
                            :total="total">
                    </el-pagination>
                </div>
            </div>
        </el-card>
        <el-dialog
                title="新增作品类型"
                :visible.sync="createTypeDialog"
                width="40%"
                center>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="110px" class="demo-ruleForm">
                <el-form-item label="作品类型名称" prop="typeName">
                    <el-input type="text" v-model="ruleForm.typeName" placeholder="请输入作品类型名称"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="createTypeDialog = false">取 消</el-button>
                <el-button type="primary" @click="submitCreateTypeDialog">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>

    export default {
        name: "mediaTypeManage",
        data(){
            return{
                meidaTypeList:[],
                queryForm:{
                    searchInput:'',
                    status:'',
                    pageSize:5,
                    page:1,
                },
                total:0,
                createTypeDialog:false,
                ruleForm:{
                    typeName:'',
                },
                rules:{
                    typeName: [
                        {required: true, message: '请输入作品类型名称', trigger: 'blue' },
                        {min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                }
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
            parseStatus(status){
                if (status == 0){
                    return "无效";
                }else{
                    return "有效"
                }
            },
            queryData(){
                this.$axios.post('/mediaType/queryMediaTypeList',{
                    searchInput:this.queryForm.searchInput,
                    status:this.queryForm.status,
                    pageSize:this.queryForm.pageSize,
                    page:this.queryForm.page
                }).then(resp=>{
                    if (resp.code == 200) {
                        this.meidaTypeList = resp.data.items;
                        this.total = resp.data.total;
                    }
                });
            },
            changeMediaTypeStatus(row,operate){
                this.$confirm('此操作将'+operate+'该作品类型, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios.post('/mediaType/changeMediaTypeStatus',{
                        id:row.id,
                        status: row.status
                    }).then(resp=>{
                        if (resp.code == 200){
                            this.$message.success("操作成功");
                            this.queryData();
                        }
                    })
                }).catch(() => {

                });
            },
            createMediaType(){
                this.createTypeDialog = true;
            },
            submitCreateTypeDialog(){
              this.$axios.post('/mediaType/addMediaType',{
                  typeName:this.ruleForm.typeName
              }).then(resp=>{
                  if (resp.code == 200){
                      this.$message.success("创建成功");
                      this.createTypeDialog = false;
                      this.queryData()
                  }
              })
            },
            handleSizeChange(val) {
                this.queryForm.pageSize = val
                this.queryData()
            },
            handleCurrentChange(val) {
                this.queryForm.page = val
                this.queryData()
            },

        }
    }
</script>

<style scoped>

</style>
