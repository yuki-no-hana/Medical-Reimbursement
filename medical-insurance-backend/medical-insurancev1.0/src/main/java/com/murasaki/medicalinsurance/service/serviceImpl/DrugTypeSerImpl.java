package com.murasaki.medicalinsurance.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.murasaki.medicalinsurance.dao.DrugTypeDao;
import com.murasaki.medicalinsurance.entity.DrugType;
import com.murasaki.medicalinsurance.service.DrugTypeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DrugTypeSerImpl extends ServiceImpl<DrugTypeDao, DrugType> implements DrugTypeSer {
    @Autowired
    DrugTypeDao drugtypeDao;

    @Override
    public List<Map<String, Object>> queryByDrugTypeName() {

        return drugtypeDao.queryDrugTypeName();
    }

    /**
     * 供created钩子调用的服务，会查询所有药品类别和对应报销比例返回给前端显示在提示块内
     *
     * @return
     * @Author:Murasaki
     */
    @Override
    public List<Map<String, Object>> queryDrugTypeAndDiscount() {
        return drugtypeDao.queryDrugTypeAndDiscount();
    }
}