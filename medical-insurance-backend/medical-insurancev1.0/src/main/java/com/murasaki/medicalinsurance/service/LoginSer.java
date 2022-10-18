package com.murasaki.medicalinsurance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.murasaki.medicalinsurance.entity.Login;

import java.util.Map;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.service
 * @Author:Murasaki
 * @CreateTime:2021-08-11 16:47
 * @Description:
 */
public interface LoginSer extends IService<Login> {
    Map<String, String> login(String userName, String password);
}
