import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const routers = new Router({
    routes:[
        {
            path:'/',
            name:'login',
            redirect: '/login/index',
        },
        {
            path:'/homPage',
            name:'homePage',
            redirect:'/homePage/index',
            children: [
                {
                    path: '/homePage',
                    name: 'departmentDashboard',
                    hidden: false,
                    component: () => import('@/views/departmentDashboard/departmentDashboard'),
                    meta: {title: '我的部门', icon: 'dashboard'}
                },
                {
                    hidden: true,
                    path: '/user-info',
                    name: 'UserInfo',
                    component: () => import('@/views/user/index'),
                    meta: {title: '用户信息', icon: 'form'}
                }
            ]
        },
    ]
})

export default routers
