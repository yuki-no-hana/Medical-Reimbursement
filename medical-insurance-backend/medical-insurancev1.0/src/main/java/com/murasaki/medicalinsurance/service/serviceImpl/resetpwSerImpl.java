package com.murasaki.medicalinsurance.service.serviceImpl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.murasaki.medicalinsurance.dao.LoginDao;
import com.murasaki.medicalinsurance.entity.Login;
import com.murasaki.medicalinsurance.form.resetpwForm;
import com.murasaki.medicalinsurance.service.resetpwSer;
import org.springframework.stereotype.Service;

@Service
public class resetpwSerImpl extends ServiceImpl<LoginDao, Login> implements resetpwSer {
    public int reset(resetpwForm form){
        Login l1 =query().eq("username",form.getUsername()).one();
        if(l1.getPassword().equals(form.getPassword())){
            return 1;//密码相同，修改失败
        }
        else {
            l1.setPassword(form.getPassword());

            saveOrUpdate(l1);  //修改密码
            return 2;//修改成功
        }

    }
}
