package com.sd.sdzx.user.mapper;

import com.sd.sdzx.model.entity.base.Region;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface RegionMapper {
    List<Region> findByParent(Integer code);
    String findNameByCode(String code);
}
