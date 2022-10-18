package com.murasaki.medicalinsurance.controller;

import com.murasaki.medicalinsurance.common.http.ResponseResult;
import com.murasaki.medicalinsurance.entity.UserInfo;
import com.murasaki.medicalinsurance.form.UserinfoForm;
import com.murasaki.medicalinsurance.form.UserinfoSava;
import com.murasaki.medicalinsurance.service.UserinfoSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/userinfo")
public class UserinfoCtl {

    @Autowired
    private UserinfoSer ser;

    /* public ResponseResult<List<Druginfo>> getDruginfoManufactor(@RequestBody @Valid DruginfoForm form) {
         List<Druginfo> pager = ser.listByManufactor(form.getManufactor());
         return ResponseResult.getSuccessResult(pager);
     }*/
    @PostMapping("/getuserinfo")
    public ResponseResult<List<UserInfo>> getUserinfoUsername(@RequestBody @Valid UserinfoForm form) {
        List<UserInfo> us = ser.listByUsername(form.getUsername());
        return ResponseResult.getSuccessResult(us);
    }

    @PostMapping("/update")
    public ResponseResult<Boolean> updateByUsername(@RequestBody @Valid UserinfoSava userinfoSava){
        boolean result = ser.update(
                userinfoSava.getUsername(),
                userinfoSava.getId(),
                userinfoSava.getName(),
                userinfoSava.getType(),
                userinfoSava.getIdentity(),
                userinfoSava.getCodeid(),
                userinfoSava.getBirthday(),
                userinfoSava.getNation(),
                userinfoSava.getAddress(),
                userinfoSava.getPhonenum(),
                userinfoSava.getScardnum(),
                userinfoSava.getRegistedinfo()
        );


       /* boolean result = userinfoSava.update(userinfoSava.getUsername(),
                userinfoSava.getId(),
       userinfoSava.getName(),
       userinfoSava.getType(),
       userinfoSava.getIdentity(),
       userinfoSava.getCodeid(),
       userinfoSava.getBirthday(),
       userinfoSava.getNation(),
       userinfoSava.getAddress(),
       userinfoSava.getPhonenum(),
       userinfoSava.getScardnum(),
       userinfoSava.getRegistedinfo()
                );*/
        if(result) {
            return ResponseResult.getSuccessResult(true,"A008",null);
        } else {
            return ResponseResult.getMessageResult(false,"A009");
        }
    }







}

