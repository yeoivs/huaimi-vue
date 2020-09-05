import {post} from '@/utils/service'

export function login(data){
    return post('/login', data)
}
