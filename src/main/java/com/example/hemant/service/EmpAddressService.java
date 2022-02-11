package com.example.hemant.service;

import com.example.hemant.model.EmpAddress;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class EmpAddressService {

    List<EmpAddress> empAddressList;

    private static void accept(EmpAddress data) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("EmpAddressService: " + data + "" + Thread.currentThread().getName());
    }

    @PostConstruct
    void init() {
        empAddressList = List.of(
                new EmpAddress("MH", "411037"),
                new EmpAddress("MH", "411037"),
                new EmpAddress("MH", "411037"),
                new EmpAddress("MH", "411037")
        );
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<EmpAddress>> getEmpAddressList() {
        empAddressList.forEach(EmpAddressService::accept);
        return CompletableFuture.completedFuture(empAddressList);
    }
}
