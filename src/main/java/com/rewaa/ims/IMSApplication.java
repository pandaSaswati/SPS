package com.rewaa.ims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class IMSApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(IMSApplication.class, args);
	}
}
