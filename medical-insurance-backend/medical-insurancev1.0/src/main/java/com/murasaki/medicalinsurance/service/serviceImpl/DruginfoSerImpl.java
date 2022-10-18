package com.murasaki.medicalinsurance.service.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.murasaki.medicalinsurance.dao.DruginfoDao;
import com.murasaki.medicalinsurance.entity.DrugInfo;
import com.murasaki.medicalinsurance.service.DruginfoSer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DruginfoSerImpl extends ServiceImpl<DruginfoDao, DrugInfo> implements DruginfoSer {
    /**
     * service层调用Dao层来查询
     * @param drugId
     * @param drugName
     * @param manuFactor
     * @return
     */
    @Override
    public List<Map<String, Object>> queryPagerByCond(String drugId, String drugName, String manuFactor) {
        List<Map<String, Object>> list = baseMapper.selectByCond(drugId, drugName, manuFactor);
        return list;
    }
}
