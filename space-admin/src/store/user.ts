import {defineStore} from "pinia";

// defineStore 调用后返回一个函数，调用该函数获得 Store 实体
// 定义的defineStore()，并且它需要一个唯一的名称，common名称抽离出去作为第一个参数传递
export const useCommonStore = defineStore('user',{
    state: () => {
        return {
            current: 0,
            token: '',
        }
    },
    getters: {
        doubleCurrent: (state) => state.current * 2
    },
    actions: {
        setCurrent() {
            this.current++
        }
    }
})
