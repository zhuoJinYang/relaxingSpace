import {defineStore} from "pinia";
import {LoginAccount} from "@/types/user";
import {LoginSuccessData} from "@/types/api";
import {apiLogin, getPasswordSecretKey} from "@/api/auth";
import {encrypt} from "@/util/security";

// defineStore 调用后返回一个函数，调用该函数获得 Store 实体
// 定义的defineStore()，并且它需要一个唯一的名称，common名称抽离出去作为第一个参数传递
export const useUserStore = defineStore('user',{
    state: () => ({
        token: '123',
        userId: '',
        username: '',
        phone: '',
        email: '',
    }),
    actions: {
        login(account:LoginAccount){
            return new Promise<LoginSuccessData>((resolve,reject) => {
                encryptPassword(account.password).then(encryptPassword => {
                    apiLogin({...account,password: encryptPassword}).then(res => {
                        this.token = res.data.token
                        this.userId = res.data.user.id
                        this.username = res.data.user.username
                        resolve(res.data)
                    }).catch(err => {
                        reject(err)
                    })
                })
            })
        }
    }
})

const encryptPassword = (passwordInput: string): Promise<string> => {
    return new Promise((resolve,reject) => {
        getPasswordSecretKey().then(rsaSecret => {
            console.log(rsaSecret);
            resolve(encrypt(passwordInput,rsaSecret.data))
        }).catch(err => {
            reject(err)
        })
    })
}