package com.sd.sdzx.user.controller;

import com.sd.sdzx.model.entity.base.Region;
import com.sd.sdzx.model.vo.common.Result;
import com.sd.sdzx.model.vo.common.ResultCodeEnum;
import com.sd.sdzx.user.service.RegionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "地区接口")
@RestController
@RequestMapping("api/user/region")
public class RegionController {
//    http://139.198.163.91:8400/api/user/region/findByParentCode/410000 GET
    @Autowired
    private RegionService regionService;
    @GetMapping("findByParentCode/{code}")
//    @GetMapping({"/",""})
    public Result<List<Region>> findByParentCode(@PathVariable("code")Integer code){
        List<Region> byParent = regionService.getByParent(code);

        return Result.build(byParent , ResultCodeEnum.SUCCESS) ;
    }


}
