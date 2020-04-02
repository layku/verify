package cn.layku.verify.service.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceAuthApplication {

    private static Logger logger = LoggerFactory.getLogger(ServiceAuthApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
        logger.info("|---auth system is running---|");
    }

}
