<template>
    <div>
        <div style="margin-bottom: 5px;">
            <el-input v-model="name" clearable placeholder="请输入物品名" suffix-icon="el-icon-search" style="width: 200px;"
                      @keyup.enter.native="loadPost"></el-input>
            <el-select v-model="storage" placeholder="请选择仓库" style="margin-left: 5px">
                <el-option
                        v-for="item in storageData"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id">
                </el-option>
            </el-select>
            <el-select v-model="goodstype" placeholder="请选择分类" style="margin-left: 5px">
                <el-option
                        v-for="item in goodstypeData"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id">
                </el-option>
            </el-select>
            <el-button type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
            <el-button type="success" @click="resetParam">重置</el-button>
        </div>
        <el-table :data="tableData"
                  :header-cell-style="{background:'#f2f5fc', color:'#555'}"
                  border
                  style="width: 100%;margin-top: 10px"
        >
            <el-table-column prop="id" label="ID" width="60">
            </el-table-column>
            <el-table-column prop="goodsname" label="物品名" width="100">
            </el-table-column>
            <el-table-column prop="storagename" label="仓库" width="100">
            </el-table-column>
            <el-table-column prop="goodstypename" label="分类" width="100">
            </el-table-column>
<!--            <el-table-column prop="adminId" label="操作人" width="70">-->
            <el-table-column prop="adminname" label="操作人" width="100">
            </el-table-column>
<!--            <el-table-column prop="userid" label="申请人" width="70">-->
            <el-table-column prop="username" label="申请人" width="100">
            </el-table-column>
            <el-table-column prop="count" label="数量" width="100">
            </el-table-column>
            <el-table-column prop="createtime" label="操作时间" width="180">
            </el-table-column>
            <el-table-column prop="remark" label="备注">
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
        name: "RecordManage",
        data() {
            return {
                tableData: [],
                pageSize:10,
                pageNum:1,
                total:0,
                name:'', // 实际上是物品名
                centerDialogVisible:false,
                form:{
                    id:'',
                    name:'',
                    storage:'',
                    goodstype:'',
                    count:'',
                    remark:''
                },
                storageData:[],
                goodstypeData:[],
                storage:'',  // 实际上是id——选择仓库时的:value值
                goodstype:'' // 实际上是id——选择分类时的:value值
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
            loadPost(){
                this.$axios.post(this.$httpUrl+'/record/page',{
                    pageSize:this.pageSize,
                    pageNum:this.pageNum,
                    param:{
                        name:this.name,
                        storage:this.storage+'', // +''是为了确保是字符型（Integer转String）
                        goodstype:this.goodstype+'',
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
                this.storage=''
                this.goodstype=''
            },
            loadStorage(){
                this.$axios.get(this.$httpUrl+'/storage/list').then(res=>res.data).then(res=>{
                    // console.log(res)
                    if(res.code==200){
                        this.storageData=res.data
                    }else{
                        alert('获取仓库数据失败')
                    }
                })
            },
            formatStorage(row){
                let tmp = this.storageData.find(item=>{
                    return item.id==row.storage;
                });
                return tmp && tmp.name;
            },
            loadGoodstype(){
                this.$axios.get(this.$httpUrl+'/goodstype/list').then(res=>res.data).then(res=>{
                    // console.log(res)
                    if(res.code==200){
                        this.goodstypeData=res.data
                    }else{
                        alert('获取物品类型数据失败')
                    }
                })
            },
            formatGoodstype(row){
                let obj = this.goodstypeData ?.find(item => {
                    return item ?.id == row.goodstype;
                });
                return obj ?.name;
            },
        },
        beforeMount() {
            this.loadStorage()
            this.loadGoodstype()
            this.loadPost()
        }
    }
</script>

<style scoped>

</style>