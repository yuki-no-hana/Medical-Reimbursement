package com.murasaki.medicalinsurance.controller;



import com.murasaki.medicalinsurance.common.http.ResponseResult;
import com.murasaki.medicalinsurance.form.DrugDeleteForm;
import com.murasaki.medicalinsurance.form.DruginfoForm;
import com.murasaki.medicalinsurance.service.DruginfoSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/drugid")
public class DruginfoCtl {
    @Autowired
    private DruginfoSer ser;

    /**
     * 查询药品
     * @param form
     * @return
     */
    @PostMapping("/getdruginfopagerlist")
    public ResponseResult<List<Map<String,Object>>> getDruginfoPagerList(@RequestBody @Valid DruginfoForm form){
        List<Map<String ,Object>> list = ser.queryPagerByCond(form.getDrugId(), form.getDrugName(), form.getManuFactor());
        return ResponseResult.getSuccessResult(list);
    }

    /**
     * 删除药品
     * @param form
     * @return
     */
    @PostMapping("/delete")
    public ResponseResult<Boolean> deletedrug(@RequestBody @Valid DrugDeleteForm form) {
        boolean res = ser.removeById(form.getId());
        if (res) {
            return ResponseResult.getSuccessResult(true, "A004", null);
        } else {
            return ResponseResult.getMessageResult(false, "A005");
        }
    }


}