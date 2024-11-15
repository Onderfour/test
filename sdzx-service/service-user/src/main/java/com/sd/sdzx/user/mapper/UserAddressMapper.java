package com.sd.sdzx.user.mapper;

import com.sd.sdzx.model.entity.user.UserAddress;
import com.sd.sdzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserAddressMapper {
    List<UserAddress> findByUserId(Long userId);

    UserAddress getById(Long id);


//    Integer save(UserAddress userAddress);

    void save(UserAddress userAddress);

    void update(UserAddress userAddress);

    void removeById(Long id);
}
