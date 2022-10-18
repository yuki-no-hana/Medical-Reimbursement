package com.murasaki.medicalinsurance.form;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.form
 * @Author:Murasaki
 * @CreateTime:2021-08-11 13:46
 * @Description:
 */
@Data
public class NormalTreatmentInfo implements Serializable {

    private static volatile long serialVersionUID=1L;

    List<Map<String, Object>> normallist;

    double nAllTotal;

    double nAllDiscounted;

    double nDiscountPercent;

}
