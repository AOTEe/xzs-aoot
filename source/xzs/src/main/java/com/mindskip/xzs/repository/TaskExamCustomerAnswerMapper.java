package com.mindskip.xzs.repository;

import com.mindskip.xzs.domain.TaskExamCustomerAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskExamCustomerAnswerMapper extends BaseMapper<TaskExamCustomerAnswer> {

    TaskExamCustomerAnswer getByTUid(@Param("tid") String tid, @Param("uid") String uid);

    List<TaskExamCustomerAnswer> selectByTUid(@Param("taskIds") List<String> taskIds, @Param("uid") String uid);
}
