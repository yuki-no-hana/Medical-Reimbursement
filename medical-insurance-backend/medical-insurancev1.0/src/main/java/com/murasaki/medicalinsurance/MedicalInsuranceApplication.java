package com.murasaki.medicalinsurance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://127.0.0.1:8848"})
@SpringBootApplication
@MapperScan("com.murasaki.medicalinsurance.dao")
public class MedicalInsuranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalInsuranceApplication.class, args);
    }

}
