package com.sd.sdzx.user;

import com.sd.sdzx.common.anno.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.sd.sdzx"})
@EnableUserWebMvcConfiguration
@EnableFeignClients(basePackages = {"com.sd.sdzx"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}