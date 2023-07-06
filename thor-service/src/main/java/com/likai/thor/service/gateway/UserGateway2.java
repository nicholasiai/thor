package com.likai.thor.service.gateway;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class UserGateway2 extends HystrixCommand {


    protected UserGateway2(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected Object run() throws Exception {
        Throwable failedExecutionException = getFailedExecutionException();
        return null;
    }
}
