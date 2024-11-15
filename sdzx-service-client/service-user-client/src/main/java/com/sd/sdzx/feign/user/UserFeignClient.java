package com.sd.sdzx.feign.user;

import com.sd.sdzx.model.entity.user.UserAddress;
import com.sd.sdzx.model.entity.user.UserBrowseHistory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-user")
public interface UserFeignClient {

    @GetMapping("/api/user/userAddress/getUserAddress/{id}")
    public abstract UserAddress getUserAddress(@PathVariable(value="id") Long id) ;

    @GetMapping("api/user/userInfo/auth/BrowseHistory")
    public UserBrowseHistory getByBrowseHistory();

}