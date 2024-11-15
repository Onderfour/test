package com.sd.sdzx.user.controller;

import com.sd.sdzx.model.dto.h5.UserRegisterDto;
import com.sd.sdzx.model.entity.user.UserAddress;
import com.sd.sdzx.model.vo.common.Result;
import com.sd.sdzx.model.vo.common.ResultCodeEnum;
import com.sd.sdzx.user.service.UserAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户地址接口")
@RestController
@RequestMapping(value="/api/user/userAddress")
@SuppressWarnings({"unchecked", "rawtypes"})
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;



    @Operation(summary = "获取用户地址列表")
    @GetMapping("auth/findUserAddressList")
    public Result<List<UserAddress>> findUserAddressList() {
        List<UserAddress> list = userAddressService.findUserAddressList();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }
    @Operation(summary = "获取地址信息")
    @GetMapping("getUserAddress/{id}")
    public UserAddress getUserAddress(@PathVariable Long id) {
        return userAddressService.getById(id);
    }
//    add
    @Operation(summary = "添加用户地址")
    @PostMapping("auth/save")
    public Result register(@RequestBody UserAddress userAddress) {
        userAddressService.save(userAddress);
    return Result.build(null, ResultCodeEnum.SUCCESS);
    }
//    /auth/updateByld
    @Operation(summary = "更新用户地址")
    @PutMapping("auth/updateById")
    public Result update(@RequestBody UserAddress userAddress) {
        userAddressService.updateAddress(userAddress);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    //auth/removeByld
    @Operation(summary = "删除用户地址")
    @DeleteMapping("auth/removeById/{id}")
    public Result delete(@PathVariable Long id) {
        userAddressService.removeById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
