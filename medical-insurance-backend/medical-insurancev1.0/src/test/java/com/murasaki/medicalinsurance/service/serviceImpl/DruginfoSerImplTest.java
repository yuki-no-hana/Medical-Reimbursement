package com.murasaki.medicalinsurance.service.serviceImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;


@SpringBootTest
class DruginfoSerImplTest {

    @Autowired
    private DruginfoSerImpl druginfoSer;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * 采用四组case，由于验证like，因此投入三组数据
     */
    @Test
    void queryPagerByCond() {
        String drugId1 = null;
        String drugName1 = null;
        String manuFactor1 = null;
        String drugId2 = "0";
        String drugName2 = "马";
        String manuFactor2 = "德";
        assertNotNull(druginfoSer.queryPagerByCond(drugId2,drugName2,manuFactor2));     //YYY
        assertNotNull(druginfoSer.queryPagerByCond(drugId1,drugName2,manuFactor2));     //NYY
        assertNotNull(druginfoSer.queryPagerByCond(drugId2,drugName1,manuFactor2));     //YNY
        assertNotNull(druginfoSer.queryPagerByCond(drugId2,drugName2,manuFactor1));     //YYN
//        assertNotNull(druginfoSer.queryPagerByCond(drugId1,drugName1,manuFactor1));     //NNN
    }
}