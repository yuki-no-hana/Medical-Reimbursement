package com.murasaki.medicalinsurance.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.entity
 * @Author:Murasaki
 * @CreateTime:2021-08-12 09:58
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("drugtype")
public class DrugType {

    private static final long serialVersionUID=1L;
    @TableId("id")
    public int id;

    @TableField("drugtypeid")
    public String drugtypeid;

    @TableField("drugtype")
    public String drugtype;

    @TableField("discount")
    public double discount;
}
