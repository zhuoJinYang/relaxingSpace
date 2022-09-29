export type BlogModel = {
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