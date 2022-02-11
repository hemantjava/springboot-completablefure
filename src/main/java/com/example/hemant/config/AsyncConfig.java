package com.example.hemant.config;

import lombok.AllArgsConstructor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@AllArgsConstructor
public class AsyncConfig implements AsyncConfigurer {

    private final AsyncExceptionHandler asyncExceptionHandler;


    //Application level overriden method
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(50);
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setThreadNamePrefix("thread 1-Async-");
        taskExecutor.setQueueCapacity(500);
        taskExecutor.initialize();
        return taskExecutor;
    }


    //Method level
    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(50);
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setThreadNamePrefix("thread 2-Async EmpAddress-");
        taskExecutor.setQueueCapacity(500);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return asyncExceptionHandler;
    }
}
