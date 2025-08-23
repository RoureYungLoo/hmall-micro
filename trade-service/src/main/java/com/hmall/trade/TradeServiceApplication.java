package com.hmall.trade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lenovo
 */
@MapperScan("com.hmall.trade.mapper")
@SpringBootApplication(scanBasePackages = {"com.hmall.api", "com.hmall.trade"})
@EnableFeignClients(basePackages = "com.hmall.api.client")
public class TradeServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(TradeServiceApplication.class, args);
  }
}