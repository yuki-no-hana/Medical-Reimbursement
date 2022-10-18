package com.murasaki.medicalinsurance.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Date;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.entity
 * @Author:Murasaki
 * @CreateTime:2021-08-10 15:39
 * @Description:userinfo表映射实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("userinfo")
public class UserInfo implements Serializable{
    private static final long serialVersionUID=1L;

    @TableId("id")
    public Integer id;

    @TableField("username")
    public String username;

    @TableField("name")
    public String name;

    @TableField("type")
    public String type;

    @TableField("identity")
    public String identity;

    @TableField("codeid")
    public String codeid;

    @TableField("birthday")
    public Date birthday;

    @TableField("nation")
    public String nation;

    @TableField("address")
    public String address;

    @TableField("phonenum")
    public String phonenum;

    @TableField("scardnum")
    public String scardnum;

    @TableField("registedinfo")
    public String registedinfo;

    public void setBirthday(String i) {
    }
}
