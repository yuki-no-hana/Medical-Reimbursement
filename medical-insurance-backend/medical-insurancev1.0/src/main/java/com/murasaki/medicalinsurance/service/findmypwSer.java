package com.murasaki.medicalinsurance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.murasaki.medicalinsurance.entity.findmypw;
import com.murasaki.medicalinsurance.form.findmypwForm;

/**
 * 忘记密码service接口
 * 王安琪
 * 2021-8-15
 */
public interface findmypwSer extends IService<findmypw> {
    public int shujubidui(findmypwForm form);
}
