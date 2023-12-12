<template>
    <div>
        <div style="margin-bottom: 5px;">
            <el-input v-model="name" placeholder="请输入名字" suffix-icon="el-icon-search" style="width: 200px;"
                @keyup.enter.native="loadPost"></el-input>
            <el-select v-model="sex" filterable placeholder="请选择性别" style="margin-left: 5px;">
                <el-option
                        v-for="item in sexs"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>
            <el-button type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
            <el-button type="success" @click="resetParam">重置</el-button>
        </div>
        <el-table :data="tableData"
              :header-cell-style="{background:'#f2f5fc', color:'#555'}"
              border
            >
            <el-table-column prop="id" label="ID" width="60">
            </el-table-column>
            <el-table-column prop="no" label="账号" width="180">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="180">
            </el-table-column>
            <el-table-column prop="age" label="年龄" width="80">
            </el-table-column>
            <el-table-column prop="sex" label="性别" width="80">
                <template slot-scope="scope">
                    <el-tag
                            :type="scope.row.sex === 1 ? 'primary' : 'success'"
                            disable-transitions>{{scope.row.sex === 1 ? '男' : '女'}}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="roleId" label="角色" width="120">
                <template slot-scope="scope">
                    <el-tag
                            :type="scope.row.roleId === 0 ? 'danger' : scope.row.roleId === 1 ? 'primary' : 'success'"
                            disable-transitions>{{scope.row.roleId === 0 ? '超级管理员' :
                                                    scope.row.roleId === 1 ? '管理员' : '用户'}}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="phone" label="电话" width="180">
            </el-table-column>
            <el-table-column prop="operate" label="操作">
                <el-button size="small" type="success">编辑</el-button>
                <el-button size="small" type="danger">删除</el-button>
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
    </div>
</template>

<script>
    export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name: "Main",
        data() {
            return {
                tableData: [],
                pageSize:10,
                pageNum:1,
                total:0,
                name:'',
                sex:'',
                sexs:[
                    {
                        value: '1',
                        label: '男'
                    }, {
                        value: '0',
                        label: '女'
                    }
                ]
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
            loadGet(){
                this.$axios.get(this.$httpUrl+'/user/list').then(res=>res.data).then(res=>{
                    console.log(res)
                    if(res.code==200){
                        this.tableData=res.data
                    }else{
                        alert('获取数据失败')
                    }
                })
            },
            loadPost(){
                this.$axios.post(this.$httpUrl+'/user/pageC',{
                    pageSize:this.pageSize,
                    pageNum:this.pageNum,
                    param:{
                        name:this.name,
                        sex:this.sex
                    }
                }).then(res=>res.data).then(res=>{
                    console.log(res)
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
                this.sex=''
            }
        },
        beforeMount() {
            // this.loadGet();
            this.loadPost();
        }
    }
</script>

<style scoped>

</style>