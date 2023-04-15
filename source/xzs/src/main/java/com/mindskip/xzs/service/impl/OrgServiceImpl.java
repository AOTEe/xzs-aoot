package com.mindskip.xzs.service.impl;

import com.github.pagehelper.PageHelper;
import com.mindskip.xzs.bean.Org;
import com.mindskip.xzs.repository.OrgMapper;
import com.mindskip.xzs.service.OrgService;
import com.mindskip.xzs.utility.DateTimeUtil;
import com.mindskip.xzs.utility.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrgServiceImpl  implements OrgService {

    @Autowired
    OrgMapper orgMapper;

    @Override
    public int deleteById(String id) {
        return orgMapper.deleteById(id);
    }



    @Override
    public int insert(Org record) {
        record.setUuid(String.valueOf(UUID.randomUUID()));
        record.setCreateTime(DateTimeUtil.dateFormat(new Date()));
        return orgMapper.insert(record);
    }

    @Override
    public int insertByFilter(Org record) {
        return 0;
    }



    @Override
    public Org selectById(String id) {

        return orgMapper.selectById(id);
    }

    @Override
    public int updateByIdFilter(Org record) {
        return 0;
    }

    @Override
    public int updateById(Org record) {

        return orgMapper.updateById(record);
    }

    @Override
    public  Map<String,Object>  list(Org org) {


        PageHelper.startPage(org.getPageIndex(),org.getPageSize());

        //数据集合
        List<Org> orgList = orgMapper.list(org);
        //数据总数
        Integer total = orgMapper.listCount(org);


        Map<String,Object> resMap = new HashMap();
        resMap.put("list",orgList);
        resMap.put("total",total);
        resMap.put("pageNum",org.getPageIndex());

        return ResponseUtil.success(resMap);
    }

    @Override
    public List getAllParents() {
        List<Org> orgList = orgMapper.getAllParents();
        return  orgList;
    }


    public Map<String,Object> getOrgChildren(Org org) {

        //总数
        Integer total = orgMapper.getOrgChildrenCount(org);
        //分页数据
        PageHelper.startPage(org.getPageIndex(),org.getPageSize());
        List<Org> orgChildren = orgMapper.getOrgChildren(org);
        Map<String,Object> resMap = new HashMap();
        resMap.put("list",orgChildren);
        resMap.put("total",total);
        resMap.put("pageNum",org.getPageIndex());
        return resMap;
    }

    public List<Map> getOrgTree(){

        List<Org> orgList = orgMapper.list(null);

        List<Map> treeList = new ArrayList<>();
        for (Org org : orgList) {
            Map<String,Object> treeNode = new HashMap<>();
            treeNode.put("id",org.getUuid());
            treeNode.put("label",org.getOrgName());
            treeNode.put("children",null);
            List children = new ArrayList();
            for (Org item : orgList) {
                if (org.getUuid().equals(item.getBelongTo())){
                    Map<String,Object> childrenNode = new HashMap<>();
                    childrenNode.put("id",item.getUuid());
                    childrenNode.put("label",item.getOrgName());
                    childrenNode.put("children",null);
                    children.add(childrenNode);
                }
            }
            if (children.size()>0){
                treeNode.put("children",children);
                treeList.add(treeNode);
            }
        }
        return treeList;
    }

}
