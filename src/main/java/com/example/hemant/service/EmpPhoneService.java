package com.example.hemant.service;

import com.example.hemant.model.EmpPhone;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class EmpPhoneService {

    private List<EmpPhone> empPhoneList;

    private static void accept(EmpPhone data) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("EmpPhoneService: " + data + " " + Thread.currentThread().getName());
    }

    @PostConstruct
    void init() {
        empPhoneList = List.of(
                new EmpPhone("1234567890"),
                new EmpPhone("2345678901"),
                new EmpPhone("3456789012")
        );
    }

   // @Async
    public CompletableFuture<List<EmpPhone>> getEmpPhoneList() {
        empPhoneList.forEach(EmpPhoneService::accept);
        return CompletableFuture.completedFuture(empPhoneList);
    }
}
