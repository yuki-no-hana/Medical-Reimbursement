package com.murasaki.medicalinsurance.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class findmypwForm {
    /**
     * 映射属性：账号
     */
    @NotBlank
    @Length(max= 18)
    private String username;

    /**
     * 映射属性：联系电话
     */
    @NotBlank
    @Length(max = 11,min = 11)
    private String phonenum;
}
