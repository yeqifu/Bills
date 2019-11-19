package com.yeqifu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.yeqifu.sys.mapper"})
public class Springboot09BillsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot09BillsApplication.class, args);
	}

}
