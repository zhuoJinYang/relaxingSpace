<template>
  <a-layout>
    <a-layout-header class="header">
      <div class="header-left">
        <div>icon</div>
        <a-menu mode="horizontal" v-model:selected-keys="currentMenuIndex">
          <a-menu-item key="mainPage">主页</a-menu-item>
          <a-menu-item key="myBlog">我的博客</a-menu-item>
          <a-menu-item key="writeMessage">留言</a-menu-item>
        </a-menu>
      </div>
      <div class="header-search">
        <a-input v-model:value="value" placeholder="Basic usage" style="border-radius: 15px">
          <template #prefix>
            <search-outlined />
          </template>
        </a-input>
      </div>
      <div class="header-profile">
        <div>你的狗头</div>
      </div>
    </a-layout-header>
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

export default defineComponent({
  components:{
    SearchOutlined
  },
  setup() {
    const currentMenuIndex = ref<string[]>(['mainPage']);
    const hahah = () => {
      console.log('hahah')
    }
    return {
      currentMenuIndex,
      hahah
    }
  }
})
</script>

<style scoped lang="scss">
.header{
  background-color: #fff;
  padding: 0 24px;
  display: flex;
  .header-left{
    width: 40%;
    display: flex;
    .inner-content{
      font-size: 14px;
      padding: 0 20px;
      cursor: pointer;
    }
    .inner-content-active{
      border-bottom: 2px solid #747bff;
    }
  }
  .header-search{
    width: 40%;
  }
  .header-profile{
    display: flex;
    padding: 0 20px;
    margin-left: auto;
  }
}
</style>