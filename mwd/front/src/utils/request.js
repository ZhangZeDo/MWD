import axios from "axios";
import qs from "qs"; //处理参数
import router from '../router';
import { Loading } from 'element-ui';
import { Message } from 'element-ui';

let loading = null;

//开始加载动画
function openLoading(){
    loading = Loading.service({
        lock:true,
        text:'拼命加载中...',
        background:'rgba(0,0,0,.7)'
    });
}

//关闭加载动画
function closeLoading(){
    loading.close();
}

//添加请求拦截器
axios.interceptors.request.use(
    config => {
        //加载动画
        openLoading()
        if(localStorage.session){
            config.headers.Authorization = localStorage.session
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

//添加响应拦截器
axios.interceptors.response.use(
    response => {
        closeLoading()//关闭加载动画
        if(response.data.errorCode == -1){ //后端反回的状态值，本应该errorCode是status
            Message({
                type:'warning',
                duration:1500,
                showClose: true,
                message:'当前登录已失效，请从新登录！'
            })
            localStorage.removeItem("session")//清除sessionId
            localStorage.removeItem("userName")//清除username
            localStorage.removeItem("modules")//清除权限
            router.push({name: 'login'});
        }
        return response;
    },
    error => {
        closeLoading() // 关闭加载动画
        return Promise.resolve(error.response);
    }
);

axios.defaults.baseURL = "http://localhost:8083";
axios.defaults.withCredentials = true;
axios.defaults.headers.post["Content-Type"] = "application/json";
axios.defaults.headers.post["X-Requested-With"] = "XMLHttpRequest";
axios.defaults.timeout = 6000; //设置请求超时时间

function checkStatus(response,type) {
    return new Promise((resolve) => {
        if(response && (response.status === 200 || response.status === 304 || response.status === 400)){
            if (response.data.code === "200"){
                resolve(response.data);
            } else if (type && type === "blob"){
                resolve(response.data)
            } else{
                Message({
                    type:'error',
                    showClose: true,
                    message:response.data.message
                })
            }
        } else{
            Message({
                type:'error',
                showClose: true,
                message:'系统异常，请稍后重试！'
            })
        }
    });
}

export default {
    post(url, params) {
        return axios({
            method: "post",
            url,
            data: params
        }).then(response => {
            return checkStatus(response);
        });
    },
    postWithBlob(url,params){
        return axios({
            method: "post",
            url,
            data: params,
            responseType: 'blob'
        }).then(response => {
            return checkStatus(response,"blob");
        });
    },
    get(url, params) {
        params = qs.stringify(params);
        return axios({
            method: "get",
            url,
            params
        }).then(response => {
            return checkStatus(response);
        });
    }
};
