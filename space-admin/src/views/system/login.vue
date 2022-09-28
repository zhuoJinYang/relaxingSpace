<template>
  <div class="login-container">
    <a-row>
      <a-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6"></a-col>
      <a-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
        <div class="login-container-form">
          <div class="container-form-navigation">
            <a-button class="navigation-login" :class="{'changeLoginTypeBtn':loginType}" @click="changeLoginType" type="text">登录</a-button>
            <a-button class="navigation-register" :class="{'changeLoginTypeBtn':!loginType}" @click="changeRegisterType" type="text">注册</a-button>
          </div>
          <div class="login-container-title">欢迎来到爷的兴趣空间-{{loginType?'登录':'注册'}}页面</div>
          <div class="container-avatar" v-if="loginType">
            <a-avatar :size="80" src="src/assets/login/avator.jpg">
            </a-avatar>
            <div class="container-avatar-name">爷就是天</div>
          </div>
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
import {apiSaveUserInfo} from "@/api/user";
import {message} from "ant-design-vue";
import {LoginAccount} from "@/types/user";
import {apiLogin} from "@/api/auth";

export default defineComponent({
  components:{
    UserOutlined,
    LockOutlined,
  },
  setup() {
    let loginType = ref<boolean>(true)
    const formState = reactive<LoginAccount>({
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
      apiLogin({...formState}).then(res => {
        console.log(res)
        console.log('登录验证成功！');
        router.push('/module')
      })
    }

    const registerUser = () => {
      apiSaveUserInfo(formState).then(() => {
        message.success('注册成功！')
      })
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
    background: url("/src/assets/login/login_bg.jpg");
    background-size: 100% 100%;
    .login-container-form {
      width: calc(100% - 40px);
      margin-top: calc((100vh - 380px) / 2);
      margin-left: 20px;
      margin-right: 20px;
      padding: 4vh;
      border-radius: 5px;
      box-shadow: 0 2px 5px 2px rgba(168, 144, 184, 0.5);
    }
    .login-container-title {
      font-size: 20px;
      margin-bottom: 20px;
    }
    .container-avatar{
      display: flex;
      flex-direction: column;
      align-items: center;
      .container-avatar-name{
        font-size: 20px;
        color: rgba(218, 13, 106, 0.74);
      }
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

.container-form-navigation{
  display: flex;
  padding-bottom: 20px;
  .navigation-login,.navigation-register{
    color: #888888;
  }
  .navigation-login::after{
    content: "";
    height: 80px;
    width: 1px;
    background: rgba(218, 218, 218, 1);
    margin: auto;
  }
}

.changeLoginTypeBtn,.changeLoginTypeBtn:focus{
  border-bottom: 1px solid rgba(245, 245, 245, 0.85);
  color: #1a1a1a;
  font-weight: bold;
  background-color: rgba(245, 245, 245, 0.71);
}
.changeLoginTypeBtn:hover{
  background-color: rgba(245, 245, 245, 0.85);
}
</style>