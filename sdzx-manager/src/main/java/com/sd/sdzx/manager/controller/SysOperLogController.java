package com.sd.sdzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.sd.sdzx.common.log.service.AsyncOperLogService;
import com.sd.sdzx.model.dto.system.SysOperLogDto;
import com.sd.sdzx.model.dto.system.SysRoleDto;
import com.sd.sdzx.model.entity.system.SysOperLog;
import com.sd.sdzx.model.entity.system.SysRole;
import com.sd.sdzx.model.vo.common.Result;
import com.sd.sdzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/admin/system/sysLog")
public class SysOperLogController {
    @Autowired
    private AsyncOperLogService asyncOperLogService;

    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysOperLog>> findByPage(@RequestBody SysOperLogDto sysOperLogDto ,
                                                   @PathVariable(value = "pageNum") Integer pageNum ,
                                                   @PathVariable(value = "pageSize") Integer pageSize) {
        PageInfo<SysOperLog> pageInfo = asyncOperLogService.findByPage(sysOperLogDto , pageNum , pageSize) ;
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }
    @GetMapping(value = "/findAllRoles")
    public Result<Map<String , Object>> findAllRoles() {
        List<SysOperLog> allLog = asyncOperLogService.findAllLog();
        return Result.build(allLog , ResultCodeEnum.SUCCESS)  ;
    }

}
