package com.murasaki.medicalinsurance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.murasaki.medicalinsurance.entity.Treatment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.dao
 * @Author:Murasaki
 * @CreateTime:2021-08-11 13:51
 * @Description:
 */
public interface TreatmentDao extends BaseMapper<Treatment> {

    /**
     * 更新treatment字段,做结算操作
     *
     * @param treatmentid
     * @param codeid
     * @param checkouttime
     * @return
     * @Author:Murasaki
     */
    @Update("UPDATE" +
            "   treatmentinfo " +
            "SET" +
            "   codeid=#{codeid}," +
            "   checkouttime=#{checkouttime} " +
            "WHERE" +
            "   treatmentid=#{treatmentid}")
    boolean checkOut(@Param("treatmentid") String treatmentid,
                     @Param("codeid") String codeid,
                     @Param("checkouttime") Date checkouttime);

    /**
     * 更新treatment字段,做打包提交操作
     *
     * @param treatmentid
     * @param shenpiid
     * @param codeid
     * @return
     * @Author:Murasaki
     */
    @Update("UPDATE" +
            "    treatmentinfo " +
            "SET" +
            "   shenpiid = #{shenpiid}," +
            "   codeid = #{codeid} " +
            "WHERE" +
            "   treatmentid=#{treatmentid}")
    boolean setAndUpdate(@Param("treatmentid") String treatmentid,
                         @Param("shenpiid") String shenpiid,
                         @Param("codeid") String codeid);
}
