package com.murasaki.medicalinsurance.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


/**
 * 查询时输入没有要求
 */
@Data
public class DruginfoForm {
    public DruginfoForm(String drugId,String drugName,String manuFactor){
        this.drugId = drugId;
        this.drugName = drugName;
        this.manuFactor = manuFactor;
    }

    private String drugId;

    private String drugName;

    private String manuFactor;
}
