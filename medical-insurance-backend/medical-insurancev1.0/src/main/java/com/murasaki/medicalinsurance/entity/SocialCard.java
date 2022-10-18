package com.murasaki.medicalinsurance.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.entity
 * @Author:Murasaki
 * @CreateTime:2021-08-11 18:44
 * @Description:socialcard表映射实体类
 */
@Data
@TableName("socialcard")
public class SocialCard {

    @TableId("id")
    public int id;

    @TableField("scardnum")
    public String scardnum;

    @TableField("money")
    public double money;

    @TableField("regionid")
    public String regionid;

}
