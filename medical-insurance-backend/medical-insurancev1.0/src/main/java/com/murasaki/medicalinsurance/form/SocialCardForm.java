package com.murasaki.medicalinsurance.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.form
 * @Author:Murasaki
 * @CreateTime:2021-08-14 15:14
 * @Description:
 */
@Data
public class SocialCardForm {
    @NotBlank
    String scardnum;
}
