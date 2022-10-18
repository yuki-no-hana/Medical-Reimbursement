package com.murasaki.medicalinsurance.controller;

import com.murasaki.medicalinsurance.common.http.ResponseResult;
import com.murasaki.medicalinsurance.common.http.StatusCode;
import com.murasaki.medicalinsurance.service.DrugTypeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/drugtype")
public class DrugTypeCtl {
    @Autowired
    private DrugTypeSer ser;

    @GetMapping("/search")
    public ResponseResult<List<Map<String, Object>>> search() {
        List<Map<String, Object>> res = ser.queryByDrugTypeName();
        return ResponseResult.getSuccessResult(res);
    }

    /**
     * created钩子调用的api，获取通用的药品报销信息
     *
     * @return
     * @Author:Murasaki
     */
    @PostMapping("/common/typeanddiscount")
    public ResponseResult<List<Map<String, Object>>> getDrugtypeAndDiscount() {
        ResponseResult<List<Map<String, Object>>> responseResult = new ResponseResult<>();
        responseResult.setResult(ser.queryDrugTypeAndDiscount());
        responseResult.setStatusCode(StatusCode.C200);
        responseResult.setMsgId("A011");
        return responseResult;
    }
}

