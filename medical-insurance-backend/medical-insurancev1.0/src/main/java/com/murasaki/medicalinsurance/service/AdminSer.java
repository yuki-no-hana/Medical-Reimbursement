package com.murasaki.medicalinsurance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.murasaki.medicalinsurance.entity.UserInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.service
 * @Author:Murasaki
 * @CreateTime:2021-08-13 09:14
 * @Description:
 */
public interface AdminSer extends IService<UserInfo> {

    /**
     * 输入医保卡号查询医疗参数
     *
     * @param scardnum
     * @return
     * @Author:Murasaki
     */
    Map<String, Object> getMedicalParamByScardNum(String scardnum);

    /**
     * 输入医保卡号，获取所有已提交的非特检特治诊疗项目
     *
     * @param scardnum
     * @return
     * @Author:Murasaki
     */
    List<Map<String, Object>> getNTreatmentListByScardNum(String scardnum);

    /**
     * 输入医保卡号，互殴所有已提交的特检特治诊疗项目
     *
     * @param scardnum
     * @return
     * @Author:Murasaki
     */
    List<Map<String, Object>> getSTreatmentListByScardNum(String scardnum);

    /**
     * 计算所有非特检特治项目总价
     *
     * @param treatments
     * @return
     * @Author:Murasaki
     */
    BigDecimal calculateNAllTotal(List<Map<String, Object>> treatments);

    /**
     * 计算所有非特检特治项目报销金额
     *
     * @param treatments
     * @param medicalParams
     * @return
     * @Author:Murasaki
     */
    BigDecimal calculateNAllDiscounted(List<Map<String, Object>> treatments, Map<String, Object> medicalParams);

    /**
     * 计算所有特检特治项目总金额
     *
     * @param treatments
     * @return
     * @Author:Murasaki
     */
    BigDecimal calculateSAllTotal(List<Map<String, Object>> treatments);

    /**
     * 计算所有特检特治项目总报销金额
     *
     * @param treatments
     * @param medicalParams
     * @return
     * @Author:Murasaki
     */
    BigDecimal calculateSAllDiscounted(List<Map<String, Object>> treatments, Map<String, Object> medicalParams);

    /**
     * 通过所有普通项目审批
     *
     * @param list
     * @return
     * @Author:Murasaki
     */
    Boolean doNormalApproval(List<String> list);

    /**
     * 通过所有特检特治项目的审批
     *
     * @param list
     * @return
     * @Author:Murasaki
     */
    Boolean doSpecialApproval(List<String> list);

    /**
     * 拒绝所有非特检特治项目的审批
     *
     * @param list
     * @return
     * @Author:Murasaki
     */
    Boolean doRejectNormalApproval(List<String> list);

    /**
     * 拒绝所有特检特治项目的审批
     *
     * @param list
     * @return
     * @Author:Murasaki
     */
    Boolean doRejectSpecialApproval(List<String> list);
}
