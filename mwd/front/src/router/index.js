import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const routers = new Router({
    routes:[
        {
            path:'/',
            redirect: '/login',
        },
        {
            path:'/login',
            name:'login',
            component:()=>import('@/views/login')
        },
        {
          path:'/register',
          name:'register',
          component:()=>import('@/views/login/register')
        },
        {
            path:'/forgetPass',
            name:'forgetPass',
            component:()=>import('@/views/login/forgetPass')
        },
        {
            path:'/index',
            name:'index',
            component:()=> import('@/views/index'),
            redirect:'/homePage',
            children:[
                {
                    path:'/homePage',
                    name:'homePage',
                    component: () => import('@/views/homePage'),
                },
                {
                    path:'/news',
                    name:'news',
                    component: () => import('@/views/homePage/news'),
                },
                {
                    path:'/search',
                    name:'search',
                    component: () => import('@/views/homePage/search'),
                },
                {
                    path:'/mediaDetail',
                    name:'mediaDetail',
                    component: () => import('@/views/homePage/mediaDetail'),
                },
                {
                    path:'/userInfo',
                    name:'userInfo',
                    component:() =>import('@/views/personalCenter/userInfo'),
                },
                {
                    path:'/fileUpload',
                    name:'fileUpload',
                    component:() =>import('@/views/personalCenter/fileUpload'),
                },
                {
                    path:'/bookmark',
                    name:'bookmark',
                    component:() =>import('@/views/personalCenter/bookmark'),
                },
                {
                    path:'/adminManage',
                    name:'adminManage',
                    component:()=>import('@/views/personManage/adminManage')
                },
                {
                    path:'/userManage',
                    name:'userManage',
                    component:()=>import('@/views/personManage/userManage')
                },
                {
                    path:'/mediaList',
                    name:'mediaList',
                    component:()=>import('@/views/mediaManage/mediaList')
                },
                {
                    path:'/mediaTypeManage',
                    name:'mediaTypeManage',
                    component:()=>import('@/views/mediaManage/mediaTypeManage')
                },
                {
                    path:'/timingTask',
                    name:'timingTask',
                    component:()=>import('@/views/system/timingTask')
                },
                {
                    path:'/variableConfig',
                    name:'variableConfig',
                    component:()=>import('@/views/system/variableConfig')
                },
                {
                    path:'/newsList',
                    name:'newsList',
                    component:()=>import('@/views/newsManage/newsList')
                },

            ]
        },
    ]
})

export default routers
