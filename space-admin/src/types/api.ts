import {AxiosResponse} from "axios";


export interface ApiResult<T = unknown>{
    code: number,
    message: string,
    data?: T
}

export type AxiosResult<T=ApiResult> = Promise<AxiosResponse<T>>

export interface UserSuccessData extends ApiResult{
    id:string,
    username:string,
    password:string,
    phone?:string,
    email?:string
}