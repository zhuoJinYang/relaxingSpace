<template>
  <div class="pokemon-header">
    <div class="header-left">
      <menu-unfold-outlined v-if="collapsed" class="trigger" @click="$emit('update:collapsed',!collapsed)"/>
      <menu-fold-outlined v-else class="trigger" @click="$emit('update:collapsed',!collapsed)"/>
    </div>
    <div class="header-right">
      <a-dropdown :trigger="['click', 'hover']">
        <div class="header-avatar">
          <a-avatar>
            <template #icon>
              <UserOutlined />
            </template>
          </a-avatar>
          <div class="header-avatar-name">{{'一只猪'}}</div>
        </div>
        <template #overlay>
          <a-menu>
            <a-menu-item key="1" @click="returnModule">
              返回模块页面
            </a-menu-item>
            <a-menu-item key="2" @click="handleLogout">
              退出登录
            </a-menu-item>
          </a-menu>
        </template>
      </a-dropdown>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent} from 'vue'
import {MenuFoldOutlined, MenuUnfoldOutlined, UserOutlined} from "@ant-design/icons-vue";
import router from "@/router";

export default defineComponent({
  name:'PokemonHeader',
  components:{
    MenuUnfoldOutlined,
    MenuFoldOutlined,
    UserOutlined,
  },
  props:['collapsed'],
  setup() {
    const handleLogout = () => {
      router.push('/')
    }
    const returnModule = () => {
      router.push('/module')
    }
    return {
      handleLogout,
      returnModule
    }
  }
})
</script>

<style scoped lang="scss">
.pokemon-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 22px;
  font-size: 20px;
  & .header-left {
    display: flex;
    align-items: center;
    flex-grow: 1;
    & .ant-tabs-bar {
      margin: 0;
      border: none;
    }
  }

  & .header-right {
    display: flex;
    align-items: center;
    flex-shrink: 0;
    flex-grow: 0;
    & .header-avatar {
      display: flex;
      align-items: center;
      padding: 0 12px;
      cursor: pointer;
      & .header-avatar-name {
        margin-left: 6px;
        font-size: 14px;
        vertical-align: middle;
      }
    }
  }
}
</style>