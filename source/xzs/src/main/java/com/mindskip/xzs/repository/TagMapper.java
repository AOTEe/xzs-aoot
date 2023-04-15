package com.mindskip.xzs.repository;



import com.mindskip.xzs.domain.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TagMapper {
    public List<Tag> list(Tag tag);

    public Tag selectById(String id);

    public int insert(Tag record);

    public int deleteById(String id);

    public int updateById(Tag tag);

    public int listCount(Tag tag);

}
