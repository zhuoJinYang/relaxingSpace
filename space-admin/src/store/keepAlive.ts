import {defineStore} from "pinia";

export const useKeepAliveStore = defineStore('keepAlive',{
    state: () => ({
        ForumIncludeList: [],
        PokeDexIncludeList:[]
    }),
    actions: {

    }
})