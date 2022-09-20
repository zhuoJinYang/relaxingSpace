import {RouteRecordRaw} from "vue-router";
import {forumModuleRoutes} from "@/router/route-moudule-forum";

// 模块路由
export const moduleRoutes :  Array<RouteRecordRaw> = [
    {
        path:"/forum",
        name:"/forum",
        component: () => import('@/views/modules/forum.vue'),
        children:forumModuleRoutes
    }
]