import { post } from '@/utils/request'

export default {
  getUserPageList: query => post('/api/admin/user/page/list', query),
  getUserEventPageList: query => post('/api/admin/user/event/page/list', query),
  createOrg: query => post('/api/admin/org/put', query),
  selectUser: id => post('/api/admin/user/select/' + id),
  getCurrentUser: () => post('/api/admin/user/current'),
  updateUser: query => post('/api/admin/user/update', query),
  changeStatus: id => post('/api/admin/user/changeStatus/' + id),
  deleteUser: id => post('/api/admin/user/delete/' + id),
  selectByUserName: query => post('/api/admin/user/selectByUserName', query),
  getOrgPageList: query => post('/api/admin/org/list', query),
  selectOrg: id => post('/api/admin/org/select/' + id),
  getAllOrgParents:query => post('/api/admin/org/getAllParents', query),
  deleteOrg:id => post('/api/admin/org/delete/'+id),
  updateOrg: query => post('/api/admin/org/update', query),
  getOrgTree: query => post('/api/admin/org/orgTree', query),
  childrenList: query => post('/api/admin/org/childrenList', query),
}
