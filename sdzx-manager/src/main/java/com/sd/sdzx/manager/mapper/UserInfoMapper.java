package com.sd.sdzx.manager.mapper;


import com.sd.sdzx.model.dto.system.SysUserDto;
import com.sd.sdzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
     void insert(UserInfo userInfo);
     List<UserInfo> findByPage(SysUserDto sysUserDto);

     void updateById(UserInfo userInfo);
     void deleteById(Long id);
     List<UserInfo> findAll();


}
