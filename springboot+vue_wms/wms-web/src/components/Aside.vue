<template>
    <el-menu
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            :default-active="$route.path"
            :collapse="isCollapse"
            :collapse-transition="false"
            router
            style="height: 100%">

        <el-menu-item index="/home">
            <i class="el-icon-s-home"></i>
            <span slot="title">首页</span>
        </el-menu-item>

        <el-menu-item :index="'/'+item.menuclick" v-for="(item,i) in menu" :key="i">
            <i :class="item.menuicon"></i>
            <span slot="title">{{item.menuname}}</span>
        </el-menu-item>

    </el-menu>
</template>

<script>
    export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name: "Aside",
        data(){
            return {
                // isCollapse:false
                /*menu:[
                    {
                        menuClick:'admin',
                        menuName:'管理员管理',
                        menuIcon:'el-icon-s-custom'
                    },
                    {
                        menuClick:'user',
                        menuName:'用户管理',
                        menuIcon:'el-icon-user-solid'
                    }
                ]*/
            }
        },
        computed:{
            "menu":{
                get(){
                    return this.$store.state.menu
                }
            }
        },
        props:{
            isCollapse: Boolean
        }
    }
</script>

<style scoped>

</style>