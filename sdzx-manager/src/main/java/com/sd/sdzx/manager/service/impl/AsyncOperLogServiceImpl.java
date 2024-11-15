package com.sd.sdzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdzx.common.log.service.AsyncOperLogService;
import com.sd.sdzx.manager.mapper.SysOperLogMapper;
import com.sd.sdzx.model.dto.system.SysOperLogDto;
import com.sd.sdzx.model.entity.system.SysOperLog;
import com.sd.sdzx.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsyncOperLogServiceImpl implements AsyncOperLogService {

    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    @Async      // 异步执行保存日志操作
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }

    @Override
    public List<SysOperLog> findAllLog() {
        return sysOperLogMapper.findAllLog();
    }


    @Override
    public PageInfo<SysOperLog> findByPage(SysOperLogDto sysOperLogDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum , pageSize) ;
        List<SysOperLog> sysRoleList = sysOperLogMapper.findByPage(sysOperLogDto) ;
        PageInfo<SysOperLog> pageInfo = new PageInfo(sysRoleList) ;
        return pageInfo;
    }

}