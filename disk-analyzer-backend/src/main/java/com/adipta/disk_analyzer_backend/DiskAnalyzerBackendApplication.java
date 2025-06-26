package com.adipta.disk_analyzer_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCaching
public class DiskAnalyzerBackendApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(DiskAnalyzerBackendApplication.class, args);
	}

}
