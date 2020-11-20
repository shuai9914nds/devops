import Vue from 'vue'
import App from './App.vue'
import axios from "axios"
// import './plugins/element.js'
import antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import router from './router'


Vue.use(antd)

Vue.config.productionTip = false;


Vue.config.productionTip = false
Vue.prototype.$axios = axios;
axios.defaults.baseURL = '/api'

new Vue({
    router,
    render: h => h(App)
}).$mount('#app1')
