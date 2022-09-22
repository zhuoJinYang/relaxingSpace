<template>
  <div class="logo">
    <div class="logo-icon">
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
          <template #icon>
            <ant-icon :type="menu.icon"></ant-icon>
          </template>
          <router-link v-if="menu.path" :to="menu.path">{{ menu.name }}</router-link>
          <span v-else>{{ menu.name }}</span>
        </a-menu-item>
      </template>
    </template>
  </a-menu>
</template>

<script lang="ts">
import {defineComponent, reactive, ref} from 'vue'
import {MenuRecord} from "@/types/public";
import AntIcon from "@/components/AntIcon.vue";

const menuList: MenuRecord[] = [
  {
    id: '1',
    name: '图鉴大全',
    icon: 'appstore-outlined',
    path: '/pokemon/pokeDex'
  },
  {
    id: '2',
    name: '我的收藏',
    icon: 'wallet-outlined',
    path: '/pokemon/myCollection'
  },
  {
    id: '3',
    name: '队伍管理',
    icon: 'team-outlined',
    path: '/pokemon/arrange'
  }
]

export default defineComponent({
  name:'PokemonMenu',
  components:{
    AntIcon
  },
  props:['collapsed'],
  setup() {
    let selectedKeys = ref<string[]>([])
    const openKeys = ref<string[]>([])
    const menus = reactive(menuList)
    return {
      selectedKeys,
      openKeys,
      menus,
    }
  }
})
</script>

<style scoped lang="scss">
.logo {
  display: flex;
  align-items: center;
  padding-left: 24px;
  height: 64px;
  line-height: 64px;
  overflow: hidden;
  white-space: nowrap;
  & .logo-icon {
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
</style>