import Vue from 'vue'
import App from './App.vue'
import axios from "axios"
import antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import router from './router'
import store from './store'


Vue.use(antd)

Vue.config.productionTip = false;


Vue.config.productionTip = false
Vue.prototype.$axios = axios;
Vue.prototype.$store = store;
axios.defaults.baseURL = '/api'

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app1')
