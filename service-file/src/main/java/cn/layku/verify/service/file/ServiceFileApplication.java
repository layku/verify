package cn.layku.verify.service.file;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.layku.verify.service.file.mapper")
public class ServiceFileApplication {

    private static Logger logger = LoggerFactory.getLogger(ServiceFileApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServiceFileApplication.class, args);
        logger.info("|---file system is running---|");
    }

}
