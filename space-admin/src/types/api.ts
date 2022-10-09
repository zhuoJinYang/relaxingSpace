import {AxiosResponse} from "axios";
import {UserInfo} from "@/types/user";

export interface ApiResult<T = unknown>{
    code: number,
    message: string,
    data?: T
}

export interface ApiListResult<T = unknown> extends ApiResult{
    page: number,
    pages: number,
    size: number,
    total: number,
    list?: T
}

export type AxiosResult<T=ApiResult> = Promise<AxiosResponse<T>>

export interface LoginSuccessData extends ApiResult {
    token: string,
    user: UserInfo,
}

export interface UserSuccessData extends ApiResult{
    id:string,
    username:string,
    password:string,
    phone?:string,
    email?:string
}

export interface BlogSucessData extends ApiListResult{
    id: string,
    userId?: string,
    title: string,
    label: string,
    summary?: string,
    preview?: string,
    collections?: string,
    likes?: string,
    dislikes?: string,
}

export interface BlogDetailData extends ApiResult{
    id: string,
    userId?: string,
    title: string,
    label: string[],
    summary?: string,
    content: string,
    preview?: string,
    collections?: string,
    likes?: string,
    dislikes?: string,
}