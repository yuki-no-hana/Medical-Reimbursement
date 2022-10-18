package com.murasaki.medicalinsurance.controller;

import com.murasaki.medicalinsurance.common.http.ResponseResult;
import com.murasaki.medicalinsurance.form.LoginForm;
import com.murasaki.medicalinsurance.service.LoginSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.controller
 * @Author:Murasaki
 * @CreateTime:2021-08-11 11:45
 * @Description:
 */

@CrossOrigin
@RestController
public class LoginCtl {

    @Autowired
    LoginSer service;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/api/user/login")
    public ResponseResult<List<Map<String,Object>>> login(@RequestBody @Validated LoginForm form){
        Map<String, String> map = service.login(form.getUsername(), form.getPassword());
        if ("-2".equals(map.get("res"))) {
            return ResponseResult.getMessageResult(null,"A002");//密码错误
        }else if ("-1".equals(map.get("res"))){
            return ResponseResult.getMessageResult(null,"A001");//用户名错误
        }else if ("1".equals(map.get("res"))){
            return ResponseResult.getMessageResult(null,"A012");//用户登陆成功
        }
        else if ("2".equals(map.get("res"))){
            return ResponseResult.getMessageResult(null,"A013");//管理员登陆成功
        }

        return ResponseResult.getErrorResult(null);
    }
}
