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
 * @CreateTime:2021-08-12 09:53
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("druginfo")
public class DrugInfo {

    private static final long serialVersionUID=1L;
    @TableId("id")
    public int id;

    @TableField("drugid")
    public String drugid;

    @TableField("drugname")
    public String drugname;

    @TableField("price")
    public double price;

    @TableField("qualification")
    public String qualification;

    @TableField("approval")
    public String approval;

    @TableField("manufactor")
    public String manufactor;

    @TableField("drugtypeid")
    public String drugtypeid;
}
