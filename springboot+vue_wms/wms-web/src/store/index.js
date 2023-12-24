import vue from 'vue'
import Vuex from 'vuex'
import router from "../router";
import {resetRouter} from "../router"
import createPersistedState from 'vuex-persistedstate'

vue.use(Vuex)

function addNewRoute(menuList) {
    // console.log(menuList)
    let routes = router.options.routes
    // console.log(routes)
    routes.forEach(routeItem=>{
        if(routeItem.path=="/index"){
            menuList.forEach(menu=>{
                let childRoute =  {
                    path:'/'+menu.menuclick,
                    name:menu.menuname,
                    meta:{
                        title:menu.menuname
                    },
                    component:()=>import('../components/'+menu.menucomponent)
                }

                routeItem.children.push(childRoute)
            })
        }
    })

    resetRouter()
    router.addRoutes(routes)
}

export default new Vuex.Store({
    state: {
        menu: []
    },
    mutations: {
        setMenu(state,menuList) {
            state.menu = menuList
            // 添加路由
            addNewRoute(menuList)
        },
        setRouter(state,menuList) {
            addNewRoute(menuList) // 添加路由
        }
    },
    getters: {
        getMenu(state) {
            return state.menu
        }
    },
    plugins: [createPersistedState()]
})
