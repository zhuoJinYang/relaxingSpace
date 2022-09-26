import {AxiosResponse} from "axios";
import {UserInfo} from "@/types/user";

export interface ApiResult<T = unknown>{
    code: number,
    message: string,
    data?: T
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