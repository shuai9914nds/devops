import Vue from 'vue'
import App from './App.vue'
import axios from "axios"
import './plugins/element.js'
import Button from 'ant-design-vue/lib/button';
import 'ant-design-vue/dist/antd.css';

Vue.component(Button.name, Button);

Vue.config.productionTip = false;


Vue.config.productionTip = false
Vue.prototype.$axios = axios;
axios.defaults.baseURL = '/api'

new Vue({
    render: h => h(App),
}).$mount('#app1')
