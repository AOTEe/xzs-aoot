import { post } from '@/utils/request'
export default {
  danmuList: vid => post('/api/danmu/' + vid),
}
