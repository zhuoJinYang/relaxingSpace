<template>
  <a-layout>
    <!-- 头部 -->
    <a-layout-header class="header">
      <ForumHeader/>
    </a-layout-header>
    <!-- 内容 -->
    <a-layout-content style="padding: 8px;overflow-y: auto;overflow-x: hidden;">
      <div style="display: flex">
        <router-view v-slot="{ Component }">
          <keep-alive :include="includeList">
            <component :is="Component" />
          </keep-alive>
        </router-view>
      </div>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent} from 'vue'
import ForumHeader from './forum/layout/header.vue'
import {useKeepAliveStore} from "@/store/keepAlive";

export default defineComponent({
  components:{
    ForumHeader,
  },
  setup() {
    const keepAliveStore = useKeepAliveStore()
    const includeList = keepAliveStore.ForumIncludeList
    return {
      includeList
    }
  }
})
</script>

<style scoped lang="scss">
.header{
  background-color: #fff;
  padding: 0 24px;
}
</style>