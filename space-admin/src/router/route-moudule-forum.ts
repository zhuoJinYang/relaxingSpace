import {RouteRecordRaw} from "vue-router";

// 模块路由
export const forumModuleRoutes :  Array<RouteRecordRaw> = [
    {
        path:"mainPage",
        component: () => import('@/views/modules/forum/MainPage.vue')
    },
    {
        path:"myBlog",
        component: () => import('@/views/modules/forum/MyBlog.vue')
    },
    {
        path:"writeMessage",
        component: () => import('@/views/modules/forum/WriteMessage.vue')
    },
    {
        path:"editBlog",
        component: () => import('@/views/modules/forum/EditBlog.vue')
    },
    {
        path:"blogDetail/:blogId",
        component: () => import('@/views/modules/forum/BlogDetail.vue')
    }
]
