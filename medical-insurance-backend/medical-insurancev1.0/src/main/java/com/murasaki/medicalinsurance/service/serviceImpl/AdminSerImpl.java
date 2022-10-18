package com.murasaki.medicalinsurance.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.murasaki.medicalinsurance.dao.TreatmentDao;
import com.murasaki.medicalinsurance.dao.UserDao;
import com.murasaki.medicalinsurance.entity.UserInfo;
import com.murasaki.medicalinsurance.service.AdminSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.service.serviceImpl
 * @Author:Murasaki
 * @CreateTime:2021-08-13 09:14
 * @Description:
 */
@Service
public class AdminSerImpl extends ServiceImpl<UserDao, UserInfo> implements AdminSer {

    /**
     * 注入userDao依赖
     * Author: Murasaki
     */
    @Autowired
    private UserDao dao;

    /**
     * 注入TreatmentDao依赖
     * Author: Murasaki
     */
    @Autowired
    private TreatmentDao treatmentDao;

    /**
     * 根据医保卡号获取医保卡医疗参数信息
     * Author: Murasaki
     *
     * @param scardnum
     * @return
     */
    @Override
    public Map<String, Object> getMedicalParamByScardNum(String scardnum) {
        Map<String, Object> res;
        if (null == scardnum || "".equals(scardnum)) {
            res = null;
        } else {
            res = dao.getMyMParamByScardNum(scardnum);
        }
        return res;
    }

    @Override
    public List<Map<String, Object>> getNTreatmentListByScardNum(String scardnum) {
        List<Map<String, Object>> res;
        if (null == scardnum || "".equals(scardnum)) {
            res = null;
        } else {
            res = dao.getMyTreatmentsByScardNum(scardnum, "S01", "N");
            for (Map treatment : res) {
                double total = (double) treatment.get("totalprice");
                double discount = (double) treatment.get("discount");
                double discountedPart = new BigDecimal(total).multiply(new BigDecimal(discount)).doubleValue();
                treatment.put("disprice", discountedPart);
                treatment.put("needtopay", total - discountedPart);
            }
        }
        return res;
    }

    /**
     * 获取特检特治项目列表
     * Author: Murasaki
     *
     * @param scardnum
     * @return List<Map < String, Object>>  treatments
     */
    @Override
    public List<Map<String, Object>> getSTreatmentListByScardNum(String scardnum) {
        List<Map<String, Object>> res;
        if (null == scardnum || "".equals(scardnum)) {
            res = null;
        } else {
            res = dao.getMyTreatmentsByScardNum(scardnum, "S01", "Y");
            for (Map treatment : res) {
                double total = (double) treatment.get("totalprice");
                double discount = (double) treatment.get("discount");
                double discountedPart = new BigDecimal(total).multiply(new BigDecimal(discount)).doubleValue();
                treatment.put("disprice", discountedPart);
                treatment.put("needtopay", total - discountedPart);
            }
        }
        return res;
    }

    /**
     * 计算非特检特治类型的诊疗信息总价
     * Author: Murasaki
     *
     * @param treatments
     * @return
     */
    @Override
    public BigDecimal calculateNAllTotal(List<Map<String, Object>> treatments) {
        BigDecimal treatmentPrice = new BigDecimal("0");
        if (null == treatments || treatments.isEmpty()) {
            return treatmentPrice;
        }
        for (Map treatment : treatments) {
            treatmentPrice = treatmentPrice.add(new BigDecimal(String.valueOf(treatment.get("totalprice"))));
        }
        return treatmentPrice;
    }

    /**
     * 计算非特检特治项目报销金额
     * Author: Murasaki
     *
     * @param treatments
     * @param medicalParams
     * @return
     */
    @Override
    public BigDecimal calculateNAllDiscounted(List<Map<String, Object>> treatments, Map<String, Object> medicalParams) {
        BigDecimal total = new BigDecimal("0");
        BigDecimal dDiscountedPrice = new BigDecimal("0");
        if (null == treatments || treatments.isEmpty() || null == medicalParams || medicalParams.isEmpty()) {
            return dDiscountedPrice;
        }

        for (Map treatment : treatments) {
            dDiscountedPrice = dDiscountedPrice.add(new BigDecimal(String.valueOf(treatment.get("disprice"))));
            total = total.add(new BigDecimal(String.valueOf(treatment.get("needtopay"))));
        }
        BigDecimal start = new BigDecimal(String.valueOf(medicalParams.get("start")));
        BigDecimal end = new BigDecimal(String.valueOf(medicalParams.get("end")));
        BigDecimal firstLevel = new BigDecimal(String.valueOf(medicalParams.get("firstlevel")));
        BigDecimal secondLevel = new BigDecimal(String.valueOf(medicalParams.get("secondlevel")));
        BigDecimal firstDiscount = new BigDecimal(String.valueOf(medicalParams.get("firstdiscount")));
        BigDecimal secondDiscount = new BigDecimal(String.valueOf(medicalParams.get("seconddiscount")));
        BigDecimal thirdDiscount = new BigDecimal(String.valueOf(medicalParams.get("thirddiscount")));
        /* 这是一坨翔一样又臭又硬的代码 ， 读之前请做好心里准备
         * 对照手写的公式会比较容易理解
         * 代码大意 :
         * 起付线下不报销  ，封顶线上不报销
         * 起步线--level1部分   按第一档比例报销;
         * level1--level2部分  按第二档比例报销
         * level2--封顶线部分   按第三档比例报销
         */
        BigDecimal res = (((((total.subtract(secondLevel)).setScale(2)).max(new BigDecimal(0)))
                .divide(((total.subtract(secondLevel)).setScale(2))
                        .max(new BigDecimal(String.valueOf(0.01))))
        ).multiply((total.min(end)).subtract(secondLevel)).multiply(thirdDiscount)
        ).add(
                ((((total.subtract(firstLevel)).setScale(2)).max(new BigDecimal(0)))
                        .divide(((total.subtract(firstLevel)).setScale(2))
                                .max(new BigDecimal(String.valueOf(0.01)))
                        )).multiply((total.min(secondLevel)).subtract(firstLevel)).multiply(secondDiscount)
        ).add(
                ((((total.subtract(start)).setScale(2)).max(new BigDecimal(0)))
                        .divide(((total.subtract(start)).setScale(2)).max(new BigDecimal(String.valueOf(0.01)))
                        )).multiply((total.min(firstLevel)).subtract(start)).multiply(firstDiscount)
        ).add(dDiscountedPrice);
        res.setScale(2, RoundingMode.HALF_UP);
        return res;
    }

    /**
     * 计算特检特治部分总金额
     * Author: Murasaki
     *
     * @param treatments
     * @return
     */
    @Override
    public BigDecimal calculateSAllTotal(List<Map<String, Object>> treatments) {
        BigDecimal treatmentPrice = new BigDecimal("0");
        if (null == treatments || treatments.isEmpty()) {
            return treatmentPrice;
        }

        for (Map treatment : treatments) {
            treatmentPrice = treatmentPrice.add(new BigDecimal(String.valueOf(treatment.get("totalprice"))));
        }
        return treatmentPrice;
    }

    /**
     * 计算特检特治部分报销的金额
     * Author: Murasaki
     *
     * @param treatments
     * @param medicalParams
     * @return
     */
    @Override
    public BigDecimal calculateSAllDiscounted(List<Map<String, Object>> treatments, Map<String, Object> medicalParams) {
        BigDecimal total = new BigDecimal("0");
        BigDecimal dDiscountedPrice = new BigDecimal("0");

        if (null == treatments || treatments.isEmpty() || null == medicalParams || medicalParams.isEmpty()) {
            return dDiscountedPrice;
        }

        for (Map treatment : treatments) {
            dDiscountedPrice = dDiscountedPrice.add(new BigDecimal(String.valueOf(treatment.get("disprice"))));
            total = total.add(new BigDecimal(String.valueOf(treatment.get("needtopay"))));
        }
        BigDecimal start = new BigDecimal(String.valueOf(medicalParams.get("start")));
        BigDecimal end = new BigDecimal(String.valueOf(medicalParams.get("end")));
        BigDecimal firstLevel = new BigDecimal(String.valueOf(medicalParams.get("firstlevel")));
        BigDecimal secondLevel = new BigDecimal(String.valueOf(medicalParams.get("secondlevel")));
        BigDecimal firstDiscount = new BigDecimal(String.valueOf(medicalParams.get("firstdiscount")));
        BigDecimal secondDiscount = new BigDecimal(String.valueOf(medicalParams.get("seconddiscount")));
        BigDecimal thirdDiscount = new BigDecimal(String.valueOf(medicalParams.get("thirddiscount")));
        /* 这是一坨翔一样又臭又硬的代码 ， 读之前请做好心里准备
         * 对照手写的公式会比较容易理解
         * 代码大意 :
         * 起付线下不报销  ，封顶线上不报销
         * 起步线--level1部分   按第一档比例报销;
         * level1--level2部分  按第二档比例报销
         * level2--封顶线部分   按第三档比例报销
         */
        BigDecimal res = (((((total.subtract(secondLevel)).setScale(2)).max(new BigDecimal(0)))
                .divide(((total.subtract(secondLevel)).setScale(2))
                        .max(new BigDecimal(String.valueOf(0.01))))
        ).multiply((total.min(end)).subtract(secondLevel)).multiply(thirdDiscount)
        ).add(
                ((((total.subtract(firstLevel)).setScale(2)).max(new BigDecimal(0)))
                        .divide(((total.subtract(firstLevel)).setScale(2))
                                .max(new BigDecimal(String.valueOf(0.01)))
                        )).multiply((total.min(secondLevel)).subtract(firstLevel)).multiply(secondDiscount)
        ).add(
                ((((total.subtract(start)).setScale(2)).max(new BigDecimal(0)))
                        .divide(((total.subtract(start)).setScale(2)).max(new BigDecimal(String.valueOf(0.01)))
                        )).multiply((total.min(firstLevel)).subtract(start)).multiply(firstDiscount)
        ).add(dDiscountedPrice);
        res.setScale(2, RoundingMode.HALF_UP);
        return res;
    }

    /**
     * 非特检特治项目审批通过
     * Author: Murasaki
     *
     * @param list
     * @return
     */
    @Override
    public Boolean doNormalApproval(List<String> list) {
        Boolean res = false;
        if (null == list || list.isEmpty()) {
            return res;
        }

        Date date = new Date(System.currentTimeMillis());
        if (!list.isEmpty()) {
            for (String treatmentid : list) {
                res = treatmentDao.checkOut(treatmentid, "S03", date);
            }
        }
        return res;
    }

    /**
     * 特检特治项目审批通过
     *
     * @param list
     * @return
     */
    @Override
    public Boolean doSpecialApproval(List<String> list) {
        Boolean res = false;
        if (null == list || list.isEmpty()) {
            return res;
        }

        Date date = new Date(System.currentTimeMillis());
        if (!list.isEmpty()) {
            for (String treatmentid : list) {
                res = treatmentDao.checkOut(treatmentid, "S03", date);
            }
        }
        return res;
    }

    /**
     * 非特检特治项目审批不通过
     * Author: Murasaki
     *
     * @param list
     * @return
     */
    @Override
    public Boolean doRejectNormalApproval(List<String> list) {
        Boolean res = false;
        if (null == list || list.isEmpty()) {
            return res;
        }

        Date date = new Date(System.currentTimeMillis());
        if (!list.isEmpty()) {
            for (String treatmentid : list) {
                res = treatmentDao.checkOut(treatmentid, "S02", date);
            }
        }
        return res;
    }

    /**
     * 特检特治项目审批不通过
     * Author: Murasaki
     *
     * @param list
     * @return
     */
    @Override
    public Boolean doRejectSpecialApproval(List<String> list) {
        Boolean res = false;
        if (null == list || list.isEmpty()) {
            return res;
        }

        Date date = new Date(System.currentTimeMillis());
        if (!list.isEmpty()) {
            for (String treatmentid : list) {
                res = treatmentDao.checkOut(treatmentid, "S02", date);
            }
        }
        return res;
    }
}
