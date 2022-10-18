package com.murasaki.medicalinsurance.service.serviceImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserSerImplTest {
    @Autowired
    private UserSerImpl userSer;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getMedicalParamByScardNum() {
        String scardnum1 = "42ab3dce2f6844e7";
        String scardnum2 = "";
        String scardnum3 = "12345shangshanda";
        assertNotNull(userSer.getMedicalParamByScardNum(scardnum1));
        assertNull(userSer.getMedicalParamByScardNum(scardnum2));
        assertNull(userSer.getMedicalParamByScardNum(scardnum3));
    }

    @Test
    void getNTreatmentListByScardNum() {
        String scardnum1 = "42ab3dce2f6844e7";
        String scardnum2 = "";
        assertNotNull(userSer.getNTreatmentListByScardNum(scardnum1));
        assertNull(userSer.getNTreatmentListByScardNum(scardnum2));
    }

    @Test
    void getSTreatmentListByScardNum() {
        String scardnum1 = "42ab3dce2f6844e7";
        String scardnum2 = "";
        assertNotNull(userSer.getSTreatmentListByScardNum(scardnum1));
        assertNull(userSer.getSTreatmentListByScardNum(scardnum2));
    }

    @Test
    void calculateNAllTotal() {
        String scardnum1 = "42ab3dce2f6844e7";
        String scardnum2 = "";
        assertNotEquals(0, userSer.calculateNAllTotal(userSer.getNTreatmentListByScardNum(scardnum1)).doubleValue());
        assertEquals(0, userSer.calculateNAllTotal(userSer.getNTreatmentListByScardNum(scardnum2)).doubleValue());
    }

    @Test
    void calculateNAllDiscounted() {
        String scardnum1 = "42ab3dce2f6844e7";
        String scardnum2 = "";
        assertNotEquals(0, userSer.calculateNAllDiscounted(userSer.getNTreatmentListByScardNum(scardnum1), userSer.getMedicalParamByScardNum(scardnum1)).doubleValue());
        assertEquals(0, userSer.calculateNAllDiscounted(userSer.getNTreatmentListByScardNum(scardnum2), userSer.getMedicalParamByScardNum(scardnum2)).doubleValue());
    }

    @Test
    void calculateSAllTotal() {
        String scardnum1 = "42ab3dce2f6844e7";
        String scardnum2 = "";
        assertNotEquals(0, userSer.calculateSAllTotal(userSer.getSTreatmentListByScardNum(scardnum1)).doubleValue());
        assertEquals(0, userSer.calculateSAllTotal(userSer.getSTreatmentListByScardNum(scardnum2)).doubleValue());
    }

    @Test
    void calculateSAllDiscounted() {
        String scardnum1 = "42ab3dce2f6844e7";
        String scardnum2 = "";
        assertNotEquals(0, userSer.calculateSAllDiscounted(userSer.getSTreatmentListByScardNum(scardnum1), userSer.getMedicalParamByScardNum(scardnum1)).doubleValue());
        assertEquals(0, userSer.calculateSAllDiscounted(userSer.getSTreatmentListByScardNum(scardnum2), userSer.getMedicalParamByScardNum(scardnum2)).doubleValue());
    }

    @Test
    void submitNormal() {
    }

    @Test
    void submitSpecial() {
    }
}