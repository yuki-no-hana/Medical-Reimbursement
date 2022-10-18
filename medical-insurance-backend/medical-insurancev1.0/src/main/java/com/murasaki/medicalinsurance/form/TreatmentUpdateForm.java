package com.murasaki.medicalinsurance.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.form
 * @Author:Murasaki
 * @CreateTime:2021-08-14 16:53
 * @Description:
 */
@Data
public class TreatmentUpdateForm {
    @NotEmpty
    List<String> treatmentIdList;
}
