package com.murasaki.medicalinsurance.controller;

import com.murasaki.medicalinsurance.common.http.ResponseResult;
import com.murasaki.medicalinsurance.form.resetpwForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.murasaki.medicalinsurance.service.resetpwSer;

import javax.validation.Valid;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/resetpw")
public class resetpwCtl {
    @Autowired
    resetpwSer ser;

    @PostMapping("/reset")
    public ResponseResult<Map<String, String>> chongzhi(@RequestBody @Valid resetpwForm form) {
        int res = ser.reset(form);
        if (res == 1) {
            return ResponseResult.getMessageResult(null, "same");//密码相同
        } else if (res == 2) {
            return ResponseResult.getMessageResult(null, "success");//密码不同修改成功
        } else {
            System.out.println("我是sb");
        }
        return ResponseResult.getErrorResult(null);
    }
}