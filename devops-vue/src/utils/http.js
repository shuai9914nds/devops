/**axios封装
 * 请求拦截、相应拦截、错误统一处理
 */
import axios from 'axios';
import {notification} from 'ant-design-vue';

function setError(error = '系统异常') {
    notification.error({message: error})
}

export default function (params) {
    const config = {
        url: params.url,
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

    axios.request(config).then(response => {
        const {obj, success, errorMessage} = response.data
        if (success) {
            params.success && params.success(obj)
        } else {
            setError(errorMessage)
        }
    }).catch(error => {
        setError(error)
    })
}

