package com.murasaki.medicalinsurance.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.murasaki.medicalinsurance.entity.DrugInfo;

import java.util.List;
import java.util.Map;

public interface DruginfoSer extends IService<DrugInfo> {
    /**
     * service层调用Dao层来查询
     * @param drugId
     * @param drugName
     * @param manuFactor
     * @return
     */
    public List<Map<String,Object>> queryPagerByCond(String drugId,
                                                     String drugName,
                                                     String manuFactor);
}
