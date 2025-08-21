package com.hmall.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author luruoyang
 */
@FeignClient(name = "user-service", path = "/users")
public interface UserClient {

  @PutMapping("/money/deduct")
  public void deductMoney(@RequestParam("pw") String pw, @RequestParam("amount") Integer amount);
}
