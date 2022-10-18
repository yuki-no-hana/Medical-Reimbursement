package com.murasaki.medicalinsurance.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.murasaki.medicalinsurance.dao.LoginDao;
import com.murasaki.medicalinsurance.entity.Login;
import com.murasaki.medicalinsurance.service.LoginSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.service.serviceImpl
 * @Author:Murasaki
 * @CreateTime:2021-08-11 16:48
 * @Description:
 */

@Service
public class LoginSerImpl extends ServiceImpl<LoginDao, Login> implements LoginSer {

    @Autowired
    LoginDao dao;

    @Override
    public Map<String, String> login(String userName, String password) {
        Map<String, String> map = new HashMap<>();
        int res = query().eq("username", userName).count();
        if (res == 0) {
            // 用户名不存在
            map.put("res", "-1");
            return map;
        }
        Login login = query().eq("username", userName)
                .one();
        if (password.equals(login.getPassword())) {
            // 登录成功
            if ("0".equals(login.getRoleid()))
                map.put("res", "2");        //管理员判断身份
            else map.put("res", "1");       //普通用户判断身份
            map.put("username", login.getUsername());

            return map;
        } else {
            // 登录不成功
            map.put("res", "-2");
            return map;
        }
    }
}
