package com.murasaki.medicalinsurance.form;

import lombok.Data;

@Data
public class UserinfoSava {
    private String username;
    private Integer id;

    private String name;
    private String type;
    private String identity;
    private String codeid;
    private String birthday;
    private String nation;
    private String address;
    private String phonenum;
    private String scardnum;
    private String registedinfo;
}
