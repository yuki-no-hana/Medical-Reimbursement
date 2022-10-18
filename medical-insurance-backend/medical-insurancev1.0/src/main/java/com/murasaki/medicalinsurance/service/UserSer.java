package com.murasaki.medicalinsurance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.murasaki.medicalinsurance.entity.UserInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.service.serviceImpl
 * @Author:Murasaki
 * @CreateTime:2021-08-11 13:34
 * @Description:
 */
public interface UserSer extends IService<UserInfo> {

    /**
     * 输入医保卡号获取医保卡医疗参数信息
     *
     * @param scardnum
     * @return
     * @Author:Murasaki
     */
    Map<String, Object> getMedicalParamByScardNum(String scardnum);

    /**
     * 输入医保卡号获取未报销的非特检特治诊疗单
     *
     * @param scardnum
     * @return
     * @Author:Murasaki
     */
    List<Map<String, Object>> getNTreatmentListByScardNum(String scardnum);

    /**
     * 输入医保卡号获取未报销的特检特治诊疗单
     *
     * @param scardnum
     * @return
     * @Author:Murasaki
     */
    List<Map<String, Object>> getSTreatmentListByScardNum(String scardnum);

    /**
     * 计算诊疗单上项目的总价
     *
     * @param treatments
     * @return
     * @Author:Murasaki
     */
    BigDecimal calculateNAllTotal(List<Map<String, Object>> treatments);

    /**
     * 预估非特检特治诊疗项目可报销的金额
     *
     * @param treatments
     * @param medicalParams
     * @return
     * @Author:Murasaki
     */
    BigDecimal calculateNAllDiscounted(List<Map<String, Object>> treatments, Map<String, Object> medicalParams);

    /**
     * 计算特检特治诊疗单总价
     *
     * @param treatments
     * @return
     * @Author:Murasaki
     */
    BigDecimal calculateSAllTotal(List<Map<String, Object>> treatments);

    /**
     * 预估特检特治项目可报销的金额
     *
     * @param treatments
     * @param medicalParams
     * @return
     * @Author:Murasaki
     */
    BigDecimal calculateSAllDiscounted(List<Map<String, Object>> treatments, Map<String, Object> medicalParams);

    /**
     * 提交非特检特治项目
     *
     * @param list
     * @return
     * @Author:Murasaki
     */
    Boolean submitNormal(List<String> list);

    /**
     * 提交特检特治项目
     *
     * @param list
     * @return
     * @Author:Murasaki
     */
    Boolean submitSpecial(List<String> list);
}
