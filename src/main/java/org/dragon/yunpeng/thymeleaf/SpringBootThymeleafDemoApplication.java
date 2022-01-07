package org.dragon.yunpeng.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication 
@EnableAutoConfiguration
@ComponentScan(basePackages={"org.dragon.yunpeng.thymeleaf"})
@EnableJpaRepositories(basePackages="org.dragon.yunpeng.thymeleaf.repositories")
@EnableTransactionManagement
@EntityScan(basePackages="org.dragon.yunpeng.thymeleaf.entities")
public class SpringBootThymeleafDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootThymeleafDemoApplication.class, args);
	}

}
