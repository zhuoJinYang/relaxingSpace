/// <reference types="vite/client" />
// 告诉ts .vue是什么类型
declare module '*.vue' {
  import type { DefineComponent } from 'vue'
  const component: DefineComponent<{}, {}, any>
  export default component
}
