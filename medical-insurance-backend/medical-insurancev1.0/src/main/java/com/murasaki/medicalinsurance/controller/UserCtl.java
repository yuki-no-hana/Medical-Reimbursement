package com.murasaki.medicalinsurance.controller;

import com.murasaki.medicalinsurance.common.http.ResponseResult;
import com.murasaki.medicalinsurance.common.http.StatusCode;
import com.murasaki.medicalinsurance.form.NormalTreatmentInfo;
import com.murasaki.medicalinsurance.form.SocialCardForm;
import com.murasaki.medicalinsurance.form.SpecialTreatmentInfo;
import com.murasaki.medicalinsurance.form.TreatmentSubmitForm;
import com.murasaki.medicalinsurance.service.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.controller
 * @Author:Murasaki
 * @CreateTime:2021-08-11 09:05
 * @Description:
 */

/**
 * CrossOrigin开启，默认本机8848端口可访问
 *
 * @Author: Murasaki
 */
@CrossOrigin(origins = {"http://127.0.0.1:8848"})
@RestController
@RequestMapping("/api/user")
public class UserCtl {
    /**
     * UserSer 依赖注入
     *
     * @Author: Murasaki
     */
    @Autowired
    private UserSer service;

    /**
     * 用户输入医保卡号查看自己的所有未报销的诊疗单
     * 同时会给出预估总金额，预估报销金额，以及预估用户自费金额
     *
     * @param form
     * @return
     * @Author: Murasaki
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

            //不存在
            if (null == medicalParams || medicalParams.isEmpty()) {
                res.setMsgId("A010");
                res.setStatusCode(StatusCode.C404);
            } else {
                if (!nTreatments.isEmpty()) {
                    BigDecimal nAllTotal = service.calculateNAllTotal(nTreatments);
                    BigDecimal nAllDiscounted = service.calculateNAllDiscounted(nTreatments, medicalParams);
                    double nDiscountPercent = ((nAllDiscounted.divide(new BigDecimal(String.valueOf(nAllTotal)), 2, RoundingMode.HALF_UP)).multiply(new BigDecimal("100"))).doubleValue();

                    NormalTreatmentInfo normalTreatmentInfo = new NormalTreatmentInfo();
                    normalTreatmentInfo.setNormallist(service.getNTreatmentListByScardNum(scardnum));
                    normalTreatmentInfo.setNAllTotal(nAllTotal.doubleValue());
                    normalTreatmentInfo.setNAllDiscounted(nAllDiscounted.doubleValue());
                    normalTreatmentInfo.setNDiscountPercent(nDiscountPercent);

                    map.put("normal", normalTreatmentInfo);
                }
                if (!sTreatments.isEmpty()) {

                    BigDecimal sAllTotal = service.calculateSAllTotal(sTreatments);
                    BigDecimal sAllDiscounted = service.calculateSAllDiscounted(sTreatments, medicalParams);
                    double sDiscountPercent = ((sAllDiscounted.divide(new BigDecimal(String.valueOf(sAllTotal)), 2, RoundingMode.HALF_UP)).multiply(new BigDecimal("100"))).doubleValue();

                    SpecialTreatmentInfo specialTreatmentInfo = new SpecialTreatmentInfo();
                    specialTreatmentInfo.setSpeciallist(service.getSTreatmentListByScardNum(scardnum));
                    specialTreatmentInfo.setSAllTotal(sAllTotal.doubleValue());
                    specialTreatmentInfo.setSAllDiscounted(sAllDiscounted.doubleValue());
                    specialTreatmentInfo.setSDiscountPercent(sDiscountPercent);

                    map.put("special", specialTreatmentInfo);
                }
                res.setResult(map);
                res.setMsgId("A011");
                res.setStatusCode(StatusCode.C200);
            }
        }
        return res;
    }

    /**
     * 按普通诊疗项目和特检特治项目分成两个大订单提交，每个订单有唯一的审批ID，订单内所有诊疗信息共享该审批ID
     *
     * @param form
     * @return
     * @Author: Murasaki
     */
    @PostMapping("/treatments/submitall")
    public ResponseResult<List<Boolean>> submitAll(@RequestBody TreatmentSubmitForm form) {
        ResponseResult<List<Boolean>> responseResult = new ResponseResult<>();
        List<Boolean> list = new ArrayList<>();

        List<String> normalList = form.getNormalIds();
        List<String> specialList = form.getSpecialIds();

        if (null != normalList) {
            list.add(service.submitNormal(normalList));
        }
        if (null != specialList && (!specialList.isEmpty())) {
            list.add(service.submitSpecial(specialList));
        }
        responseResult.setResult(list);
        responseResult.setStatusCode(StatusCode.C200);
        responseResult.setMsgId("A011");
        return responseResult;
    }

    /**
     * 按照医保卡号获取基本参数显示到前端下边提示框内
     *
     * @param form
     * @return
     * @Author: Murasaki
     */
    @PostMapping("/medicalparam")
    public ResponseResult<Map<String, Object>> getMedicalParams(@RequestBody @Validated SocialCardForm form) {
        ResponseResult<Map<String, Object>> res = new ResponseResult<>();
        Map<String, Object> map = new HashMap<>();
        String scardnum = form.getScardnum();
        if (scardnum.length() != 16) {
            res.setMsgId("A010");
            res.setStatusCode(StatusCode.C404);
        } else {
            res.setResult(service.getMedicalParamByScardNum(scardnum));
            res.setMsgId("A011");
            res.setStatusCode(StatusCode.C200);
        }
        return res;
    }
}
