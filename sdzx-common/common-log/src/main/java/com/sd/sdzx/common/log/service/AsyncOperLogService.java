package com.sd.sdzx.common.log.service;

import com.github.pagehelper.PageInfo;
import com.sd.sdzx.model.dto.system.SysOperLogDto;
import com.sd.sdzx.model.entity.system.SysOperLog;

import java.util.List;
import java.util.Map;

public interface AsyncOperLogService {			// 保存日志数据
     void saveSysOperLog(SysOperLog sysOperLog) ;
     List<SysOperLog> findAllLog();
//    Map<String, Object> findAllLog(Long Id);


    PageInfo<SysOperLog> findByPage(SysOperLogDto sysOperLogDto, Integer pageNum, Integer pageSize);
}
