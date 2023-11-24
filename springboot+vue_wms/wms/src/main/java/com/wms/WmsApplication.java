package com.wms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsApplication.class, args);
        System.out.println("启动成功" +
                "+++++++++++++++++++++++++++++++++++++++++" +
                "+++++++++++++++++++++++++++++++++++++++++" +
                "+++++++++++++++++++++++++++++++++++++++++");
    }

}
