package com.murasaki.medicalinsurance.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 映射userinfo表的实体类
 * 王安琪
 * 2021-8-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("userinfo")
public class findmypw implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 表主键id*/
    @TableId("id")
    private int id;

    /**
     * 表属性：账户名
     * */

    @TableField("username")
    private String username;

    /**
     * 表属性：姓名
     * */

    @TableField("name")
    private String name;

    /**
     * 表属性：身份证号*/
    @TableField("identity")
    private String identity;

    /**
     * 表属性：属性id*/
    @TableField("codeid")
    private String codeid;

    /**
     * 表属性：类型*/
    @TableField("type")
    private String type;


    /**
     * 表属性：出生日期*/
    @TableField("birthday")
    private Date birthday;

    /**
     * 表属性：nation*/
    @TableField("nation")
    private String nation;

    /**
     * 表属性：家庭住址*/
    @TableField("address")
    private String address;

    /**
     * 表属性：联系方式*/
    @TableField("phonenum")
    private String phonenum;

    /**
     * 表属性：什么卡号码*/
    @TableField("scardnum")
    private String scardnum;

    /**
     * 表属性：我也不知道*/
    @TableField("registedinfo")
    private  String registedinfo;

}
