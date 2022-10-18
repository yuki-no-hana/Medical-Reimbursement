package com.murasaki.medicalinsurance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.murasaki.medicalinsurance.entity.Login;
import com.murasaki.medicalinsurance.form.resetpwForm;

/**
 * 重置密码service接口类
 * 王安琪
 * 2021-8-15
 */

public interface resetpwSer extends IService<Login> {
    public int reset(resetpwForm form);
}
