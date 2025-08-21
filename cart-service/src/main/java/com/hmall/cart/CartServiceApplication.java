package com.hmall.cart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.hmall.cart.mapper")
@SpringBootApplication(scanBasePackages = {"com.hmall.api", "com.hmall.cart"})
@EnableFeignClients(basePackages = {"com.hmall.api"})
public class CartServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(CartServiceApplication.class, args);
  }
}