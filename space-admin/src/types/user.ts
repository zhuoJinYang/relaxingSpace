export type LoginAccount = {
    username:string,
    password:string,
    uuid?: string,
    captcha?: string,
    phone?:string,
    email?:string
}

export type UserInfo = {
    id: string,
    username: string
}