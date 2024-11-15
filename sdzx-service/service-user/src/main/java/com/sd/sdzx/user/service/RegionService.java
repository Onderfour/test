package com.sd.sdzx.user.service;

import com.sd.sdzx.model.entity.base.Region;

import java.util.List;

public interface RegionService {
    List<Region> getByParent(Integer code);
    String getNameByCode(String code);
}
