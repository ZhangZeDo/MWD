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
                    path:'/personalCenter',
                    name:'personalCenter',
                    component:() =>import('@/views/personalCenter'),
                },
                {
                    path:'/order',
                    name:'order',
                    component:()=>import('@/views/order')
                }
            ]
        },
    ]
})

export default routers
