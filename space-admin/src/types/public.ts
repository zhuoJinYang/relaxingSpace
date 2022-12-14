export type ModuleRecord = {
    id: string,
    name: string,
    path: string,
    defaultPath?: string,
}

export type MenuRecord = {
    id: string,
    path: string,
    name: string,
    component?: string,
    redirect?: string,
    icon?: string,
    title?: string,
    children?: MenuRecord[],
    hidden?: boolean,
    keepAlive?: boolean,
    permission?: string[],
}