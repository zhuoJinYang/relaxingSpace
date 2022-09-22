<template>
  <div class="login-container">
    <a-row>
      <a-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6"></a-col>
      <a-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
        <div class="login-container-form">
          <div style="display: flex">
            <a-button @click="changeLoginType">登录</a-button>
            <a-button @click="changeRegisterType">注册</a-button>
          </div>
          <div class="login-container-title">欢迎来到爷的兴趣空间</div>
          <a-form
              :model="formState"
              :rules="loginRules"
              @finish="onFinish"
              @finishFailed="onFinishFailed"
          >
            <a-form-item
                name="username"
                :rules="[{ required: true, message: '请输入账号' }]"
            >
              <a-input v-model:value="formState.username"
                       placeholder="请输入账号">
                <template #prefix>
                  <UserOutlined class="site-form-item-icon" />
                </template>
              </a-input>
            </a-form-item>

            <a-form-item
                name="password"
                :rules="[{ required: true, message: '请输入密码' }]"
            >
              <a-input-password v-model:value="formState.password"
                                placeholder="请输入密码">
                <template #prefix>
                  <LockOutlined class="site-form-item-icon" />
                </template>
              </a-input-password>
            </a-form-item>

            <a-form-item>
              <a-button v-if="loginType" type="primary" html-type="submit" @click="handleUserLogin">登录</a-button>
              <a-button v-else type="primary" @click="registerUser">注册</a-button>
            </a-form-item>
          </a-form>
        </div>
      </a-col>
      <a-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6"></a-col>
    </a-row>
    <div class="login-container-tips"></div>
  </div>
</template>
<script lang="ts">
import {defineComponent, reactive, ref} from 'vue';
import { UserOutlined, LockOutlined } from '@ant-design/icons-vue';
import router from "@/router";
interface FormState {
  username: string;
  password: string;
}
export default defineComponent({
  components:{
    UserOutlined,
    LockOutlined,
  },
  setup() {
    let loginType = ref<boolean>(true)
    const formState = reactive<FormState>({
      username: '',
      password: '',
    });

    const loginRules = reactive({
      username:[
        {
          require: true,
          message: '请输入账号',
          trigger: 'change'
        }
      ],
      password:[
        {
          require: true,
          message: '请输入密码',
          trigger: 'change'
        }
      ]
    })

    const onFinish = (values: any) => {
      console.log('Success:', values);
    };

    const onFinishFailed = (errorInfo: any) => {
      console.log('Failed:', errorInfo);
    };

    const handleUserLogin = () => {
      router.push('/module')
    }

    const registerUser = () => {
      console.log('注册')
    }

    const changeLoginType = () => {
      loginType.value = true
    }
    const changeRegisterType = () => {
      loginType.value = false
    }
    return {
      loginType,
      formState,
      loginRules,
      onFinish,
      onFinishFailed,
      handleUserLogin,
      registerUser,
      changeLoginType,
      changeRegisterType
    };
  }
})

</script>
<style scoped lang="scss">
  .login-container{
    width: 100%;
    height: 100vh;
    .login-container-form {
      width: calc(100% - 40px);
      margin-top: calc((100vh - 380px) / 2);
      margin-left: 20px;
      margin-right: 20px;
      padding: 4vh;
      background: url("/src/assets/login/login_bg.jpg");
      background-size: 100% 100%;
      border-radius: 5px;
    }
    .login-container-title {
      font-size: 20px;
      margin-bottom: 20px;
    }
  }
</style>
<style lang="scss">
.login-container{
  .login-container-form{
    .ant-input {
      height: 25px;
    }
    .ant-btn{
      width: 100%;
    }
  }
}
</style>