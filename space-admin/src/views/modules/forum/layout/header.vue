<template>
  <div class="forum-header">
    <div class="header-left">
      <div>icon</div>
      <a-menu mode="horizontal" v-model:selected-keys="currentMenuIndex">
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
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive, ref} from 'vue'
import {MenuRecord} from "@/types/public";

const menuList: MenuRecord[] = [
  {
    id: '1',
    name: '主页',
    path: '/forum/mainPage'
  },
  {
    id: '2',
    name: '我的博客',
    path: '/forum/myBlog'
  },
  {
    id: '3',
    name: '留言',
    path: '/forum/writeMessage'
  }
]

export default defineComponent({
  name:'ForumHeader',
  setup() {
    const currentMenuIndex = ref<string[]>([]);
    const menus = reactive(menuList)
    return {
      currentMenuIndex,
      menus,
    }
  }
})
</script>

<style scoped lang="scss">
.forum-header{
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