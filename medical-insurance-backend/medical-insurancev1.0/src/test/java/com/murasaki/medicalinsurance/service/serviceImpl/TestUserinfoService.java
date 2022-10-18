package com.murasaki.medicalinsurance.service.serviceImpl;


import com.murasaki.medicalinsurance.entity.UserInfo;
import com.murasaki.medicalinsurance.service.UserinfoSer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestUserinfoService {
@Autowired
    private UserinfoSer service;

@Test

    public void testUserinfo(){
    UserInfo userinfo = new UserInfo();
    userinfo.setId(3);
    userinfo.setUsername("wangwu");
    userinfo.setName("王五");
    userinfo.setIdentity("231181199909099999");
    userinfo.setCodeid("G03");
    userinfo.setType("身份证");
    userinfo.setBirthday("20200518");
    userinfo.setNation("中国");
    userinfo.setAddress("黑河");
    userinfo.setPhonenum("18696969696");
    userinfo.setScardnum("123456789123");
    userinfo.setRegistedinfo("发烧");
    service.save(userinfo);
      }
    @Test
    public void testQuerywhere(){
        List<UserInfo> list = service.query().like("username","zhangsan").list();
        System.out.println(list);
    }
   /* public void testQuerywhere(){
        List<Druginfo> list = ser.query().like("drugname","双黄连").list();
        System.out.println(list);
    }*/
}
