package com.renga.money.manager.common.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ExecutorConfig {

    @Bean(name = "mmFixedCachedThreadPoolExecutor")
    public ExecutorService mmFixedCachedThreadPoolExecutor(@Value("${mm.executor.maxPoolSize: 150}") int maxPoolSize,
                                                           @Value("${mm.executor.maxQueueCapacitySize: 300}") int maxQueueCapacity){
        int corePoolSize = 5;
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("mm-pool-thread-%d").build();
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize < corePoolSize ? corePoolSize : maxPoolSize, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(maxQueueCapacity), threadFactory);
    }
}
