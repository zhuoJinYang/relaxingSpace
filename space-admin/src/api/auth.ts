import {AxiosResult, LoginSuccessData} from "@/types/api";
import axiosInstance from "@/axois";
import {LoginAccount} from "@/types/user";

export const getPasswordSecretKey = ():AxiosResult<string> => {
    return axiosInstance.get('/auth/password-secret-key')
}

export const apiLogin = (data:LoginAccount):AxiosResult<LoginSuccessData> => {
    return axiosInstance.post('/auth/login',data)
}