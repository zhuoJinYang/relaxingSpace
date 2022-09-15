<template>
  <div class="login-container">
    <a-row>
      <a-col :xs="0" :md="0" :sm="12" :lg="14" :xl="16">
        <div style="background-color: red"></div>
      </a-col>
      <a-col :xs="24" :sm="24" :md="12" :lg="10" :xl="6">
        <div class="login-container-form">
          <div class="login-container-hello">您好!</div>
          <div class="login-container-title">欢迎来到答题小程序管理后台</div>


<!--          <a-form ref="formRef" :model="form" :rules="rules" @keyup.enter="handleOpenCaptchaVerify">-->
<!--            <a-form-item name="username">-->
<!--              <a-input v-model:value="form.username" autocomplete="off" placeholder="请输入账号">-->
<!--                <template v-slot:prefix>-->
<!--                  <UserOutlined style="color: rgba(0, 0, 0, 0.25)" />-->
<!--                </template>-->
<!--              </a-input>-->
<!--            </a-form-item>-->
<!--            <a-form-item name="password">-->
<!--              <a-input-password v-model:value="form.password" autocomplete="off" placeholder="请输入密码">-->
<!--                <template v-slot:prefix>-->
<!--                  <LockOutlined style="color: rgba(0, 0, 0, 0.25)" />-->
<!--                </template>-->
<!--              </a-input-password>-->
<!--            </a-form-item>-->
<!--            <a-form-item>-->
<!--              <a-button type="primary" @click="handleOpenCaptchaVerify">登录</a-button>-->
<!--            </a-form-item>-->
<!--          </a-form>-->

        </div>
      </a-col>
    </a-row>
    <div class="login-container-tips"></div>
  </div>
</template>
<script setup lang="ts">
</script>
<style scoped lang="scss">
  .login-container{
    width: 100%;
    height: 100%;
    .login-container-form {
      width: calc(100% - 40px);
      height: 400px;
      padding: 4vh;
    }
  }
</style>