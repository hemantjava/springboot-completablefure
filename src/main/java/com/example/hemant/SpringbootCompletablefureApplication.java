package com.example.hemant;

import com.example.hemant.model.EmpAddress;
import com.example.hemant.model.EmpName;
import com.example.hemant.model.EmpPhone;
import com.example.hemant.service.EmpAddressService;
import com.example.hemant.service.EmpNameService;
import com.example.hemant.service.EmpPhoneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class SpringbootCompletablefureApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCompletablefureApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(EmpAddressService empAddressService, EmpNameService empNameService,
								 EmpPhoneService empPhoneService) {
		return args->{
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			CompletableFuture<List<EmpAddress>> empAddressList = empAddressService.getEmpAddressList();
			CompletableFuture<List<EmpName>> empNameList = empNameService.getEmpNameList();
			CompletableFuture<List<EmpPhone>> empPhoneList = empPhoneService.getEmpPhoneList();
			CompletableFuture.allOf(empAddressList,empNameList,empPhoneList).join();
			stopWatch.stop();
			System.out.println("Time taken to fetch all the data : "+stopWatch.getTotalTimeMillis());
		};
	}
}
