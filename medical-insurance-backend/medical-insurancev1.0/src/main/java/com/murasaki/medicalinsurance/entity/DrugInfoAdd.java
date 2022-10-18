package com.murasaki.medicalinsurance.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("druginfo")
public class DrugInfoAdd implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId("id")
    private int  id;

    @TableField("drugid")
    private String drugid;

    @TableField("drugname")
    private String drugname;

    @TableField("price")
    private Double price;

    @TableField("qualification")
    private Character qualification;

    @TableField("approval")
    private Character approval;

    @TableField("manufactor")
    private String manufactor;

    @TableField("drugtypeid")
    private String drugtypeid;
}
