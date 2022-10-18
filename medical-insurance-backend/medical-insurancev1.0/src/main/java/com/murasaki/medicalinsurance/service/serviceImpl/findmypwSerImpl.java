package com.murasaki.medicalinsurance.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.murasaki.medicalinsurance.dao.findmypwDao;
import com.murasaki.medicalinsurance.entity.findmypw;
import com.murasaki.medicalinsurance.form.findmypwForm;
import com.murasaki.medicalinsurance.service.findmypwSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 忘记密码service实现类
 * 王安琪
 * 2021-8-15
 */

@Service
public class findmypwSerImpl extends ServiceImpl<findmypwDao,findmypw> implements findmypwSer{
    @Autowired
    private findmypwDao dao;

    public int shujubidui(findmypwForm form){
        findmypw find =new findmypw();
        int res1 = query().eq("username", form.getUsername()).count();
        //int res2 = query().eq("phonenum",form.getPhonenum()).count();//可能出现不是这个人的电话号码
        if (res1==0){
            return 1; //不存在用户名
        }
        else{   //存在用户名
            findmypw f1 = query().eq("username", form.getUsername())
                    .one();
            if(f1.getPhonenum().equals(form.getPhonenum())) {
                // 登录成功
                return 3;
            } else {
                // 登录不成功,联系电话不正确
                return 2;
            }
        }

    }

}
