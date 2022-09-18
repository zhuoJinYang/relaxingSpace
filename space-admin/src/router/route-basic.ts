import {RouteRecordRaw} from "vue-router";

// 基础路由
export const basicRoutes :  Array<RouteRecordRaw> = [
    {
        path:"/",
        component: () => import('@/views/system/login.vue')
    },
    {
        path:"/module",
        component: () => import('@/views/system/module.vue')
    }
]