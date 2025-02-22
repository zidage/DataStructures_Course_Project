//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
import { ElMessage } from 'element-plus'

//定义一个变量,记录公共的前缀，baseURL
// const baseURL = 'http://localhost:8080';
const baseURL = '/api';
const instance = axios.create({ baseURL })
import { useTokenStore } from '@/stores/token.js';


//添加响应拦截器
instance.interceptors.response.use(
    result => {
        //判断业务状态码
        if (result.data.code === 0) {
            return result.data;
        }
        if (result.data !== null) {
            ElMessage.error(result.data);
        } else {
            ElMessage.error('服务异常');
        }
        //异步操作的状态转换为失败
        return Promise.reject(result.data);
    },
    err => {

        ElMessage.error('服务异常');
        return Promise.reject(err);//异步的状态转化成失败的状态
    }
)

//添加请求拦截器
instance.interceptors.request.use(
    (config) => {
        //请求前的回调
        //获取token
        const tokenStore = useTokenStore();
        if (tokenStore.token) {
            //有token则返回
            config.headers.Authorization = tokenStore.token;
        }
        return config;
    },
    (err) => {
        //请求失败的回调
        Promise.reject(err);
    }

)
export default instance;