import {RouteRecordRaw} from "vue-router";

// 模块路由
export const pokemonModuleRoutes :  Array<RouteRecordRaw> = [
    {
        path:"/pokemon/pokeDex",
        component: () => import('@/views/modules/pokemon/PokeDex.vue')
    },
    {
        path:"/pokemon/myCollection",
        component: () => import('@/views/modules/pokemon/MyCollection.vue')
    },
    {
        path:"/pokemon/arrange",
        component: () => import('@/views/modules/pokemon/Arrange.vue')
    }
]
