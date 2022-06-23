package com.evol.mapper;

import com.evol.domain.model.NetOrder;
import com.evol.domain.model.NetOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NetOrderMapper {
    long countByExample(NetOrderExample example);

    int deleteByExample(NetOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetOrder record);

    int insertSelective(NetOrder record);

    List<NetOrder> selectByExample(NetOrderExample example);

    NetOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetOrder record, @Param("example") NetOrderExample example);

    int updateByExample(@Param("record") NetOrder record, @Param("example") NetOrderExample example);

    int updateByPrimaryKeySelective(NetOrder record);

    int updateByPrimaryKey(NetOrder record);
}