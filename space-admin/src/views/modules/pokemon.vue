<template>
  <a-layout class="layout">
    <!-- 侧边 -->
    <a-layout-sider width="256" v-model:collapsed="collapsed" :trigger="null" collapsible>
      <div class="menu__logo">
        <div class="menu__logo-icon">
          <img src="@/assets/pokemon/logo.ico" />
        </div>
        <span v-show="!collapsed">精灵图鉴中心</span>
      </div>
      <a-menu
          theme="dark"
          mode="inline"
          v-model:selectedKeys="selectedKeys"
          v-model:openKeys="openKeys"
      >
        <template v-for="menu in menus">
          <template v-if="menu?.children?.length > 0">
            <a-sub-menu :key="menu.id">
              <template #title>{{ menu.name }}</template>
              <a-menu-item v-for="submenu in menu.children" :key="submenu.id">
                <router-link v-if="submenu.path" :to="submenu.path">{{ submenu.name }}</router-link>
                <span v-else>{{ submenu.name }}</span>
              </a-menu-item>
            </a-sub-menu>
          </template>
          <template v-else>
            <a-menu-item :key="menu.id">
              <router-link v-if="menu.path" :to="menu.path">{{ menu.name }}</router-link>
              <span v-else>{{ menu.name }}</span>
            </a-menu-item>
          </template>
        </template>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <!-- 头部 -->
      <a-layout-header class="header">
        <div class="header__left">
          <menu-unfold-outlined v-if="collapsed" class="trigger"/>
          <menu-fold-outlined v-else class="trigger"/>
        </div>
        <div class="header__right">
          <a-dropdown :trigger="['click', 'hover']">
            <div class="header__avatar">
              <a-avatar>
                <template #icon>
                  <UserOutlined />
                </template>
              </a-avatar>
              <div class="header__avatar-name">{{'一只猪'}}</div>
            </div>
            <template #overlay>
              <a-menu>
                <a-menu-item key="1">
                  1
                </a-menu-item>
                <a-menu-item key="2">
                  2
                </a-menu-item>
              </a-menu>
            </template>
          </a-dropdown>
        </div>
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
import {defineComponent, reactive, ref} from 'vue'
import {MenuRecord} from "@/types/public";
import {UserOutlined,MenuUnfoldOutlined,MenuFoldOutlined} from "@ant-design/icons-vue";

const menuList: MenuRecord[] = [
  {
    id: '1',
    name: '图鉴大全',
    path: '/pokemon/pokeDex'
  },
  {
    id: '2',
    name: '我的收藏',
    path: '/pokemon/myCollection'
  },
  {
    id: '3',
    name: '队伍管理',
    path: '/pokemon/arrange'
  }
]

export default defineComponent({
  components:{
    UserOutlined,
    MenuUnfoldOutlined,
    MenuFoldOutlined
  },
  setup() {
    const collapsed = ref<boolean>(false)
    let selectedKeys = ref<string[]>([])
    const openKeys = ref<string[]>([])
    const menus = reactive(menuList)
    return {
      collapsed,
      selectedKeys,
      openKeys,
      menus
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
.menu__logo {
  display: flex;
  align-items: center;
  padding-left: 24px;
  height: 64px;
  line-height: 64px;
  overflow: hidden;
  white-space: nowrap;
  & .menu__logo-icon {
    width: 32px;
    margin-right: 8px;
    img {
      display: block;
      width: 100%;
    }
  }
  & span {
    display: inline-block;
    font-size: 18px;
    color: #fff;
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