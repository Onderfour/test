package com.sd.sdzx.user.service.impl;

import com.sd.sdzx.common.exception.GuiguException;
import com.sd.sdzx.model.entity.user.UserAddress;
import com.sd.sdzx.model.vo.common.ResultCodeEnum;
import com.sd.sdzx.user.mapper.RegionMapper;
import com.sd.sdzx.user.mapper.UserAddressMapper;
import com.sd.sdzx.user.service.RegionService;
import com.sd.sdzx.user.service.UserAddressService;
import com.sd.sdzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private RegionService regionService;
    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<UserAddress> findUserAddressList() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        return userAddressMapper.findByUserId(userId);
    }

    @Override
    public UserAddress getById(Long id) {
        return userAddressMapper.getById(id);
    }


    @Override
    public void save(UserAddress userAddress) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        userAddress.setUserId(userId);
        String provinceName = regionMapper.findNameByCode(userAddress.getProvinceCode());
        String cityName     = regionMapper.findNameByCode(userAddress.getCityCode());
        String districtName = regionMapper.findNameByCode(userAddress.getDistrictCode());

        StringBuilder fullAddressBuilder = new StringBuilder();
        fullAddressBuilder.append(provinceName)
                .append(cityName)
                .append(districtName);

        String fullAddress = fullAddressBuilder.toString();
        userAddress.setFullAddress(fullAddress);
        userAddressMapper.save(userAddress);

//        UserAddress address = userAddressMapper.getById(userId);
//
//        if (address.getUserId()>=5){
//            throw new GuiguException(ResultCodeEnum.ADDRESS_LINIT);
//        }
//
//        String provinceName = regionService.getNameByCode(address.getProvinceCode());
//        String cityName     = regionService.getNameByCode(address.getCityCode());
//        String districtName = regionService.getNameByCode(address.getDistrictCode());
//
//        userAddress.setProvinceCode(provinceName);
//        userAddress.setCityCode(cityName);
//        userAddress.setDistrictCode(districtName);
//
//        userAddress.setUserId(userId);
//        userAddress.setIsDefault(0);
//
//        userAddress.setPhone(userAddress.getAddress());
//        userAddress.setTagName(userAddress.getTagName());
//        userAddress.setAddress(userAddress.getAddress());
//        userAddress.setFullAddress(userAddress.getFullAddress());
//        userAddress.setCreateTime(new Date());
//        userAddress.setUpdateTime(new Date());

//        Integer rows = userAddressMapper.save(userAddress);
//        if (rows!=1){
//            System.err.println("插入用户收货地址发生未知异常");
//        }

    }

    @Override
    public void updateAddress(UserAddress userAddress) {
        String provinceName = regionMapper.findNameByCode(userAddress.getProvinceCode());
        String cityName     = regionMapper.findNameByCode(userAddress.getCityCode());
        String districtName = regionMapper.findNameByCode(userAddress.getDistrictCode());
        StringBuilder fullAddressBuilder = new StringBuilder();
        fullAddressBuilder.append(provinceName)
                .append(cityName)
                .append(districtName);

        String fullAddress = fullAddressBuilder.toString();
        userAddress.setFullAddress(fullAddress);

    userAddressMapper.update(userAddress);
    }

    @Override
    public void removeById(Long id) {
        userAddressMapper.removeById(id);
    }
}
