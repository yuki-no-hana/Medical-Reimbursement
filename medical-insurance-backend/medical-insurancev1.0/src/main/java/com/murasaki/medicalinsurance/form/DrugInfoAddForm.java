package com.murasaki.medicalinsurance.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
public class DrugInfoAddForm {

        @NotNull
        private int id;

        private String drugid;
        @NotNull
        private String drugname;
        @NotNull
        @Digits(integer = 7, fraction = 2)//price：7位整数2位小数
        private Double price;
        @NotNull
        private Character qualification;
        @NotNull
        private Character approval;
        @NotNull
        private String manufactor;
        @NotNull
        @Length(min = 3, max = 3) //drugtypeid为三位
        private String drugtypeid;

}
