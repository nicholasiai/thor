package com.likai.thor.service.gateway;

import com.likai.thor.dao.domain.UserInfoDo;
import com.likai.thor.service.bo.GatewayReqDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@Slf4j
public class UserGateway {

    public static int num = 1;

    @HystrixCommand(fallbackMethod = "gatUserFallback",commandKey="gatUser", commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED, value = "true"),
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "20"),
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_FORCE_OPEN, value = "true"),
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "5000"),
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "50"),
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "5000"),
            @HystrixProperty(name = HystrixPropertiesManager.METRICS_ROLLING_STATS_TIME_IN_MILLISECONDS, value = "10000")
    })
    public UserInfoDo gatUser(GatewayReqDto reqDto){
        System.out.println("正常业务开始:"+reqDto.hashCode());
        num++;
        if (num%2 == 0){
            Scanner scanner = new Scanner(System.in);
            System.out.println("等待输入");
            String username = scanner.nextLine();
            System.out.println(username);
        }
        if (num%3 == 0){
            int i = 1/0;
        }
        UserInfoDo userInfoDo = new UserInfoDo();
        userInfoDo.setId(reqDto.getUserId());
        userInfoDo.setUserName("正常业务");
        return userInfoDo;
    }

    public UserInfoDo gatUserFallback(GatewayReqDto reqDto,Throwable throwable){
        // Hystrix circuit short-circuited and is OPEN 熔断器打开异常
        log.error("触发熔断异常信息",throwable);
        System.out.println("触发熔断:"+reqDto.hashCode());
        UserInfoDo userInfoDo = new UserInfoDo();
        userInfoDo.setId(reqDto.getUserId());
        userInfoDo.setUserName("触发熔断");
        return userInfoDo;
    }
}
