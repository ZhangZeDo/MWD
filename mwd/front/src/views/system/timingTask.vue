<template>
    <div>
        <div class="card-header" style="margin-top: 20px;margin-left: 100px">
            <h3>任务列表</h3>
        </div>
        <el-card style="margin:20px 100px;">
            <div slot="header" style="height: 30px">
                <div style="float: left;margin-right: 10px;">
                    <label style="">任务名称:</label>
                </div>
                <div style="float: left;margin-right: 10px">
                    <el-input size="small" placeholder="搜索任务名称" v-model="queryForm.searchInput" clearable @change="queryData()">
                        <i slot="prefix" class="el-input__icon el-icon-search"></i>
                    </el-input>
                </div>
                <div style="float: right;margin-right: 10px">
                    <el-button size="small" class="el-icon-refresh" @click="queryData()"></el-button>
                    <el-button @click="showJobDialog"  type="primary" size="small">新增</el-button>
                </div>
            </div>
            <div>
                <el-table :data="jobList" style="width: 100%" stripe>
                    <el-table-column align="center" prop="jobName" label="任务名称" style="width: 10%"></el-table-column>
                    <el-table-column align="center" prop="cronExpression" label="时间表达式" style="width: 10%"></el-table-column>
                    <el-table-column align="center" label="状态" style="width: 5%">
                        <template slot-scope="scope">
                            <el-button v-if="scope.row.status == 0" size="mini" type="success"
                                       @click="updateJobStatus(scope.row)">启用
                            </el-button>
                            <el-button v-if="scope.row.status == 1" size="mini" type="danger"
                                       @click="updateJobStatus(scope.row)">禁用
                            </el-button>
                        </template>
                    </el-table-column>
                    <el-table-column align="center" prop="methodName" label="方法名称" style="width: 10%"></el-table-column>
                    <el-table-column align="center" prop="beanClazz" label="beanId" style="width: 10%"></el-table-column>
                    <el-table-column align="center" prop="params" label="参数" style="width: 5%">
                        <template slot-scope="scope">
                            <el-popover placement="bottom" width="800" trigger="click">
                                <div>
                                    {{scope.row.params}}
                                </div>
                                <el-button type="text" slot="reference">{{ shortCut(scope.row.params,10) }}</el-button>
                            </el-popover>
                        </template>
                    </el-table-column>

                    <el-table-column align="center" label="操作" style="width: 40%">
                        <template slot-scope="scope">
                            <el-button  @click="showEditJobDialog(scope.row)"  type="primary" size="mini">编辑</el-button>
                            <el-button  @click="runJob(scope.row)"  type="primary" size="mini">立即执行</el-button>
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
                            layout="total,sizes, prev ,pager, next, jumper"
                            :total="total">
                    </el-pagination>
                </div>
            </div>
            <el-dialog
                    title="新建定时任务"
                    :visible.sync="createJobDialog"
                    width="50%"
                    center>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="任务名称" prop="jobName">
                        <el-input v-model="ruleForm.jobName" placeholder="请输入任务名称"></el-input>
                    </el-form-item>
                    <el-form-item label="任务组" prop="jobGroup">
                        <el-input v-model="ruleForm.jobGroup" placeholder="请输入任务组"></el-input>
                    </el-form-item>
                    <el-form-item label="时间表达式" prop="cronExpression">
                        <el-input v-model="ruleForm.cronExpression" placeholder="请输入时间表达式"></el-input>
                    </el-form-item>
                    <el-form-item label="方法名称" prop="methodName">
                        <el-input v-model="ruleForm.methodName" placeholder="请输入方法名称"></el-input>
                    </el-form-item>
                    <el-form-item label="beanId" prop="beanId">
                        <el-input v-model="ruleForm.beanClazz" placeholder="请输入beanId"></el-input>
                    </el-form-item>
                    <el-form-item label="参数" prop="params">
                        <el-input type="textarea" :rows="6" v-model="ruleForm.params" placeholder="请输入参数，以json格式"></el-input>
                    </el-form-item>
                    <el-form-item label="备注" prop="remark">
                        <el-input type="textarea" :rows="4" v-model="ruleForm.remark" placeholder="请输入备注"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                <el-button @click="createJobDialog = false">取 消</el-button>
                <el-button type="primary" @click="submitCreateForm">确 定</el-button>
            </span>
            </el-dialog>

            <el-dialog
                    title="编辑定时任务"
                    :visible.sync="editJobDialog"
                    width="50%"
                    center>
                <el-form :model="editForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="任务名称" prop="jobName">
                        <el-input v-model="editForm.jobName" placeholder="请输入任务名称"></el-input>
                    </el-form-item>
                    <el-form-item label="任务组" prop="jobGroup">
                        <el-input v-model="editForm.jobGroup" placeholder="请输入任务组"></el-input>
                    </el-form-item>
                    <el-form-item label="时间表达式" prop="cronExpression">
                        <el-input v-model="editForm.cronExpression" placeholder="请输入时间表达式"></el-input>
                    </el-form-item>
                    <el-form-item label="方法名称" prop="methodName">
                        <el-input v-model="editForm.methodName" placeholder="请输入方法名称"></el-input>
                    </el-form-item>
                    <el-form-item label="beanId" prop="beanId">
                        <el-input v-model="editForm.beanClazz" placeholder="请输入beanId"></el-input>
                    </el-form-item>
                    <el-form-item label="参数" prop="params">
                        <el-input type="textarea" :rows="6" v-model="editForm.params" placeholder="请输入参数，以json格式"></el-input>
                    </el-form-item>
                    <el-form-item label="备注" prop="remark">
                        <el-input type="textarea" :rows="4" v-model="editForm.remark" placeholder="请输入备注"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                <el-button @click="editJobDialog = false">取 消</el-button>
                <el-button type="primary" @click="submitEditForm">确 定</el-button>
            </span>
            </el-dialog>
        </el-card>
    </div>
</template>

<script>
    export default {
        name: "timingTask",
        data(){
            return{
                createJobDialog:false,
                editJobDialog:false,
                queryForm:{
                    page:1,
                    pageSize:5,
                    searchInput:'',
                },
                total:0,
                jobList:[],
                ruleForm:{
                    id:'',
                    jobName:'',
                    jobGroup:'',
                    cronExpression:'',
                    methodName:'',
                    beanClazz:'',
                    params:'',
                    remark:''
                },
                editForm:{
                    id:'',
                    jobName:'',
                    jobGroup:'',
                    cronExpression:'',
                    methodName:'',
                    beanClazz:'',
                    params:'',
                    remark:''
                },
                rules:{
                    jobName: [
                        { required: true, message: '请输入任务名称', trigger: 'blur' },
                        { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
                    ],
                    jobGroup: [
                        { required: true, message: '请输入任务组', trigger: 'blur' },
                        { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
                    ],
                    cronExpression: [
                        { required: true, message: '请输入时间表达式', trigger: 'blur' },
                        { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
                    ],
                    methodName: [
                        { required: true, message: '请输入方法名称', trigger: 'blur' },
                        { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
                    ],
                    beanClazz: [
                        { required: true, message: '请输入beanId', trigger: 'blur' },
                        { min: 3, max: 100, message: '长度在 3 到 100 个字符', trigger: 'blur' }
                    ],
                }
            }
        },
        created(){
            this.queryData()
        },
        methods:{
            queryData(){
                this.$axios.post('/job/queryJobList',{
                    page:this.queryForm.page,
                    pageSize:this.queryForm.pageSize,
                    searchInput: this.queryForm.searchInput
                }).then(resp=>{
                    if (resp.code == 200) {
                        this.jobList = resp.data.items
                        this.total = resp.data.total
                    }
                });
            },
            updateJobStatus(row){
                this.$axios.post('/job/updateJobStatus',{
                    id:row.id,
                    status:row.status,
                }).then(resp=>{
                    if (resp.code == 200){
                        this.$message.success("操作成功")
                    }
                    this.queryData()
                })
            },
            shortCut(src,size){
                if (src && src.length > size){
                    return src.substring(0,size) + "...";
                }else {
                    return src;
                }
            },
            showJobDialog(){
                this.createJobDialog = true;
            },
            submitCreateForm(){
                this.$axios.post('/job/addJob',{
                    jobName:this.ruleForm.jobName,
                    jobGroup:this.ruleForm.jobGroup,
                    cronExpression:this.ruleForm.cronExpression,
                    methodName:this.ruleForm.methodName,
                    beanClazz:this.ruleForm.beanClazz,
                    params:this.ruleForm.params,
                    remark:this.ruleForm.remark,
                }).then(resp=>{
                    if (resp.code == 200){
                        this.$message.success("添加成功")
                        this.queryData()
                        this.createJobDialog = false
                    }
                    this.queryData()
                })
            },
            showEditJobDialog(row){
                this.editForm = row
                this.editJobDialog = true
            },
            submitEditForm(){
                this.$axios.post('/job/editJob',{
                    id:this.editForm.id,
                    jobName:this.editForm.jobName,
                    jobGroup:this.editForm.jobGroup,
                    status:this.editForm.status,
                    cronExpression:this.editForm.cronExpression,
                    methodName:this.editForm.methodName,
                    beanClazz:this.editForm.beanClazz,
                    params:this.editForm.params,
                    remark:this.editForm.remark,
                }).then(resp=>{
                    if (resp.code == 200){
                        this.$message.success("编辑成功")
                        this.queryData()
                        this.editJobDialog = false
                    }
                    this.queryData()
                })
            },
            handleSizeChange(val) {
                this.queryForm.pageSize = val;
                this.queryData()
            },
            handleCurrentChange(val) {
                this.queryForm.page = val;
                this.queryData()
            },
            runJob(row){
                this.$axios.post('/job/runJob',{
                    jobName:row.jobName,
                    jobGroup:row.jobGroup,
                }).then(resp=>{
                    if (resp.code == 200){
                        this.$message.success("操作成功，后台正在执行")
                    }
                })
            },
        }
    }
</script>

<style scoped>

</style>
