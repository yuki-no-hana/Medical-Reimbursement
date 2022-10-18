package com.murasaki.medicalinsurance.form;

import lombok.Data;

import java.util.List;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.form
 * @Author:Murasaki
 * @CreateTime:2021-08-14 18:32
 * @Description:
 */
@Data
public class TreatmentSubmitForm {

    List<String> normalIds;

    List<String> specialIds;
}
