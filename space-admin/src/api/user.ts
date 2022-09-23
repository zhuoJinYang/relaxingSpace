import axiosInstance from "@/axois";
import {AxiosResult, UserSuccessData} from "@/types/api";

export const apiGetUserList = ():AxiosResult<UserSuccessData> => {
    return axiosInstance.get('/user/list')
}