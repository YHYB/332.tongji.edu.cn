import Vue from 'vue'
import moment from 'moment'
import 'normalize.css/normalize.css'// A modern alternative to CSS resets

import ElementUI from 'element-ui'
//import 'element-ui/lib/theme-chalk/index.css'
import '@/theme/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN'

import '@/styles/index.scss' // global css
Vue.prototype.$moment = moment
import App from './App'
import router from './router'
import store from './store'

import '@/icons' // icon
import '@/permission' // permission control

Vue.use(ElementUI, { locale })
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
