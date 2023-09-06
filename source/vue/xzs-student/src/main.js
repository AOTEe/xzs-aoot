import Vue from 'vue'
import App from './App.vue'
import { router } from './router'
import store from './store'
import 'normalize.css/normalize.css'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import '@/styles/index.scss' // global css
import './icons' // icon
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import axios from 'axios'
import jquery from "jquery";
import moment from 'moment';
import VueLazyLoad from 'vue-lazyload'// 1.图片懒加载



Vue.prototype.$ = jquery;
Vue.prototype.$axios = axios
Vue.prototype.$moment = moment
Vue.use(Element, {
  size: 'medium' // set element-ui default size
})
// 使用懒加载的插件   3.修改img :src -> v-lazy
Vue.use(VueLazyLoad, {
  // 占位图: 还没加载完，用这个图片代替
  loading: require('./assets/img/bilibili.png')
})

Vue.config.productionTip = false

NProgress.configure({ showSpinner: false }) // NProgress Configuration

router.beforeEach(async (to, from, next) => {
  // start progress bar
  NProgress.start()
  if (to.meta.title !== undefined) {
    document.title = to.meta.title
  } else {
    document.title = '\u200E'
  }

  if (to.meta.bodyBackground !== undefined) {
    document.querySelector('body').setAttribute('style', 'background: ' + to.meta.bodyBackground)
  } else {
    document.querySelector('body').removeAttribute('style')
  }

  if (to.path) {
    // eslint-disable-next-line no-undef
    _hmt.push(['_trackPageview', '/#' + to.fullPath])
  }
  next()
})

router.afterEach((to, from, next) => {
  // finish progress bar
  NProgress.done()
})

Vue.prototype.$$router = router

new Vue({
  router: router,
  store: store,
  beforeCreate() {
    Vue.prototype.$bus = this //安装全局事件总线，$bus就是当前应用的vm
  },
  render: h => h(App)
}).$mount('#app')
