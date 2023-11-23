import {post,formDataPost} from '@/utils/request'

export default {
  postApi: (url,query) => post(url, query),
  formDataPostApi: (url,query) => formDataPost(url, query)
}
