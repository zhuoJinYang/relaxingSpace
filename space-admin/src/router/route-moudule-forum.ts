import {RouteRecordRaw} from "vue-router";

// 模块路由
export const forumModuleRoutes :  Array<RouteRecordRaw> = [
    {
        path:"/forum/mainPage",
        component: () => import('@/views/modules/forum/MainPage.vue')
    },
    {
        path:"/forum/myBlog",
        component: () => import('@/views/modules/forum/MyBlog.vue')
    },
    {
        path:"/forum/writeMessage",
        component: () => import('@/views/modules/forum/WriteMessage.vue')
    }
]