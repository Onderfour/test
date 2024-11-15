package com.sd.sdzx.user.service.impl;

import com.sd.sdzx.model.entity.base.Region;
import com.sd.sdzx.user.mapper.RegionMapper;
import com.sd.sdzx.user.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionMapper regionMapper;
    @Override
    public List<Region> getByParent(Integer code) {
        List<Region> byParent = regionMapper.findByParent(code);
//        for (Region region:byParent) {
//            region.setId(null);
//            region.setParentCode(null);
//        }
        return byParent;
    }

    @Override
    public String getNameByCode(String code) {
        return regionMapper.findNameByCode(code);
    }
}
