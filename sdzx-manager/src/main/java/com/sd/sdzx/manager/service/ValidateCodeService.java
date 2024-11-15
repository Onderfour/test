package com.sd.sdzx.manager.service;

import com.sd.sdzx.model.vo.system.ValidateCodeVo;

public interface ValidateCodeService {
    // 获取验证码图片
    public abstract ValidateCodeVo generateValidateCode();
}
