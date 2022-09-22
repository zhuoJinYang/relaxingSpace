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
import {defineComponent, ref} from 'vue'
import { SearchOutlined } from '@ant-design/icons-vue'
import ForumHeader from './forum/layout/header.vue'

export default defineComponent({
  components:{
    ForumHeader,
    SearchOutlined
  },
  setup() {
    const value = ref<string>('');
    const hahah = () => {
      console.log('hahah')
    }
    return {
      value,
      hahah
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