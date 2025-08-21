package com.hmall.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author luruoyang
 */
@FeignClient(name = "order-service", path = "/orders")
public interface OrderClient {
  @PutMapping("/{orderId}")
  public void markOrderPaySuccess(@PathVariable("orderId") Long orderId);
}
