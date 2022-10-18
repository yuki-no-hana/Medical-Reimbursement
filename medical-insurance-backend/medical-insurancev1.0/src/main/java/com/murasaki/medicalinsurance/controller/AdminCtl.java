package com.murasaki.medicalinsurance.controller;

import com.murasaki.medicalinsurance.common.http.ResponseResult;
import com.murasaki.medicalinsurance.common.http.StatusCode;
import com.murasaki.medicalinsurance.form.NormalTreatmentInfo;
import com.murasaki.medicalinsurance.form.SocialCardForm;
import com.murasaki.medicalinsurance.form.SpecialTreatmentInfo;
import com.murasaki.medicalinsurance.form.TreatmentUpdateForm;
import com.murasaki.medicalinsurance.service.AdminSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.controller
 * @Author:Murasaki
 * @CreateTime:2021-08-11 09:04
 * @Description:
 */

/**
 * CrossOrigin开启，默认本机8848端口可访问
 *
 * @Author: Murasaki
 */
@CrossOrigin(origins = {"http://127.0.0.1:8848"})
@RestController
@RequestMapping("/api/admin")
public class AdminCtl {
    /**
     * AdminSer依赖注入
     *
     * @Author:Murasaki
     */
    @Autowired
    AdminSer service;

    /**
     * 输入社保卡号，返回所有诊疗信息
     *
     * @param form
     * @return
     * @Author:Murasaki
     */
    @PostMapping("/assessments")
    public ResponseResult<Map<String, Object>> getAssessments(@RequestBody @Validated SocialCardForm form) {
        ResponseResult<Map<String, Object>> res = new ResponseResult<>();
        Map<String, Object> map = new HashMap<>();
        String scardnum = form.getScardnum();
        if (scardnum.length() != 16) {
            res.setMsgId("A010");
            res.setStatusCode(StatusCode.C404);
        } else {
            Map<String, Object> medicalParams = service.getMedicalParamByScardNum(scardnum);
            List<Map<String, Object>> nTreatments = service.getNTreatmentListByScardNum(scardnum);
            List<Map<String, Object>> sTreatments = service.getSTreatmentListByScardNum(scardnum);

            BigDecimal nAllTotal = service.calculateNAllTotal(nTreatments);
            BigDecimal nAllDiscounted = service.calculateNAllDiscounted(nTreatments, medicalParams);
            double nDiscountPercent = ((nAllDiscounted.divide(new BigDecimal(String.valueOf(nAllTotal)), 2, RoundingMode.HALF_UP)).multiply(new BigDecimal("100"))).doubleValue();

            BigDecimal sAllTotal = service.calculateSAllTotal(sTreatments);
            BigDecimal sAllDiscounted = service.calculateSAllDiscounted(sTreatments, medicalParams);
            double sDiscountPercent = ((sAllDiscounted.divide(new BigDecimal(String.valueOf(sAllTotal)), 2, RoundingMode.HALF_UP)).multiply(new BigDecimal("100"))).doubleValue();

            NormalTreatmentInfo normalTreatmentInfo = new NormalTreatmentInfo();
            normalTreatmentInfo.setNormallist(service.getNTreatmentListByScardNum(scardnum));
            normalTreatmentInfo.setNAllTotal(nAllTotal.doubleValue());
            normalTreatmentInfo.setNAllDiscounted(nAllDiscounted.doubleValue());
            normalTreatmentInfo.setNDiscountPercent(nDiscountPercent);

            SpecialTreatmentInfo specialTreatmentInfo = new SpecialTreatmentInfo();
            specialTreatmentInfo.setSpeciallist(service.getSTreatmentListByScardNum(scardnum));
            specialTreatmentInfo.setSAllTotal(sAllTotal.doubleValue());
            specialTreatmentInfo.setSAllDiscounted(sAllDiscounted.doubleValue());
            specialTreatmentInfo.setSDiscountPercent(sDiscountPercent);

            map.put("normal", normalTreatmentInfo);
            map.put("special", specialTreatmentInfo);

            res.setResult(map);
            res.setMsgId("A011");
            res.setStatusCode(StatusCode.C200);
        }
        return res;
    }

    /**
     * 普通审批
     *
     * @param form
     * @return
     * @Author:Murasaki
     */
    @PostMapping("/approval/normal")
    public ResponseResult<Boolean> doNormalApproval(@RequestBody @Validated TreatmentUpdateForm form) {
        ResponseResult<Boolean> responseResult = new ResponseResult<>();
        responseResult.setResult(service.doNormalApproval(form.getTreatmentIdList()));
        responseResult.setMsgId("A011");
        responseResult.setStatusCode(StatusCode.C200);
        return responseResult;
    }

    /**
     * 特检特治报销审批
     *
     * @param form
     * @return
     * @Author:Murasaki
     */
    @PostMapping("/approval/special")
    public ResponseResult<Boolean> doSpecialApproval(@RequestBody @Validated TreatmentUpdateForm form) {
        ResponseResult<Boolean> responseResult = new ResponseResult<>();
        responseResult.setResult(service.doSpecialApproval(form.getTreatmentIdList()));
        responseResult.setMsgId("A011");
        responseResult.setStatusCode(StatusCode.C200);
        return responseResult;
    }

    /**
     * 普通报销审批
     *
     * @param form
     * @return
     * @Author:Murasaki
     */
    @PostMapping("/reject/normal")
    public ResponseResult<Boolean> doRejectNormalApproval(@RequestBody @Validated TreatmentUpdateForm form) {
        ResponseResult<Boolean> responseResult = new ResponseResult<>();
        List<String> list = form.getTreatmentIdList();
        responseResult.setResult(service.doRejectNormalApproval(list));
        responseResult.setMsgId("A011");
        responseResult.setStatusCode(StatusCode.C200);
        return responseResult;
    }

    /**
     * 特检特治报销审批
     *
     * @param form
     * @return
     * @Author:Murasaki
     */
    @PostMapping("/reject/special")
    public ResponseResult<Boolean> doRejectSpecialApproval(@RequestBody @Validated TreatmentUpdateForm form) {
        ResponseResult<Boolean> responseResult = new ResponseResult<>();
        List<String> list = form.getTreatmentIdList();
        responseResult.setResult(service.doRejectSpecialApproval(list));
        responseResult.setMsgId("A011");
        responseResult.setStatusCode(StatusCode.C200);
        return responseResult;
    }

}
