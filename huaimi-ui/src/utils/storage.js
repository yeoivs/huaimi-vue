export function getToken(){
    return window.sessionStorage.getItem('token');
}

export function setToken(value){
    return window.sessionStorage.setItem('token', 'Bearer ' + value);
}

export function clear(){
    return window.sessionStorage.clear();
}

export function get(key){
    return window.sessionStorage.getItem(key)
}

export function set(key, val){
    return window.sessionStorage.setItem(key, val)
}

export function getLocal(key){
    return window.localStorage.getItem(key)
}

export function setLocal(key, value){
    return window.localStorage.setItem(key, value);
}

const storage = { setToken, getToken, get, set, clear, getLocal, setLocal }

export default storage
