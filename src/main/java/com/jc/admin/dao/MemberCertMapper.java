package com.jc.admin.dao;

import com.jc.admin.bean.MemberCert;
import com.jc.admin.bean.MemberCertExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberCertMapper {
    long countByExample(MemberCertExample example);

    int deleteByExample(MemberCertExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberCert record);

    int insertSelective(MemberCert record);

    List<MemberCert> selectByExample(MemberCertExample example);

    MemberCert selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberCert record, @Param("example") MemberCertExample example);

    int updateByExample(@Param("record") MemberCert record, @Param("example") MemberCertExample example);

    int updateByPrimaryKeySelective(MemberCert record);

    int updateByPrimaryKey(MemberCert record);
}