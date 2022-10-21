import {RouteRecordRaw} from "vue-router";

// 模块路由
export const pokemonModuleRoutes :  Array<RouteRecordRaw> = [
    {
        path:"pokeDex",
        component: () => import('@/views/modules/pokemon/PokeDex.vue')
    },
    {
        path:"myCollection",
        component: () => import('@/views/modules/pokemon/MyCollection.vue')
    },
    {
        path:"arrange",
        component: () => import('@/views/modules/pokemon/Arrange.vue')
    }
]
