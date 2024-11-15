package com.sd.sdzx.user.controller;

import com.github.pagehelper.PageInfo;
import com.sd.sdzx.model.dto.h5.UserLoginDto;
import com.sd.sdzx.model.dto.h5.UserRegisterDto;
import com.sd.sdzx.model.entity.user.UserBrowseHistory;
import com.sd.sdzx.model.entity.user.UserCollect;
import com.sd.sdzx.model.vo.common.Result;
import com.sd.sdzx.model.vo.common.ResultCodeEnum;
import com.sd.sdzx.model.vo.h5.UserInfoVo;
import com.sd.sdzx.user.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "会员用户接口")
@RestController
@RequestMapping("api/user/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Operation(summary = "会员注册")
    @PostMapping("register")
    public Result register(@RequestBody UserRegisterDto userRegisterDto) {
        userInfoService.register(userRegisterDto);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @Operation(summary = "会员登录")
    @PostMapping("login")
    public Result login(@RequestBody UserLoginDto userLoginDto) {
        String token = userInfoService.login(userLoginDto);
        return Result.build(token,
                ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("auth/getCurrentUserInfo")
    public Result<UserInfoVo> getCurrentUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        UserInfoVo userInfoVo = userInfoService.getCurrentUserInfo(token) ;
        return Result.build(userInfoVo, ResultCodeEnum.SUCCESS) ;
    }
    @Operation(summary = "新增商品收藏信息")
    @GetMapping("isCollect/{skuId}")
    public Result<UserInfoVo> saveUserCollect(@PathVariable("skuId") String id) {
//        if ("undefined".equals(id)){
        if ("undefined".equals(id)){
            return Result.build(false , ResultCodeEnum.SUCCESS) ;
        }else {
            //保存商品收藏信息
            userInfoService.saveUserCollect(Long.valueOf(id));
            //查询是否收藏
            Boolean bool = userInfoService.findUserisCollect(Long.valueOf(id));

            return Result.build(bool , ResultCodeEnum.SUCCESS) ;
        }
    }

    @Operation(summary = "商品浏览信息分页展示")
    @GetMapping("auth/findUserBrowseHistoryPage/{page}/{limit}")
    public Result<UserInfoVo> findUserBrowseHistoryPage(@PathVariable Integer page, @PathVariable Integer limit) {
        PageInfo<UserCollect> pageInfo = userInfoService.findUserBrowseHistoryPage(page, limit);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "收藏商品分页展示")
    @GetMapping("auth/findUserCollectPage/{page}/{limit}")
    public Result<UserInfoVo> findUserCollectPage(@PathVariable Integer page, @PathVariable Integer limit) {
        PageInfo<UserBrowseHistory> pageInfo = userInfoService.findUserCollectPage(page, limit);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "取消收藏")
    @GetMapping("auth/cancelCollect/{skuId}")
    public Result updatecancelCollect(@PathVariable String skuId) {
        // 参数校验，确保skuId不为空
        if (skuId == null || skuId.isEmpty() || skuId.equals("undefined")) {
            // 获取当前浏览量最多的商品并取消收藏
            userInfoService.updatecancelCollect(userInfoService.getMostFrequentSkuId().getSkuId());
        } else {
            long id = skuId.equals("undefined") ? userInfoService.getMostFrequentSkuId().getSkuId() : Long.valueOf(skuId);
            userInfoService.updatecancelCollect(id);
        }
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "添加收藏")
    @GetMapping("/auth/collect/{skuId}")
    public Result savecollect(@PathVariable String skuId) {
        // 参数校验，确保skuId不为空
        if (skuId == null || skuId.isEmpty() || skuId.equals("undefined")) {
            // 获取当前浏览量最多的商品
            UserBrowseHistory userBrowseHistory = userInfoService.getMostFrequentSkuId();
            userInfoService.savecollect(userBrowseHistory.getSkuId());
        } else {
            // 使用三元运算符简化代码逻辑
            long id = skuId.equals("undefined") ? userInfoService.getMostFrequentSkuId().getSkuId() : Long.valueOf(skuId);
            userInfoService.savecollect(id);
        }
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    /**
     * @Description: 远程调用：获取浏览量最多的商品
     */
    @Operation(summary = "获取浏览量最多的商品")
    @GetMapping("auth/BrowseHistory")
    public UserBrowseHistory getByBrowseHistory() {
        UserBrowseHistory userBrowseHistory = userInfoService.getMostFrequentSkuId();
        return userBrowseHistory;
    }

}
