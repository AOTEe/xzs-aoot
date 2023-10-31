package com.mindskip.xzs.message_center.repository;

import com.mindskip.xzs.message_center.domain.entity.RecentChats;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecentChatsMapper {
    public RecentChats get(String userId);

    public void update(RecentChats recentChats);

    public void add(RecentChats recentChats);

}
