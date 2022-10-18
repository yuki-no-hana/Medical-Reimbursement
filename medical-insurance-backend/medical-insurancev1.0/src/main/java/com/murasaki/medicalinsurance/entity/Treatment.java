package com.murasaki.medicalinsurance.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.entity
 * @Author:Murasaki
 * @CreateTime:2021-08-11 13:38
 * @Description:treatmentinfo表映射实体类
 */
@Data
@TableName("treatmeninfo")
public class Treatment implements Serializable {
    private static volatile long serialVersionUID = 1L;

    @TableId("id")
    public int id;

    @TableField("treatmentid")
    public String treatmentid;

    @TableField("scardnum")
    public String scardnum;

    @TableField("drugid")
    public String drugid;

    @TableField("drugnum")
    public int drugnum;

    @TableField("totalprice")
    public double totalprice;

    @TableField("shenpiid")
    public String shenpiid;

    @TableField("codeid")
    public String codeid;

    @TableField("checkouttime")
    public Date checkouttime;

}
