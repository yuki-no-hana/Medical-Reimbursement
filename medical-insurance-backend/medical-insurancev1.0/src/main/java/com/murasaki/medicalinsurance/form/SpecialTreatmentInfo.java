package com.murasaki.medicalinsurance.form;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.form
 * @Author:Murasaki
 * @CreateTime:2021-08-11 13:47
 * @Description:
 */
@Data
public class SpecialTreatmentInfo implements Serializable{

    private static volatile long serialVersionUID=1L;

    List<Map<String, Object>> speciallist;

    double sAllTotal;

    double sAllDiscounted;

    double sDiscountPercent;

}
