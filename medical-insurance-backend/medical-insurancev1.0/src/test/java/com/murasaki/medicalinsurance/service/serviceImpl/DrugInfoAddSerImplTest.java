package com.murasaki.medicalinsurance.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.murasaki.medicalinsurance.entity.DrugInfoAdd;
import com.murasaki.medicalinsurance.form.DrugInfoAddForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DrugInfoAddSerImplTest {

    @Autowired
    private DrugInfoAddSerImpl drugInfoAddSer;

    @Test
    void checkAndInsert() throws SQLException {

        DrugInfoAddForm form = new DrugInfoAddForm();

        /**
         * 除id自增可缺省外均不可缺省
         */
        form.setQualification('Y');
        form.setPrice(25.0);
        form.setApproval('N');
        form.setManufactor("大连制药");
        form.setDrugtypeid("DTA");
        form.setDrugname("葡萄糖");
        form.setDrugid("fasdfafasfasfa");
//       form.setId(5);
        DrugInfoAdd drugInfoAdd = BeanUtil.toBean(form, DrugInfoAdd.class);
        assertNotNull(drugInfoAddSer.checkAndInsert(drugInfoAdd));

        /**
         * 所偶函数均不缺省，可以指定id
         */
        form.setQualification('Y');
        form.setPrice(25.0);
        form.setApproval('N');
        form.setManufactor("大连制药");
        form.setDrugtypeid("DTA");
       form.setDrugname("葡萄糖");
        form.setDrugid("fasdfafasfasfa");
       form.setId(4);
        drugInfoAdd = BeanUtil.toBean(form, DrugInfoAdd.class);
        assertNotNull(drugInfoAddSer.checkAndInsert(drugInfoAdd));

        /**
         * 必须参数缺省则无法正常执行
         */
//        DrugInfoAddForm form1 = new DrugInfoAddForm();
//        form.setQualification('Y');
//        form.setPrice(25.0);
//        form.setApproval('N');
//        form.setManufactor("大连制药");
//        form.setDrugtypeid("DTA");
//       form.setDrugname("葡萄糖");
//        form.setDrugid("fasdfafasfasfa");
//       form.setId(5);
//        drugInfoAdd = BeanUtil.toBean(form1, DrugInfoAdd.class);
//        assertNull(drugInfoAddSer.checkAndInsert(drugInfoAdd));
    }
}