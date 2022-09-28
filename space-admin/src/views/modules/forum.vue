<template>
  <a-layout>
    <!-- 头部 -->
    <a-layout-header class="header">
      <ForumHeader/>
    </a-layout-header>
    <!-- 内容 -->
    <a-layout-content>
      <div style="display: flex">
        <router-view v-slot="{ Component }">
          <keep-alive>
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

export default defineComponent({
  components:{
    ForumHeader,
  },
  setup() {
    return {
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