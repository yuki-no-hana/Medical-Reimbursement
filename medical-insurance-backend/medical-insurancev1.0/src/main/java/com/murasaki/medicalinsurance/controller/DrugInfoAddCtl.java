package com.murasaki.medicalinsurance.controller;

import cn.hutool.core.bean.BeanUtil;
import com.murasaki.medicalinsurance.common.http.ResponseResult;
import com.murasaki.medicalinsurance.entity.DrugInfoAdd;
import com.murasaki.medicalinsurance.form.DrugInfoAddForm;
import com.murasaki.medicalinsurance.service.DrugInfoAddSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/drugid")
public class DrugInfoAddCtl {

    @Autowired
    private DrugInfoAddSer ser;


    @PostMapping("/add")
    public ResponseResult<Boolean> add(@RequestBody @Valid DrugInfoAddForm form) {
        DrugInfoAdd drugInfoAdd = BeanUtil.toBean(form, DrugInfoAdd.class);
        boolean res = ser.checkAndInsert(drugInfoAdd);
        if (res) {
            return ResponseResult.getSuccessResult(true, "A006", null);
        } else {
            return ResponseResult.getMessageResult(false, "A007");
        }
    }

}
