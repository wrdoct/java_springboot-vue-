import VueRouter from 'vue-router'
import Login from "@/components/Login";

const routes = [
    {
        path:'/',
        name:'login',
        // component:()=>import('../components/Login')
        component:Login
    },
    {
        path:'/index',
        name:'index',
        component:()=>import('../components/Index'),
        children:[
            {
                path:'/home',
                name:'home',
                meta:{
                    title:'首页'
                },
                component:()=>import('../components/Home'),
            },
            /*{
                path:'/admin',
                name:'admin',
                meta:{
                    title:'管理员管理'
                },
                component:()=>import('../components/admin/AdminManage.vue'),
            },
            {
                path:'/user',
                name:'user',
                meta:{
                    title:'用户管理'
                },
                component:()=>import('../components/user/UserManage.vue'),
            },*/
        ]
    }
]

const router = new VueRouter({
    mode:'history',  // 去掉url中的#
    routes:routes
})

export function resetRouter() {
    router.matcher = new VueRouter({
        mode:'history',
        routes:[]
    }).matcher
}

// 防止连续点击多次路由报错
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return VueRouterPush.call(this, location).catch(err => err)
}
const VueRouterReplace = VueRouter.prototype.replace
VueRouter.prototype.replace = function push(location) {
    return VueRouterReplace.call(this, location).catch(err => err)
}

export default router;