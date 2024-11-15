package com.sd.sdzx.common.log.aspect;

import com.sd.sdzx.common.log.annotation.Log;
import com.sd.sdzx.common.log.service.AsyncOperLogService;
import com.sd.sdzx.common.log.utils.LogUtil;
import com.sd.sdzx.model.entity.system.SysOperLog;

import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {            // 环绕通知切面类定义
    @Autowired
    private AsyncOperLogService asyncOperLogService;
    @Around(value = "@annotation(sysLog)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint , Log sysLog) {
//        String title = sysLog.title();
//        log.info("LogAspect...doAroundAdvice方法执行了"+title);
//        System.out.println("LogAspect...doAroundAdvice方法执行了"+title);

        SysOperLog sysOperLog = new SysOperLog();
        LogUtil.beforeHandleLog(sysLog,joinPoint, sysOperLog);
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();              // 执行业务方法
            LogUtil.afterHandlLog(sysLog,proceed,sysOperLog,0,null);
        } catch (Throwable e) {
            e.printStackTrace();
            LogUtil.afterHandlLog(sysLog,proceed,sysOperLog,1,e.getMessage());
            // 代码执行进入到catch中，业务方法执行产生异常
            throw new RuntimeException(e);
        }
        asyncOperLogService.saveSysOperLog(sysOperLog);
        return proceed ;                                // 返回执行结果
    }
}
