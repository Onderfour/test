package com.sd.sdzx.manager;


import com.sd.sdzx.common.log.annotation.EnableLogAspect;
import com.sd.sdzx.manager.properties.MinioProperties;
import com.sd.sdzx.manager.properties.UserAuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableLogAspect
@EnableAsync
@SpringBootApplication
@ComponentScan("com.sd.sdzx")
@EnableConfigurationProperties(value = {UserAuthProperties.class, MinioProperties.class})
@EnableScheduling

public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class , args) ;
    }

}