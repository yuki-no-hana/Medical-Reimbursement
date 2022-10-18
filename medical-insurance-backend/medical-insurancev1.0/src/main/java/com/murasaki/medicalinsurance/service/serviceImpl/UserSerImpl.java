package com.murasaki.medicalinsurance.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.murasaki.medicalinsurance.dao.TreatmentDao;
import com.murasaki.medicalinsurance.dao.UserDao;
import com.murasaki.medicalinsurance.entity.UserInfo;
import com.murasaki.medicalinsurance.service.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.service.serviceImpl
 * @Author:Murasaki
 * @CreateTime:2021-08-11 13:34
 * @Description:
 */

@Service
public class UserSerImpl extends ServiceImpl<UserDao, UserInfo> implements UserSer {
    /**
     * UserDao依赖注入
     * Author: Murasaki
     */
    @Autowired
    private UserDao dao;
    /**
     * TreatmentDao依赖注入
     * Author: Murasaki
     */
    @Autowired
    private TreatmentDao treatmentDao;

    /**
     * 查询医保卡医疗参数
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

    /**
     * 查询非特检特治项目
     * Author: Murasaki
     *
     * @param scardnum
     * @return
     */
    @Override
    public List<Map<String, Object>> getNTreatmentListByScardNum(String scardnum) {
        List<Map<String, Object>> res;
        if (null == scardnum || "".equals(scardnum)) {
            return null;
        } else {
            res = dao.getMyTreatmentsByScardNum(scardnum, "S00", "N");
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
     * 查询特检特治项目
     * Author: Murasaki
     *
     * @param scardnum
     * @return
     */
    @Override
    public List<Map<String, Object>> getSTreatmentListByScardNum(String scardnum) {
        List<Map<String, Object>> res;
        if (null == scardnum || "".equals(scardnum)) {
            return null;
        } else {
            res = dao.getMyTreatmentsByScardNum(scardnum, "S00", "Y");
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
     * 计算非特检特治项目合计计费
     * Author: Murasaki
     *
     * @param treatments
     * @return
     */
    @Override
    public BigDecimal calculateNAllTotal(List<Map<String, Object>> treatments) {
        BigDecimal treatmentPrice = new BigDecimal("0");
        if (null != treatments) {
            for (Map treatment : treatments) {
                treatmentPrice = treatmentPrice.add(new BigDecimal(String.valueOf(treatment.get("totalprice"))));
            }
        }
        return treatmentPrice;
    }

    /**
     * 预估非特检特治项目报销金额
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
        BigDecimal res = new BigDecimal("0");
        if (null != treatments && null != medicalParams) {
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
            /*用空间来换时间，不建议写一坨，可读性差
             *可以封装实体
             *老师推荐通过数组来分段存，先把值分隔开每一段都是什么值
             *然后循环来带入公式计算
             */
            //
            res = (((((total.subtract(secondLevel)).setScale(2)).max(new BigDecimal("0")))
                    .divide(((total.subtract(secondLevel)).setScale(2))
                            .max(new BigDecimal(String.valueOf("0.01"))))
            ).multiply((total.min(end)).subtract(secondLevel)).multiply(thirdDiscount)
            ).add(
                    ((((total.subtract(firstLevel)).setScale(2)).max(new BigDecimal("0")))
                            .divide(((total.subtract(firstLevel)).setScale(2))
                                    .max(new BigDecimal(String.valueOf("0.01")))
                            )).multiply((total.min(secondLevel)).subtract(firstLevel)).multiply(secondDiscount)
            ).add(
                    ((((total.subtract(start)).setScale(2)).max(new BigDecimal("0")))
                            .divide(((total.subtract(start)).setScale(2)).max(new BigDecimal(String.valueOf("0.01")))
                            )).multiply((total.min(firstLevel)).subtract(start)).multiply(firstDiscount)
            ).add(dDiscountedPrice);
            res.setScale(2, RoundingMode.HALF_UP);
        }
        return res;
    }

    /**
     * 计算特检特治项目总金额
     * Author: Murasaki
     *
     * @param treatments
     * @return
     */
    @Override
    public BigDecimal calculateSAllTotal(List<Map<String, Object>> treatments) {
        BigDecimal treatmentPrice = new BigDecimal("0");
        if (null != treatments) {
            for (Map treatment : treatments) {
                treatmentPrice = treatmentPrice.add(new BigDecimal(String.valueOf(treatment.get("totalprice"))));
            }
        }
        return treatmentPrice;
    }

    /**
     * 预估特检特治项目报销金额
     *
     * @param treatments
     * @param medicalParams
     * @return
     */
    @Override
    public BigDecimal calculateSAllDiscounted(List<Map<String, Object>> treatments, Map<String, Object> medicalParams) {
        BigDecimal total = new BigDecimal("0");
        BigDecimal dDiscountedPrice = new BigDecimal("0");
        BigDecimal res = new BigDecimal("0");
        if (null != treatments && null != medicalParams) {
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
            res = (((((total.subtract(secondLevel)).setScale(2)).max(new BigDecimal(0)))
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
        }
        return res;
    }

    @Override
    public Boolean submitNormal(List<String> list) {
        Boolean res = false;
        String shenpiId = UUID.randomUUID().toString().replaceAll("-", "");
        if (!list.isEmpty()) {
            for (String treatmentid : list) {
                res = treatmentDao.setAndUpdate(treatmentid, shenpiId, "S01");
            }
        }
        return res;
    }

    @Override
    public Boolean submitSpecial(List<String> list) {
        Boolean res = false;
        String shenpiId = UUID.randomUUID().toString().replaceAll("-", "");
        if (!list.isEmpty()) {
            for (String treatmentid : list) {
                res = treatmentDao.setAndUpdate(treatmentid, shenpiId, "S01");
            }
        }
        return res;
    }
}
