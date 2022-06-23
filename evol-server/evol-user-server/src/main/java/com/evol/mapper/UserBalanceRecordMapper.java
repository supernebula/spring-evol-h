package com.evol.mapper;

import com.evol.domain.model.UserBalanceRecord;
import com.evol.domain.model.UserBalanceRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserBalanceRecordMapper {
    long countByExample(UserBalanceRecordExample example);

    int deleteByExample(UserBalanceRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserBalanceRecord record);

    int insertSelective(UserBalanceRecord record);

    List<UserBalanceRecord> selectByExample(UserBalanceRecordExample example);

    UserBalanceRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserBalanceRecord record, @Param("example") UserBalanceRecordExample example);

    int updateByExample(@Param("record") UserBalanceRecord record, @Param("example") UserBalanceRecordExample example);

    int updateByPrimaryKeySelective(UserBalanceRecord record);

    int updateByPrimaryKey(UserBalanceRecord record);
}