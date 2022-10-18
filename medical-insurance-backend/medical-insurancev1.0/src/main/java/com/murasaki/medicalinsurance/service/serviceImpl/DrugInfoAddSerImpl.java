package com.murasaki.medicalinsurance.service.serviceImpl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.murasaki.medicalinsurance.dao.DrugInfoAddDao;
import com.murasaki.medicalinsurance.entity.DrugInfoAdd;
import com.murasaki.medicalinsurance.service.DrugInfoAddSer;
import org.springframework.stereotype.Service;

@Service
public class DrugInfoAddSerImpl extends ServiceImpl<DrugInfoAddDao, DrugInfoAdd> implements DrugInfoAddSer {
    @Override
    public boolean checkAndInsert(DrugInfoAdd drugInfoAdd) {
  drugInfoAdd.setDrugid(UUID.randomUUID().toString());
        return save(drugInfoAdd);
    }
}
