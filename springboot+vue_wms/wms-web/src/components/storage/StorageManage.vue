<template>
    <div>
        <div style="margin-bottom: 5px;">
            <el-input v-model="name" clearable placeholder="请输入仓库名" suffix-icon="el-icon-search" style="width: 200px;"
                      @keyup.enter.native="loadPost"></el-input>
            <el-button type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
            <el-button type="success" @click="resetParam">重置</el-button>
            <el-button type="primary" style="margin-left: 5px;" @click="add">新增</el-button>
        </div>
        <el-table :data="tableData"
                  :header-cell-style="{background:'#f2f5fc', color:'#555'}"
                  border
                  style="width: 100%;margin-top: 10px"
        >
            <el-table-column prop="id" label="ID" width="60">
            </el-table-column>
            <el-table-column prop="name" label="仓库名" width="180">
            </el-table-column>
            <el-table-column prop="remark" label="备注">
            </el-table-column>
            <el-table-column prop="operate" label="操作" fixed="right" width="170">
                <template slot-scope="scope">
                    <el-button size="small" type="text" @click="mod(scope.row)">编辑</el-button>
                    <el-popconfirm title="确定删除吗？" style="margin-left: 5px" @confirm="del(scope.row.id)">
                        <el-button slot="reference" size="small" type="text">删除</el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[5, 10, 20, 30]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>
        <el-dialog
                title="提示"
                :visible.sync="centerDialogVisible"
                width="30%"
                center>
            <el-form ref="form" :rules="rules" :model="form" label-width="80px">
                <el-form-item label="仓库名" prop="name">
                    <el-col :span="20">
                        <el-input v-model="form.name"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="备注" prop="remark">
                    <el-col :span="20">
                        <el-input type="textarea" v-model="form.remark"></el-input>
                    </el-col>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="centerDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name: "StorageManage",
        data() {
            return {
                tableData: [],
                pageSize:10,
                pageNum:1,
                total:0,
                name:'',
                centerDialogVisible:false,
                form:{
                    id:'',
                    name:'',
                    remark:''
                },
                rules: {
                    name: [
                        {required: true, message: '请输入仓库名', trigger: 'blur'},
                    ],
                }
            }
        },
        methods:{
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.pageNum=1
                this.pageSize=val
                this.loadPost()
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.pageNum=val
                this.loadPost()
            },
            // loadGet(){
            //     this.$axios.get(this.$httpUrl+'/storage/page').then(res=>res.data).then(res=>{
            //         console.log(res)
            //         if(res.code==200){
            //             this.tableData=res.data
            //         }else{
            //             alert('获取数据失败')
            //         }
            //     })
            // },
            loadPost(){
                this.$axios.post(this.$httpUrl+'/storage/page',{
                    pageSize:this.pageSize,
                    pageNum:this.pageNum,
                    param:{
                        name:this.name,
                    }
                }).then(res=>res.data).then(res=>{
                    // console.log(res)
                    if(res.code==200){
                        this.tableData=res.data
                        this.total=res.total
                    }else{
                        alert('获取数据失败')
                    }
                })
            },
            resetParam(){
                this.name=''
            },
            add(){
                this.centerDialogVisible=true
                this.$nextTick(()=>{
                    this.resetForm();
                })
            },
            doSave(){
                this.$axios.post(this.$httpUrl+'/storage/add',this.form).then(res=>res.data).then(res=>{
                    // console.log(res)
                    if(res.code==200){
                        this.$message({
                            message: '操作成功！',
                            type: 'success'
                        });
                        this.centerDialogVisible=false
                        this.loadPost()
                        // this.resetForm()
                    }else{
                        this.$message({
                            message: '操作失败！',
                            type: 'error'
                        });
                    }
                })
            },
            save(){
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        if(this.form.id){
                            this.doMod();
                        }else {
                            this.doSave();
                        }
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm() {
                this.$refs.form.resetFields(); //复位到窗口的初始值
            },
            doMod(){
                this.$axios.put(this.$httpUrl+'/storage/update',this.form).then(res=>res.data).then(res=>{
                    // console.log(res)
                    if(res.code==200){
                        this.$message({
                            message: '操作成功！',
                            type: 'success'
                        });
                        this.centerDialogVisible=false
                        this.loadPost()
                        // this.resetForm()
                    }else{
                        this.$message({
                            message: '操作失败！',
                            type: 'error'
                        });
                    }
                })
            },
            mod(row){
                // console.log(row)
                this.centerDialogVisible=true
                this.$nextTick(()=>{
                    //赋值到表单
                    this.form.id = row.id
                    this.form.name = row.name
                    this.form.remark = row.remark
                })
            },
            del(id){
                // console.log(id)
                this.$axios.delete(this.$httpUrl+'/storage/del?id='+id).then(res=>res.data).then(res=>{
                    // console.log(res)
                    if(res.code==200){
                        this.$message({
                            message: '操作成功！',
                            type: 'success'
                        });
                        this.loadPost()
                    }else{
                        this.$message({
                            message: '操作失败！',
                            type: 'error'
                        });
                    }
                })
            }
        },
        beforeMount() {
            this.loadPost();
        }
    }
</script>

<style scoped>

</style>