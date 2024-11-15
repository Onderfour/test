package com.sd.sdzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdzx.manager.mapper.UserInfoMapper;
import com.sd.sdzx.manager.service.UserInfoService;
import com.sd.sdzx.model.dto.system.SysUserDto;
import com.sd.sdzx.model.entity.product.Brand;
import com.sd.sdzx.model.entity.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public void insert(UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
    }

    @Override
    public PageInfo<UserInfo> findByPage(SysUserDto sysUserDto,Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<UserInfo> userList = userInfoMapper.findByPage(sysUserDto) ;
        return new PageInfo(userList);
    }



    @Override
    public void updateById(UserInfo userInfo) {
    userInfoMapper.updateById(userInfo);
    }

    @Override
    public void deleteById(Long id) {
    userInfoMapper.deleteById(id);
    }

    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.findAll();
    }
}
