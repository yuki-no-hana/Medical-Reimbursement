package com.murasaki.medicalinsurance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.murasaki.medicalinsurance.entity.DrugType;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface DrugTypeDao extends BaseMapper<DrugType> {
    @Select({
            "<script> " +
                    "SELECT " +
                    "    drugtypename , " +
                    "    drugtypeid  " +
                    "FROM  drugtype" +
                    "</script>"
    })
    public List<Map<String, Object>> queryDrugTypeName();

    /**
     * created钩子对应dao层操作，查询所有药品类别以及其对应的报销比例
     *
     * @Author:Murasaki
     */
    @Select("SELECT " +
            "    drugtypename," +
            "    discount " +
            "FROM " +
            "    drugtype")
    List<Map<String, Object>> queryDrugTypeAndDiscount();
}