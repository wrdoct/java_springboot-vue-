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
            {
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
            },
        ]
    }
]

const router = new VueRouter({
    model:'history',
    routes:routes
})

const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(to) {
    return VueRouterPush.call(this, to).catch(err => err)
}

export default router;