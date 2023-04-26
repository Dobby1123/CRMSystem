import Vue from 'vue';
import App from './App.vue';
import router from './router';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css'; // 默认主题
import './assets/css/icon.css';
import 'babel-polyfill';
import axios from 'axios';
//import 'default-passive-events'
import common from './utils/common.js';
// 引入echarts
import * as echarts from 'echarts' 
Vue.prototype.$echarts = echarts
// 引入百度地图
import baiduMap from "vue-baidu-map";
Vue.use(baiduMap, { ak: 'HShV6kwpACYyFExk9Cvt7qrgaz2fhiNP' });
// 修改 el-dialog 默认点击遮照为不关闭
ElementUI.Dialog.props.closeOnClickModal.default = false
Vue.config.productionTip = false;
Vue.prototype.$axios = axios
Vue.prototype.common=common;
Vue.use(ElementUI, {
    size: 'small'
});


//守卫路由代码
//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
    // 简单的判断IE10及以下不进入富文本编辑器，该组件不兼容
    if (navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor') {
        Vue.prototype.$alert('vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看', '浏览器不兼容通知', {
            confirmButtonText: '确定'
        });
    } else {
        next();
    }
});


new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
