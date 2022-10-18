package com.murasaki.medicalinsurance.controller;

import com.murasaki.medicalinsurance.common.http.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.murasaki.medicalinsurance.service.findmypwSer;
import com.murasaki.medicalinsurance.form.findmypwForm;
import javax.validation.Valid;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/findmypw")
/**
 * 忘记密码api类
 * 王安琪
 * 2021-8-15
 */
public class findmypwCtl {
    @Autowired
    findmypwSer ser;

    @PostMapping("/yanzheng")
    public ResponseResult<Map<String, String>> lookifexist(@RequestBody @Valid findmypwForm form){
        int finalres = ser.shujubidui(form);
        //如果是1就告诉他没那个用户名，如果是2就告诉过他电话不对，如果是3就跳转！！！
        if(finalres==1){
            return ResponseResult.getMessageResult(null, "A001");//用户名不匹配
        }
        else if(finalres==2){
            return ResponseResult.getMessageResult(null, "A002");//查找到用户名没有联系电话
        }
        else if(finalres==3){
            return ResponseResult.getMessageResult(null, "A003");//提交成功
        }
        else{
            System.out.println("我是傻逼");
        }
        return ResponseResult.getErrorResult(null);
    }

}
