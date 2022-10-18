package com.murasaki.medicalinsurance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.murasaki.medicalinsurance.entity.DrugType;

import java.util.List;
import java.util.Map;

public interface DrugTypeSer extends IService<DrugType> {
     List<Map<String,Object>> queryByDrugTypeName();

    List<Map<String, Object>> queryDrugTypeAndDiscount();
}
