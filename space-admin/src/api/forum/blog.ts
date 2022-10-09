import {AxiosResult, BlogDetailData, BlogSucessData} from "@/types/api";
import axiosInstance from "@/axois";
import {BlogModel} from "@/types/model";


export const apiGetBlogList = ():AxiosResult<BlogSucessData> => {
    return axiosInstance.get('/blog/list')
}

export const apiSaveBlogList = (data:BlogModel):AxiosResult => {
    return axiosInstance.post('/blog/save',data)
}

export const apiGetBlogDetail = (id:string):AxiosResult<BlogDetailData> => {
    return axiosInstance.get('/blog/detail',{params:{id}})
}