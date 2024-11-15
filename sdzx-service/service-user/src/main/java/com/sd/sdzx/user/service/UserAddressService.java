package com.sd.sdzx.user.service;

import com.sd.sdzx.model.entity.user.UserAddress;

import java.util.List;

public interface UserAddressService {
    List<UserAddress> findUserAddressList();

    UserAddress getById(Long id);
    void save( UserAddress userAddress);

    void updateAddress(UserAddress userAddress);

    void removeById(Long id);
}
