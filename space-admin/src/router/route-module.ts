import {RouteRecordRaw} from "vue-router";

// 模块路由
export const moduleRoutes :  Array<RouteRecordRaw> = [
    {
        path:"/forum",
        component: () => import('@/views/modules/forum.vue')
    }
]