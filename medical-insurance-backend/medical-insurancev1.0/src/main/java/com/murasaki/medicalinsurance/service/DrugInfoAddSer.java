package com.murasaki.medicalinsurance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.murasaki.medicalinsurance.entity.DrugInfoAdd;

public interface DrugInfoAddSer extends IService<DrugInfoAdd> {
    public boolean checkAndInsert(DrugInfoAdd drugInfoAdd);

}