package com.murasaki.medicalinsurance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.murasaki.medicalinsurance.entity.UserInfo;

import java.util.List;

public interface UserinfoSer extends IService<UserInfo> {

    public List<UserInfo> listByUsername(String username);

    public boolean update(String username,
                          Integer id,
                          String name,
                          String type,
                          String identity,
                          String codeid,
                          String birthday,
                          String nation,
                          String address,
                          String phonenum,
                          String scardnum,
                          String registedinfo
    );


}
