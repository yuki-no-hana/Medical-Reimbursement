package com.murasaki.medicalinsurance.dao;




import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.murasaki.medicalinsurance.entity.DrugInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


public interface DruginfoDao extends BaseMapper<DrugInfo> {

    /**
     * Dao层操作从数据库查询数据
     * @param drugId
     * @param drugName
     * @param manuFactor
     * @return
     */
    @Select({
            "<script> " +
                    "SELECT " +
                    " druginfo.id, " +
                    " druginfo.drugid, " +
                    " druginfo.drugname, " +
                    " druginfo.price, " +
                    " druginfo.qualification, " +
                    " druginfo.approval, " +
                    " druginfo.manufactor, " +
                    " drugtype.drugtypename, " +
                    " drugtype.discount " +
                    "FROM druginfo " +
                    "  INNER JOIN drugtype ON druginfo.drugtypeid = drugtype.drugtypeid " +
                    "WHERE 1 = 1 " +
                    " <if test='drugId != null and drugId != \"\" '>" +
                    "   AND drugid LIKE CONCAT('%',#{drugId},'%') " +
                    " </if>" +
                    " <if test='drugName != null and drugName != \"\" '>" +
                    "   AND drugname LIKE CONCAT('%',#{drugName},'%') " +
                    " </if>" +
                    " <if test='manuFactor != null and manuFactor != \"\" '>" +
                    "   AND manufactor LIKE CONCAT('%',#{manuFactor},'%') " +
                    " </if>" +
                    " ORDER BY druginfo.id" +
                    "</script>"
    })
    public List<Map<String, Object>> selectByCond(@Param("drugId") String drugId,
                                                  @Param("drugName") String drugName,
                                                  @Param("manuFactor") String manuFactor);
}
