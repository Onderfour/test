package com.sd.sdzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.sd.sdzx.common.log.service.AsyncOperLogService;
import com.sd.sdzx.manager.service.UserInfoService;
import com.sd.sdzx.model.dto.system.SysOperLogDto;
import com.sd.sdzx.model.dto.system.SysUserDto;
import com.sd.sdzx.model.entity.product.Brand;
import com.sd.sdzx.model.entity.system.SysOperLog;
import com.sd.sdzx.model.entity.user.UserInfo;
import com.sd.sdzx.model.vo.common.Result;
import com.sd.sdzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/admin/users/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<UserInfo>> findByPage(SysUserDto sysUserDto,
            @PathVariable Integer page, @PathVariable Integer limit) {
        PageInfo<UserInfo> pageInfo = userInfoService.findByPage(sysUserDto,page, limit);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

    @PostMapping("save")
    public Result save(@RequestBody UserInfo userInfo) {
        userInfoService.insert(userInfo);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @PutMapping("updateById")
    public Result updateById(@RequestBody UserInfo userInfo) {
        userInfoService.updateById(userInfo);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        userInfoService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @GetMapping("/findAll")
    public Result findAll() {
        List<UserInfo> list = userInfoService.findAll();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }
}
