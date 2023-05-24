import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/emp',
    name: 'emp',
    component: () => import('../views/orderSystem/EmpView.vue')
  },

  {
    path: '/client',
    name: 'client',
    component: () => import('../views/orderSystem/ClientView.vue')
  },
  {
    path: '/shipper',
    name: 'shipper',
    component: () => import('../views/orderSystem/ShipperView.vue')
  },
  {
    path: '/supplier',
    name: 'supplier',
    component: () => import('../views/orderSystem/SupplierView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/orderSystem/LoginUser.vue')
  },
  {
    path: '/',
    redirect: '/login'
  }
]

const router = new VueRouter({
  routes
})

export default router
