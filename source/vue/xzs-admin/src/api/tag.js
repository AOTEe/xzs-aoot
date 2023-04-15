import { post } from '@/utils/request'

export default {
  list: query => post('/api/admin/education/tag/list',query),
  pageList: query => post('/api/admin/education/tag/list', query),
  edit: query => post('/api/admin/education/tag/edit', query),
  select: id => post('/api/admin/education/tag/select/' + id),
  deleteSubject: id => post('/api/admin/education/tag/delete/' + id)
}
