package com.sd.sdzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.sd.sdzx.model.dto.system.AssginRoleDto;
import com.sd.sdzx.model.dto.system.LoginDto;
import com.sd.sdzx.model.dto.system.SysUserDto;
import com.sd.sdzx.model.entity.system.SysUser;
import com.sd.sdzx.model.vo.system.LoginVo;

public interface SysUserService {
    LoginVo login(LoginDto loginDto) ;

    SysUser getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Long userId);

    void doAssign(AssginRoleDto assginRoleDto);
}
