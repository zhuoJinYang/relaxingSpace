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
      <a-dropdown :trigger="['click', 'hover']">
        <div class="profile-avatar">
          <a-avatar>
            <template #icon>
              <UserOutlined />
            </template>
          </a-avatar>
          <div class="profile-avatar-name">{{'你的狗头'}}</div>
        </div>
        <template #overlay>
          <a-menu>
            <a-menu-item key="1" @click="handleWriteBlog">
              编写文章
            </a-menu-item>
            <a-menu-item key="2" @click="returnModule">
              返回模块页面
            </a-menu-item>
            <a-menu-item key="3" @click="handleLogout">
              退出登录
            </a-menu-item>
          </a-menu>
        </template>
      </a-dropdown>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive, ref} from 'vue'
import {MenuRecord} from "@/types/public";
import {SearchOutlined, UserOutlined} from "@ant-design/icons-vue";
import router from "@/router";
import {useRoute} from "vue-router";

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
  components:{
    SearchOutlined,
    UserOutlined
  },
  setup() {
    const route = useRoute()
    let currentMenuIndex = ref<string[]>([]);
    const value = ref<string>('');
    const menus = reactive(menuList)

    const matchMenu = menuList.find(v => v.path === route.path)
    if (matchMenu){
      currentMenuIndex = ref([matchMenu.id])
    }

    const handleWriteBlog = () => {
      router.push('/forum/editBlog')
    }
    const handleLogout = () => {
      router.push('/')
    }
    const returnModule = () => {
      router.push('/module')
    }
    return {
      currentMenuIndex,
      menus,
      value,
      handleWriteBlog,
      handleLogout,
      returnModule
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
    align-items: center;
    flex-shrink: 0;
    flex-grow: 0;
    & .profile-avatar{
      display: flex;
      align-items: center;
      padding: 0 12px;
      cursor: pointer;
      & .profile-avatar-name{
        margin-left: 6px;
        font-size: 14px;
        vertical-align: middle;
      }
    }
  }
}
</style>