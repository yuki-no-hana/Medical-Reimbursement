package com.murasaki.medicalinsurance.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



@Data
public class DrugDeleteForm {
    @NotNull
    @Min(1)
    private Integer id;     //药品删除表，有参数且最少为1，即有药才能删除
}