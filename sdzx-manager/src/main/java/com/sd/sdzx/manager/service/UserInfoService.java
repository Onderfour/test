package com.sd.sdzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.sd.sdzx.model.dto.system.SysUserDto;
import com.sd.sdzx.model.entity.user.UserInfo;

import java.util.List;


public interface UserInfoService {
    void insert(UserInfo userInfo);
    PageInfo<UserInfo> findByPage(SysUserDto sysUserDto,Integer page, Integer limit);
    void updateById(UserInfo userInfo);
    void deleteById(Long id);
    List<UserInfo> findAll();

}
