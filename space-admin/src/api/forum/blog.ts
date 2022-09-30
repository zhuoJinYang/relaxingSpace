import {AxiosResult, BlogSucessData} from "@/types/api";
import axiosInstance from "@/axois";
import {BlogModel} from "@/types/model";


export const apiGetBlogList = ():AxiosResult<BlogSucessData> => {
    return axiosInstance.get('/blog/list')
}

export const apiSaveBlogList = (data:BlogModel):AxiosResult => {
    return axiosInstance.post('/blog/save',data)
}