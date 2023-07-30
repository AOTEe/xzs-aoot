import { post,postParam } from '@/utils/request'
export default {
  connectWebSocket: userId => post('/chatWebSocket/' + userId),
  getRecentChats: query => postParam("/api/record/getRecentChats",query),
  getRecords: query => postParam("/api/record/getRecords",query),
}
