import {AxiosResult, LoginSuccessData} from "@/types/api";
import axiosInstance from "@/axois";
import {LoginAccount} from "@/types/user";

const API_URL_CAPTCHA = import.meta.env.VITE_APP_BASE_URL + '/auth/getCaptcha'

export const getPasswordSecretKey = ():AxiosResult<string> => {
    return axiosInstance.get('/auth/password-secret-key')
}

export const apiLogin = (data:LoginAccount):AxiosResult<LoginSuccessData> => {
    return axiosInstance.post('/auth/login',data)
}

export const apiGetCaptcha = (uuid: string): string => {
    return API_URL_CAPTCHA + '?uuid=' + uuid
}