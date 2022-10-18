package com.murasaki.medicalinsurance.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.murasaki.medicalinsurance.dao.UserinfoDao;
import com.murasaki.medicalinsurance.entity.UserInfo;
import com.murasaki.medicalinsurance.service.UserinfoSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.ServerCloneException;
import java.util.List;

@Service
public class UserinfoImpl extends ServiceImpl<UserinfoDao,UserInfo> implements UserinfoSer {
    @Autowired
    private UserinfoDao userinfoDao;
    @Override
    public List<UserInfo> listByUsername(String username) {

        return baseMapper.selectByUsername(username);



    }


    @Override
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
                          String registedinfo) {
        boolean res = userinfoDao.update(username,id,name,type,identity,codeid,birthday,nation,address,phonenum,scardnum,registedinfo);
        return res;
    }




}
