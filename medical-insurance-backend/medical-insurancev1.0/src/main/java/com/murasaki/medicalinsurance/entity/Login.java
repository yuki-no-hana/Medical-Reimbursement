package com.murasaki.medicalinsurance.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.entity
 * @Author:Murasaki
 * @CreateTime:2021-08-08 19:18
 * @Description:login表映射实体类
 */

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("login")
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    Integer id;

    @TableField("username")
    String username;

    @TableField("password")
    String password;


    @TableField("roleid")
    String roleid;

}
