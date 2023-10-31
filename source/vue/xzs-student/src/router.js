import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/layout'

Vue.use(Router)
const router = new Router({
  routes: [
    { path: '/login', name: 'Login', component: () => import('@/views/login/index'), meta: { title: '登录', bodyBackground: '#fbfbfb' } },
    { path: '/register', name: 'Register', component: () => import('@/views/register/index'), meta: { title: '注册', bodyBackground: '#fbfbfb' } },
    {
      path: '/',
      component: Layout,
      redirect: '/index',
      children: [
        {
          path: 'index',
          component: () => import('@/views/dashboard/index'),
          name: 'Dashboard',
          meta: { title: '首页' }
        }
      ]
    },
    {
      path: '/paper',
      component: Layout,
      children: [
        {
          path: 'index',
          component: () => import('@/views/paper/index'),
          name: 'PaperIndex',
          meta: { title: '试卷中心' }
        }
      ]
    },
    {
      path: '/video_room',
      component: Layout,
      children: [
        {
          path: 'index2',
          component: () => import('@/views/video-room/index2'),
          name: 'VideoIndex',
          meta: { title: '视频课堂' }
        },
        {
          path: '/video/:id',
          component: () => import('@/views/video-room/videoPage'),
          name: 'video',
          meta: { title: '视频课堂' }
        }
      ]
    },
    {
      path: '/message',
      component: Layout,
      name: 'index',
      meta: { title: '消息中心' },
      children: [
        {
          path: '/', // 每个路由都需要映射到一个组件。
          component: () => import('@/views/message-center/index'),
          name: 'index',
          children : [
            {
              path: 'reply',
              component: () => import('@/views/message-center/reply'),
              name: 'reply',
            },
            {
              path: 'at',
              component: () => import('@/views/message-center/at'),
              name: 'at',
            },
            {
              path: 'love',
              component: () => import('@/views/message-center/love'),
              name: 'love',
            },
            {
              path: 'system',
              component: () => import('@/views/message-center/system'),
              name: 'system',
            },
            {
              path: 'whisper/:id?',
              component: () => import('@/views/message-center/whisper'),
              name: 'whisper',
            }
          ]
        },
      ]
    },
    {
      path: '/record',
      component: Layout,
      children: [
        {
          path: 'index',
          component: () => import('@/views/record/index'),
          name: 'RecordIndex',
          meta: { title: '考试记录' }
        }
      ]
    },
    {
      path: '/question',
      component: Layout,
      children: [
        {
          path: 'index',
          component: () => import('@/views/question-error/index'),
          name: 'QuestionErrorIndex',
          meta: { title: '错题本' }
        }
      ]
    },
    {
      path: '/user',
      component: Layout,
      children: [
        {
          path: 'index',
          component: () => import('@/views/user-info/index'),
          name: 'UserInfo',
          meta: { title: '个人中心' }
        }
      ]
    },
    {
      path: '/user',
      component: Layout,
      children: [
        {
          path: 'message',
          component: () => import('@/views/user-info/message'),
          name: 'UserMessage',
          meta: { title: '消息中心' }
        }
      ]
    },
    { path: '/do', name: 'ExamPaperDo', component: () => import('@/views/exam/paper/do'), meta: { title: '试卷答题' } },
    { path: '/edit', name: 'ExamPaperEdit', component: () => import('@/views/exam/paper/edit'), meta: { title: '试卷批改' } },
    { path: '/read', name: 'ExamPaperRead', component: () => import('@/views/exam/paper/read'), meta: { title: '试卷查看' } },
    { path: '*', component: () => import('@/views/error-page/404'), meta: { title: '404' }
    }
  ]
})

export { router }
