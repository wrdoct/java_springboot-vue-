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
        component:()=>import('../components/Index')
    }
]

const router = new VueRouter({
    model:'history',
    routes:routes
})

export default router;