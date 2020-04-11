package com.zzd.api.dao;


import com.zzd.api.domain.TUser;
import com.zzd.api.domain.TUserExample;
import com.zzd.api.dto.UserDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserMapper {
    int countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(TUser record);

    int insertSelective(TUser record);

    List<TUser> selectByExample(TUserExample example);

    TUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    List<TUser> selectUserByDTO(@Param("param") UserDTO userDTO);

    int selectUserTotalByDTO(@Param("param") UserDTO userDTO);
}
