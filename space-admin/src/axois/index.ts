import axios, {AxiosError, AxiosRequestConfig, AxiosResponse} from 'axios';
import router from "@/router";
import {message} from "ant-design-vue";

const axiosInstance = axios.create({
    timeout: 60000,
    withCredentials: true,
    baseURL: import.meta.env.VITE_APP_BASE_URL,
});

const errorHandler = (error:AxiosError): AxiosError | Promise<AxiosError> => {
    window.console.log(error)
    return Promise.reject(error)
}

/**
 * 请求发生前拦截
 */
axiosInstance.interceptors.request.use((config:AxiosRequestConfig): AxiosRequestConfig => {
    return config
},errorHandler)

/**
 * 服务器响应后拦截
 */
axiosInstance.interceptors.response.use((response:AxiosResponse): AxiosResponse | Promise<AxiosResponse> => {
    window.console.log('发起了请求:',response);
    if (response.status !== 200){
        return Promise.reject(response)
    }
    const { code : resultCode } = response.data
    if ( resultCode !== 0 ){
        switch (resultCode){
            case 2000:{
                // 登录失效
                router.push('/login')
                return Promise.reject(response)
            }
            default:{
                message.error(response.data.message)
                return Promise.reject(response)
            }
        }
    }
    return response.data
},errorHandler)

export default axiosInstance;