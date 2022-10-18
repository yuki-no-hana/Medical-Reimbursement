package com.murasaki.medicalinsurance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.murasaki.medicalinsurance.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserinfoDao extends BaseMapper<UserInfo>{
    @Select({
            "<script>" +
                    "SELECT " +
                    "    id, " +
                    "    username, " +
                    "    name, " +
                    "    identity, " +
                    "    codeid, " +
                    "    type, " +
                    "    birthday, " +
                    "    nation, " +
                    "    address, " +
                    "    phonenum, " +
                    "    scardnum, " +
                    "     registedinfo " +
                    "FROM userinfo " +
                    " WHERE " +
                    "    1 = 1 " +
                    "    <if  test=' username != null and username !=\"\" '> "  +
                    "    AND username like CONCAT('%',#{username} ,'%') " +
                    "    </if> " +
                    "</script>"

    })

    public List<UserInfo> selectByUsername(@Param("username") String username);




    @Update({
            "<script>" +
                    "UPDATE "+
                    "userinfo " +
                    "SET " +
                    " id = #{id}, " +
                    " name = #{name}, " +
                    " type = #{type}, " +
                    " identity = #{identity}, " +
                    " codeid = #{codeid}, " +
                    " birthday = #{birthday}, " +
                    " nation = #{nation}, " +
                    " address = #{address}, " +
                    " phonenum = #{phonenum}, " +
                    " scardnum = #{scardnum}, " +
                    " registedinfo = #{registedinfo} " +
                    "WHERE username = #{username} " +
                    "</script>"
    })

    public boolean update(       @Param("username") String username,
                                 @Param("id") Integer id,
                                 @Param("name") String name,
                                 @Param("type") String type,
                                 @Param("identity") String identity,
                                 @Param("codeid") String codeid,
                                 @Param("birthday") String birthday,
                                 @Param("nation") String nation,
                                 @Param("address") String address,
                                 @Param("phonenum") String phonenum,
                                 @Param("scardnum") String scardnum,
                                 @Param("registedinfo") String registedinfo
    );














}
