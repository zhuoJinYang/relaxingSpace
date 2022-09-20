import {RouteRecordRaw} from "vue-router";

// 模块路由
export const forumModuleRoutes :  Array<RouteRecordRaw> = [
    {
        path:"/forum/mainPage",
        component: () => import('@/components/forum/MainPage.vue')
    },
    {
        path:"/forum/myBlog",
        component: () => import('@/components/forum/MyBlog.vue')
    },
    {
        path:"/forum/writeMessage",
        component: () => import('@/components/forum/WriteMessage.vue')
    }
]
