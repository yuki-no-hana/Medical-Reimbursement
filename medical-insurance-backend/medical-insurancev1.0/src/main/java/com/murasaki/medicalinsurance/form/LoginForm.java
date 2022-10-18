package com.murasaki.medicalinsurance.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.form
 * @Author:Murasaki
 * @CreateTime:2021-08-10 15:48
 * @Description:
 */
@Data
public class LoginForm {
    @NotNull
    @Length(min = 1,max = 20)
    String username;

    @NotNull
    @Length(min = 5,max = 16)
    String password;

}
