package com.mindskip.xzs.repository;

import com.mindskip.xzs.bean.Org;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface OrgMapper {
    public List<Org> list(Org org);

    public Org selectById(String id);

    public  List<Org> getAllParents();

    public int insert(Org record);

    public int deleteById(String id);

    public int updateById(Org org);

    public int listCount(Org org);

    public List<Org>  getOrgChildren(Org org);

    public Integer getOrgChildrenCount(Org org);
}
