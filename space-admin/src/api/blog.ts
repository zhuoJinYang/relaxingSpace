import {AxiosResult, BlogSucessData} from "@/types/api";
import axiosInstance from "@/axois";


export const apiGetBlogList = ():AxiosResult<BlogSucessData> => {
    return axiosInstance.get('/user/list')
}