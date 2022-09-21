import {RouteRecordRaw} from "vue-router";
import {forumModuleRoutes} from "@/router/route-moudule-forum";
import {pokemonModuleRoutes} from "@/router/route-module-pokemon";

// 模块路由
export const moduleRoutes :  Array<RouteRecordRaw> = [
    {
        path:"/forum",
        name:"/forum",
        component: () => import('@/views/modules/forum.vue'),
        children:forumModuleRoutes
    },
    {
        path:"/pokemon",
        name:"/pokemon",
        component: () => import('@/views/modules/pokemon.vue'),
        children:pokemonModuleRoutes
    }
]