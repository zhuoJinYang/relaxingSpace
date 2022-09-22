<template>
  <a-layout class="layout">
    <!-- 侧边 -->
    <a-layout-sider width="256" v-model:collapsed="collapsed" :trigger="null" collapsible>
      <PokemonMenu :collapsed="collapsed"/>
    </a-layout-sider>

    <!-- 内容 -->
    <a-layout>
      <!-- 头部 -->
      <a-layout-header class="header">
        <PokemonHeader v-model:collapsed="collapsed"/>
      </a-layout-header>
      <!-- 内容 -->
      <a-layout-content class="container">
        <div class="main__container">
          <router-view v-slot="{ Component }">
            <keep-alive>
              <component :is="Component" />
            </keep-alive>
          </router-view>
        </div>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent,ref} from 'vue'
import PokemonMenu from './pokemon/layout/menu.vue'
import PokemonHeader from './pokemon/layout/header.vue'

export default defineComponent({
  components:{
    PokemonMenu,
    PokemonHeader,
  },
  setup() {
    const collapsed = ref<boolean>(false)

    return {
      collapsed,
    }
  }
})
</script>

<style scoped lang="scss">
.layout{
  height: 100vh;
  overflow: hidden;
  & .header{
    background-color: #fff;
    padding: 0;
  }
  & .container {
    padding: 8px;
    overflow-y: auto;
    overflow-x: hidden;
  }
  & .container::-webkit-scrollbar {
    width: 6px;
  }
  & .container::-webkit-scrollbar-thumb {
    border-radius: 10px;
    box-shadow: inset 0 0 5px #d8d8d8;
    background: #C1C1C1;
  }
  & .container::-webkit-scrollbar-track {
    box-shadow: inset 0 0 5px #d8d8d8;
    background: #ededed;
  }
}

.layout__header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 22px;
  font-size: 20px;
  & .header__left {
    display: flex;
    align-items: center;
    flex-grow: 1;
    & .group__tabs {
      width: 500px;
      margin-left: 22px;
      margin-right: 22px;
      flex-grow: 1;
    }
  }
  & .header__right {
    display: flex;
    align-items: center;
    flex-shrink: 0;
    flex-grow: 0;
    & .header__avatar {
      display: flex;
      align-items: center;
      padding: 0 12px;
      cursor: pointer;
      & .header__avatar-name {
        margin-left: 6px;
        font-size: 14px;
        vertical-align: middle;
      }
    }
  }
}
</style>
<style lang="scss">
.layout {
  & .ant-layout-sider-children {
    overflow-y: auto;
    overflow-x: hidden;
  }
  & .ant-layout-sider-children::-webkit-scrollbar {
    width: 4px;
  }
  & .ant-layout-sider-children::-webkit-scrollbar-thumb {
    border-radius: 10px;
    box-shadow: inset 0 0 5px #d8d8d8;
    background: #535353;
  }
  & .ant-layout-sider-children::-webkit-scrollbar-track {
    box-shadow: inset 0 0 5px #d8d8d8;
    background: #ededed;
  }
}
</style>