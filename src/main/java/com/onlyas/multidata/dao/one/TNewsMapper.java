package com.onlyas.multidata.dao.one;

import com.onlyas.multidata.domain.one.TNews;
import com.onlyas.multidata.domain.one.TNewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TNewsMapper {
    long countByExample(TNewsExample example);

    int deleteByExample(TNewsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TNews record);

    int insertSelective(TNews record);

    List<TNews> selectByExample(TNewsExample example);

    TNews selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TNews record, @Param("example") TNewsExample example);

    int updateByExample(@Param("record") TNews record, @Param("example") TNewsExample example);

    int updateByPrimaryKeySelective(TNews record);

    int updateByPrimaryKey(TNews record);
}