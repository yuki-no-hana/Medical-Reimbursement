package com.murasaki.medicalinsurance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.murasaki.medicalinsurance.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.dao
 * @Author:Murasaki
 * @CreateTime:2021-08-10 15:33
 * @Description:
 */
public interface UserDao extends BaseMapper<UserInfo> {
    /**
     * 根据医保卡号查阅诊疗信息
     *
     * @param scardnum
     * @param codeid
     * @param approval
     * @return
     * @Author:Murasaki
     */
    @Select("<script> " +
            "SELECT " +
            "    treatmentinfo.treatmentid," +
            "    d.drugid, " +
            "    d.drugname, " +
            "    d.drugtypename, " +
            "    d.discount, " +
            "    treatmentinfo.drugnum, " +
            "    d.price, " +
            "    treatmentinfo.totalprice " +
            "FROM   treatmentinfo" +
            "   INNER JOIN " +
            "(" +
            "SELECT " +
            "    druginfo.drugid," +
            "    druginfo.drugname," +
            "    druginfo.price," +
            "    druginfo.qualification," +
            "    druginfo.approval," +
            "    druginfo.manufactor," +
            "    drugtype.drugtypename," +
            "    drugtype.discount" +
            " FROM " +
            "    druginfo INNER JOIN drugtype " +
            " ON " +
            "    druginfo.drugtypeid = drugtype.drugtypeid" +
            ") " +
            "AS d " +
            "    ON treatmentinfo.drugid =  d.drugid " +
            "WHERE 1=1 " +
            "  <if test='scardnum != null and scardnum != \"\" '>" +
            "    AND treatmentinfo.scardnum = #{scardnum} " +
            "  </if>" +
            "  <if test='codeid != null and codeid != \"\" '>" +
            "    AND ( treatmentinfo.codeid = #{codeid}) " +
            "  </if>" +
            "  <if test='codeid != null and codeid != \"\"'>" +
            "    AND ( d.approval = #{approval}) " +
            "  </if>" +
            " ORDER BY treatmenttime ASC" +
            "</script>")
    List<Map<String, Object>> getMyTreatmentsByScardNum(@Param("scardnum") String scardnum,
                                                        @Param("codeid") String codeid,
                                                        @Param("approval") String approval);


    /**
     * 根据医保卡号查询医疗信息参数
     *
     * @param scardnum
     * @return
     * @Author:Murasaki
     */
    @Select("<script> " +
            "SELECT " +
            "    medicalparam.start," +
            "    medicalparam.end," +
            "    medicalparam.firstlevel," +
            "    medicalparam.secondlevel," +
            "    medicalparam.firstdiscount," +
            "    medicalparam.seconddiscount," +
            "    medicalparam.thirddiscount " +
            "FROM   medicalparam " +
            "INNER JOIN socialcard " +
            "ON " +
            "   medicalparam.regionid =  socialcard.regionid " +
            "WHERE 1=1" +
            "  <if test='scardnum != null and scardnum != \"\" '>" +
            "    AND scardnum = #{scardnum}" +
            "  </if>" +
            "</script>")
    Map<String, Object> getMyMParamByScardNum(@Param("scardnum") String scardnum);
}
