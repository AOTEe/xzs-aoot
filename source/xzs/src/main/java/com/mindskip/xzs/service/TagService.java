package com.mindskip.xzs.service;

import com.github.pagehelper.PageInfo;
import com.mindskip.xzs.domain.Tag;


import java.util.List;
import java.util.Map;

public interface TagService extends BaseService<Tag> {

    Map<String,Object> list(Tag tag);

    List<Tag> allTags();

    List<Tag> getTagsByIds(String[] ids);

}
