<template>
    <div style="display: flex;line-height: 60px;">
        <div style="margin-top: 8px;">
            <i :class="icon" style="font-size: 20px;cursor: pointer;" @click="collapse"></i>
        </div>
        <div style="flex: 1;text-align: center;font-size: 34px;">
            <span>欢迎来到仓库管理系统</span>
        </div>
        <el-dropdown>
            <span>王小虎</span>
            <i class="el-icon-arrow-down" style="margin-left: 5px;"></i>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
                <el-dropdown-item @click.native="logOut">退出登录</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
    </div>
</template>

<script>
    export default {
        name: "Header",
        props:{
            icon:String
        },
        methods:{
            toUser(){
                console.log('to_user')
            },
            logOut(){
                console.log('log_out')
            },
            collapse(){
                this.$emit('doCollapse')
            }
        }
    }
</script>

<style scoped>

</style>