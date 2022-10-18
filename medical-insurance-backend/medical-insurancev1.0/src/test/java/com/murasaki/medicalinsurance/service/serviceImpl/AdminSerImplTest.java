package com.murasaki.medicalinsurance.service.serviceImpl;

import com.murasaki.medicalinsurance.service.AdminSer;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject:medical-insurance
 * @BelongsPackage:com.murasaki.medicalinsurance.service.serviceImpl
 * @Author:Murasaki
 * @CreateTime:2021-08-18 09:16
 * @Description:
 */
@SpringBootTest
class AdminSerImplTest {

    @Autowired
    private AdminSer adminSer;

    @Test
    void getMedicalParamByScardNum() {
        String scardnumY = "7eaa3f08dda844ad";

        Map<String, Object> expectedResultY = new HashMap<>();

        expectedResultY.put("start", 1800.0);
        expectedResultY.put("end", 20000.0);

        expectedResultY.put("firstlevel", 3000.0);
        expectedResultY.put("secondlevel", 8000.0);

        expectedResultY.put("firstdiscount", 0.75);
        expectedResultY.put("seconddiscount", 0.8);
        expectedResultY.put("thirddiscount", 0.9);

        Map<String, Object> actualY = adminSer.getMedicalParamByScardNum(scardnumY);

        Assertions.assertEquals((double) expectedResultY.get("start"), (double) actualY.get("start"), 0.0001);
        Assertions.assertEquals((double) expectedResultY.get("end"), (double) actualY.get("end"), 0.0001);

        Assertions.assertEquals((double) expectedResultY.get("firstlevel"), (double) actualY.get("firstlevel"), 0.0001);
        Assertions.assertEquals((double) expectedResultY.get("secondlevel"), (double) actualY.get("secondlevel"), 0.0001);

        Assertions.assertEquals((double) expectedResultY.get("firstdiscount"), (double) actualY.get("firstdiscount"), 0.0001);
        Assertions.assertEquals((double) expectedResultY.get("seconddiscount"), (double) actualY.get("seconddiscount"), 0.0001);
        Assertions.assertEquals((double) expectedResultY.get("thirddiscount"), (double) actualY.get("thirddiscount"), 0.0001, "thirddiscount不同");

        String scardnumN = "";
        Assert.assertNull(adminSer.getMedicalParamByScardNum(scardnumN));

    }

    @Test
    void getNTreatmentListByScardNum() {
        String scardnumY = "7eaa3f08dda844ad";
        int expectedSize = 3;
        Map<String, Object> expectedRecord = new HashMap<>();

        expectedRecord.put("treatmentid", "01e51f02584940529c6207f3220fb84b");
        expectedRecord.put("drugid", "fb200ae894ec434ea162e1dd490cbb69");
        expectedRecord.put("drugname", "苯溴马隆");

        expectedRecord.put("drugtypename", "乙类药");
        expectedRecord.put("discount", 0.7);

        expectedRecord.put("drugnum", 5);
        expectedRecord.put("price", 30.0);
        expectedRecord.put("totalprice", 150.0);

        List<Map<String, Object>> actualResultY = adminSer.getNTreatmentListByScardNum(scardnumY);

        Assertions.assertEquals(expectedSize, actualResultY.size());

        Assertions.assertEquals(expectedRecord.get("treatmentid"), actualResultY.get(1).get("treatmentid"));
        Assertions.assertEquals(expectedRecord.get("drugid"), actualResultY.get(1).get("drugid"));
        Assertions.assertEquals(expectedRecord.get("drugname"), actualResultY.get(1).get("drugname"));

        Assertions.assertEquals(expectedRecord.get("drugtypename"), actualResultY.get(1).get("drugtypename"));
        Assertions.assertEquals(expectedRecord.get("discount"), actualResultY.get(1).get("discount"));

        Assertions.assertEquals(expectedRecord.get("drugnum"), actualResultY.get(1).get("drugnum"));
        Assertions.assertEquals(expectedRecord.get("price"), actualResultY.get(1).get("price"));
        Assertions.assertEquals(expectedRecord.get("totalprice"), actualResultY.get(1).get("totalprice"));

        String scardnumN = "";
        Assertions.assertNull(adminSer.getSTreatmentListByScardNum(scardnumN));
    }

    @Test
    void getSTreatmentListByScardNum() {
        String scardnumY = "7eaa3f08dda844ad";
        int expectedSize = 1;
        Map<String, Object> expectedRecord = new HashMap<>();

        expectedRecord.put("treatmentid", "32bfd49bb70f4038b70e872d04532ae5");
        expectedRecord.put("drugid", "70b73b07e40d46bf99f4a1cf69cfad3e");
        expectedRecord.put("drugname", "恩莱瑞");

        expectedRecord.put("drugtypename", "乙类药");
        expectedRecord.put("discount", 0.7);

        expectedRecord.put("drugnum", 1);
        expectedRecord.put("price", 9186.0);
        expectedRecord.put("totalprice", 9186.0);

        List<Map<String, Object>> actualResultY = adminSer.getSTreatmentListByScardNum(scardnumY);

        Assertions.assertEquals(expectedSize, actualResultY.size());

        Assertions.assertEquals(expectedRecord.get("treatmentid"), actualResultY.get(0).get("treatmentid"));
        Assertions.assertEquals(expectedRecord.get("drugid"), actualResultY.get(0).get("drugid"));
        Assertions.assertEquals(expectedRecord.get("drugname"), actualResultY.get(0).get("drugname"));

        Assertions.assertEquals(expectedRecord.get("drugtypename"), actualResultY.get(0).get("drugtypename"));
        Assertions.assertEquals(expectedRecord.get("discount"), actualResultY.get(0).get("discount"));

        Assertions.assertEquals(expectedRecord.get("drugnum"), actualResultY.get(0).get("drugnum"));
        Assertions.assertEquals(expectedRecord.get("price"), actualResultY.get(0).get("price"));
        Assertions.assertEquals(expectedRecord.get("totalprice"), actualResultY.get(0).get("totalprice"));

        String scardnumN = "";
        Assertions.assertNull(adminSer.getSTreatmentListByScardNum(scardnumN));
    }

    @Test
    void calculateNAllTotal() {
        String scardnumY = "7eaa3f08dda844ad";
        double expectedY = 450.0;
        Assertions.assertEquals(expectedY, adminSer.calculateNAllTotal(adminSer.getNTreatmentListByScardNum(scardnumY)).doubleValue(), 0.01);
        String scardnumN = "";
        double expectedN = 0.0;
        Assertions.assertEquals(expectedN, adminSer.calculateNAllTotal(adminSer.getNTreatmentListByScardNum(scardnumN)).doubleValue(), 0.01);
    }

    @Test
    void calculateNAllDiscounted() {
        String scardnumY = "7eaa3f08dda844ad";
        double expectedY = 315.0;
        Assertions.assertEquals(expectedY, adminSer.calculateNAllDiscounted(adminSer.getNTreatmentListByScardNum(scardnumY), adminSer.getMedicalParamByScardNum(scardnumY)).doubleValue(), 0.01);

        String scardnumN = "";
        double expectedN = 0.0;
        Assertions.assertEquals(expectedN, adminSer.calculateNAllDiscounted(adminSer.getNTreatmentListByScardNum(scardnumN), adminSer.getMedicalParamByScardNum(scardnumN)).doubleValue(), 0.01);
    }

    @Test
    void calculateSAllTotal() {
        String scardnumY = "7eaa3f08dda844ad";
        double expectedY = 9186.0;
        Assertions.assertEquals(expectedY, adminSer.calculateNAllTotal(adminSer.getNTreatmentListByScardNum(scardnumY)).doubleValue(), 0.01);
        String scardnumN = "";
        double expectedN = 0.0;
        Assertions.assertEquals(expectedN, adminSer.calculateNAllTotal(adminSer.getNTreatmentListByScardNum(scardnumN)).doubleValue(), 0.01);
    }

    @Test
    void calculateSAllDiscounted() {
        String scardnumY = "7eaa3f08dda844ad";
        double expectedY = 7147.05;
        Assertions.assertEquals(expectedY, adminSer.calculateSAllDiscounted(adminSer.getSTreatmentListByScardNum(scardnumY), adminSer.getMedicalParamByScardNum(scardnumY)).doubleValue(), 0.01);

        String scardnumN = "";
        double expectedN = 0.0;
        Assertions.assertEquals(expectedN, adminSer.calculateSAllDiscounted(adminSer.getSTreatmentListByScardNum(scardnumN), adminSer.getMedicalParamByScardNum(scardnumN)).doubleValue(), 0.01);
    }

    @Test
    void doNormalApproval() {
        String scardnumY = "7eaa3f08dda844ad";
        List<Map<String, Object>> treatments = adminSer.getNTreatmentListByScardNum(scardnumY);
        List<String> nTreatmentIds = new ArrayList<>();
        for (Map<String, Object> treatment : treatments) {
            nTreatmentIds.add((String) treatment.get("treatmentid"));
        }
        int expectedSize = 3;
        Assertions.assertEquals(expectedSize, nTreatmentIds.size());
        Assertions.assertTrue(adminSer.doNormalApproval(nTreatmentIds));

        String scardnumN = "";
        List<Map<String, Object>> treatmentsN = adminSer.getNTreatmentListByScardNum(scardnumN);
        Assertions.assertNull(treatmentsN);

        List<String> nTreatmentIdListN = new ArrayList<>();
        Assertions.assertFalse(adminSer.doNormalApproval(nTreatmentIdListN));
    }

    @Test
    void doSpecialApproval() {
        String scardnumY = "7eaa3f08dda844ad";
        List<Map<String, Object>> treatments = adminSer.getSTreatmentListByScardNum(scardnumY);

        List<String> sTreatmentIds = new ArrayList<>();
        for (Map<String, Object> treatment : treatments) {
            sTreatmentIds.add((String) treatment.get("treatmentid"));
        }

        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, sTreatmentIds.size());
        Assertions.assertTrue(adminSer.doSpecialApproval(sTreatmentIds));

        String scardnumN = "";
        List<Map<String, Object>> treatmentsN = adminSer.getSTreatmentListByScardNum(scardnumN);
        Assertions.assertNull(treatmentsN);
        List<String> sTreatmentIdListN = new ArrayList<>();
        Assertions.assertFalse(adminSer.doNormalApproval(sTreatmentIdListN));
    }

    @Test
    void doRejectNormalApproval() {
        String scardnumY = "7eaa3f08dda844ad";
        List<Map<String, Object>> treatments = adminSer.getNTreatmentListByScardNum(scardnumY);

        List<String> nTreatmentIds = new ArrayList<>();
        for (Map<String, Object> treatment : treatments) {
            nTreatmentIds.add((String) treatment.get("treatmentid"));
        }

        int expectedSize = 3;
        Assertions.assertEquals(expectedSize, nTreatmentIds.size());
        Assertions.assertTrue(adminSer.doRejectNormalApproval(nTreatmentIds));

        String scardnumN = "";
        List<Map<String, Object>> treatmentsN = adminSer.getNTreatmentListByScardNum(scardnumN);
        Assertions.assertNull(treatmentsN);
        List<String> nTreatmentIdListN = new ArrayList<>();
        Assertions.assertFalse(adminSer.doRejectNormalApproval(nTreatmentIdListN));
    }

    @Test
    void doRejectSpecialApproval() {
        String scardnumY = "7eaa3f08dda844ad";
        List<Map<String, Object>> treatments = adminSer.getSTreatmentListByScardNum(scardnumY);

        List<String> sTreatmentIds = new ArrayList<>();
        for (Map<String, Object> treatment : treatments) {
            sTreatmentIds.add((String) treatment.get("treatmentid"));
        }

        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, sTreatmentIds.size());
        Assertions.assertTrue(adminSer.doRejectSpecialApproval(sTreatmentIds));

        String scardnumN = "";
        List<Map<String, Object>> treatmentsN = adminSer.getSTreatmentListByScardNum(scardnumN);
        Assertions.assertNull(treatmentsN);
        List<String> sTreatmentIdListN = new ArrayList<>();
        Assertions.assertFalse(adminSer.doRejectSpecialApproval(sTreatmentIdListN));
    }
}