import axios, {AxiosError, AxiosRequestConfig, AxiosResponse} from 'axios';

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
axiosInstance.interceptors.request.use((config:AxiosRequestConfig) => {
    return config
},errorHandler)

/**
 * 请求发生后拦截
 */
axios.interceptors.response.use((response:AxiosResponse) => {
    window.console.log('发起了请求:',response);
    return response.data
},errorHandler)

export default axiosInstance;