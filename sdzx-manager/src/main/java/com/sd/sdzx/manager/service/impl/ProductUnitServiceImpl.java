package com.sd.sdzx.manager.service.impl;

import com.sd.sdzx.manager.mapper.ProductUnitMapper;
import com.sd.sdzx.manager.service.ProductUnitService;
import com.sd.sdzx.model.entity.base.ProductUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductUnitServiceImpl implements ProductUnitService {
    @Autowired
    private ProductUnitMapper productUnitMapper ;

    @Override
    public List<ProductUnit> findAll() {
        return productUnitMapper.findAll() ;
    }
}
