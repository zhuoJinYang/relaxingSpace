import {createRouter, createWebHistory} from 'vue-router'
import {basicRoutes} from "./route-basic";
import {moduleRoutes} from "./route-module";

// 配置路由元信息
declare module 'vue-router'{
  interface RouteMeta {
    title?: string
  }
}

const routes = [...basicRoutes,...moduleRoutes]

const router =  createRouter({
  history:createWebHistory(),
  routes
})

export default router