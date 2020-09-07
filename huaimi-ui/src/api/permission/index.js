import service from "@/config/service";

export async function list() {
    return await service.get('/system/permission/list')
}

export async function get(id){
    return await service.get('/system/permission/'+id)
}

export function search(data){
    return service.post('/system/permission/list', data)
}

export function add(data){
    return service.post('/system/permission', data)
}

export function edit(data){
    return service.put('/system/permission', data)
}

export function del(id){
    return service.delete('/system/permission?id='+id, )
}

const permission = {get, list, edit, del, add, search};

export default permission
