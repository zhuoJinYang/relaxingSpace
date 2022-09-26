import axiosInstance from "@/axois";
import {AxiosResult, UserSuccessData} from "@/types/api";
import {LoginAccount} from "@/types/user";

export const apiGetUserList = ():AxiosResult<UserSuccessData> => {
    return axiosInstance.get('/user/list')
}

export const apiSaveUserInfo = (data:LoginAccount):AxiosResult => {
    return axiosInstance.post('/user/save',data)
}