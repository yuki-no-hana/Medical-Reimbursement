package com.murasaki.medicalinsurance.form;


import lombok.Data;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotBlank;

/**
 * 重置密码接收表单
 * 王安琪
 * 2021-8-15
 */
@Data
public class resetpwForm {
    @NotBlank
    @Length(max =18)
    private String username;

    @NotBlank
    @Length(min=5,max=16)
    private String password;
}
