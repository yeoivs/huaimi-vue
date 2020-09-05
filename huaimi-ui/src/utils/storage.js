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
    return window.localStorage.getItem(key)
}

export function set(key, value){
    return window.localStorage.setItem(key, value);
}

const storage = { setToken, getToken, clear, get, set }

export default storage
