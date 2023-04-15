package com.mindskip.xzs.service;

import com.mindskip.xzs.bean.Org;

import java.util.List;
import java.util.Map;

public interface OrgService extends BaseService<Org> {

    public Map<String,Object> list(Org org);

    public List getAllParents();

}
