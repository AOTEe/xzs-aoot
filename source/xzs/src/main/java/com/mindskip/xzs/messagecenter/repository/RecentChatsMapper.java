package com.mindskip.xzs.messagecenter.repository;

import com.mindskip.xzs.messagecenter.bean.RecentChats;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecentChatsMapper {
    public RecentChats get(String userId);

    public void update(RecentChats recentChats);

    public void add(RecentChats recentChats);

}
