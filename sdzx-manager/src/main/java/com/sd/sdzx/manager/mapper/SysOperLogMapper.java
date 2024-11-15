package com.sd.sdzx.manager.mapper;

import com.sd.sdzx.model.dto.system.SysOperLogDto;
import com.sd.sdzx.model.dto.system.SysRoleDto;
import com.sd.sdzx.model.entity.system.SysOperLog;
import com.sd.sdzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysOperLogMapper {
     void insert(SysOperLog sysOperLog);
     List<SysOperLog> findAllLog();
     List<SysOperLog> findByPage(SysOperLogDto sysOperLogDto);

}
