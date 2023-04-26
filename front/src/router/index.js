import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect:'/login',
        },
        {
            path: '/login',
            component: () => import('../components/page/Login.vue'),
            meta: {title: '登录'}
        },
        {
            path: '/register',
            component: () => import('../components/page/Register.vue'),
            meta: {title: '注册'}
        },
        {
            path: '/admin/',
            component: () => import('../components/common/Home.vue'),
            meta: {title: '首页'},
            children: [
                {
                    path: '/admin/Index',
                    component: () => import('../components/page/Index.vue'),
                    meta: {title: '首页'}
                },
                {
                    path: '/admin/UserList',
                    component: () => import('../components/page/UserList.vue'),
                    meta: {title: '用户管理'}
                },
                {
                    path: '/admin/ActivityList',
                    component: () => import('../components/page/ActivityList.vue'),
                    meta: {title: '市场活动管理'}
                },
                {
                    path: '/admin/AuthorityList',
                    component: () => import('../components/page/AuthorityList.vue'),
                    meta: {title: '部门权限管理'}
                },
                {
                    path: '/admin/ContacterList',
                    component: () => import('../components/page/ContacterList.vue'),
                    meta: {title: '联系人管理'}
                },
                {
                    path: '/admin/CustomerList',
                    component: () => import('../components/page/CustomerList.vue'),
                    meta: {title: '客户管理'}
                },
                {
                    path: '/admin/Customer2List',
                    component: () => import('../components/page/Customer2List.vue'),
                    meta: {title: '客户管理'}
                },
                {
                    path: '/admin/DealsList',
                    component: () => import('../components/page/DealsList.vue'),
                    meta: {title: '交易管理'}
                },
                {
                    path: '/admin/DepartmentList',
                    component: () => import('../components/page/DepartmentList.vue'),
                    meta: {title: '部门管理'}
                },
                {
                    path: '/admin/MenusList',
                    component: () => import('../components/page/MenusList.vue'),
                    meta: {title: '菜单管理'}
                }, {
                    path: '/admin/SalesList',
                    component: () => import('../components/page/SalesList.vue'),
                    meta: {title: '售后信息管理'}
                },
                {
                    path: '/admin/AdminList',
                    component: () => import('../components/page/AdminList.vue'),
                    meta: {title: '管理员管理'}
                },
                {
                    path: '/admin/MyInfo',
                    component: () => import('../components/page/MyInfo.vue'),
                    meta: {title: '我的信息'}
                },
                {
                    path: '/admin/*',
                    redirect: '/404'
                }
            ]
        }
    ]
});
