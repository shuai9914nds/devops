/**axios封装
 * 请求拦截、相应拦截、错误统一处理
 */
import axios from 'axios';
import store from '@/store'
import {notification} from 'ant-design-vue';

function setError(error = '系统异常') {
    store.commit('SET_LOADING', false)
    notification.error({message: error})
}

export default function (params) {
    const baseUrl = '/api'
    const config = {
        url: baseUrl + params.url,
        base_url: '/abc',
        timeout: 1000 * 30,
        method: params.method || 'post' || 'put' || 'delete',
        headers: {
            'User-Token': localStorage.getItem("token")
        },
        data: params.data
    }

    if (params.method.toLowerCase() === 'get') {
        config.params = params.data
        config.data = {}
    }

    // 开启loading
    store.commit('SET_LOADING', true)
    // 特殊业务关闭loading
    if (params.loading === false) {
        store.commit('SET_LOADING', false)
    }

    axios.request(config).then(response => {
        const {obj, success, errorMessage} = response.data
        if (success) {
            store.commit('SET_LOADING', false)
            params.success && params.success(obj)
        } else {
            setError(errorMessage)
        }
    }).catch(error => {
        setError(error)
    })
}

