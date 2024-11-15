package com.sd.sdzx.user.service;

import com.github.pagehelper.PageInfo;
import com.sd.sdzx.model.dto.h5.UserLoginDto;
import com.sd.sdzx.model.dto.h5.UserRegisterDto;
import com.sd.sdzx.model.entity.user.UserBrowseHistory;
import com.sd.sdzx.model.entity.user.UserCollect;
import com.sd.sdzx.model.vo.h5.UserInfoVo;

public interface UserInfoService {
    void register(UserRegisterDto userRegisterDto);

    String login(UserLoginDto userLoginDto);

    UserInfoVo getCurrentUserInfo(String token);


    Boolean findUserisCollect(Long skuId);

    PageInfo<UserCollect> findUserBrowseHistoryPage(Integer page, Integer limit);

    PageInfo<UserBrowseHistory> findUserCollectPage(Integer page, Integer limit);

    //新增用户收藏
    void saveUserCollect(Long id);

    void updatecancelCollect(long skuId);

    //    远程调用：获取浏览量最多的商品
    UserBrowseHistory getMostFrequentSkuId();

    void savecollect(long skuId);
}
