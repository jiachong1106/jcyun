package com.jc.admin.dao;

import com.jc.admin.bean.ProjectType;
import com.jc.admin.bean.ProjectTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectTypeMapper {
    long countByExample(ProjectTypeExample example);

    int deleteByExample(ProjectTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectType record);

    int insertSelective(ProjectType record);

    List<ProjectType> selectByExample(ProjectTypeExample example);

    ProjectType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectType record, @Param("example") ProjectTypeExample example);

    int updateByExample(@Param("record") ProjectType record, @Param("example") ProjectTypeExample example);

    int updateByPrimaryKeySelective(ProjectType record);

    int updateByPrimaryKey(ProjectType record);
}