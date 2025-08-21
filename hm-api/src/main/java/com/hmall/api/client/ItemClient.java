package com.hmall.api.client;

import com.hmall.api.client.dto.ItemDTO;
import com.hmall.api.client.dto.OrderDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * @author luruoyang
 */
@FeignClient(name = "item-service", path = "/items")
public interface ItemClient {

  /**
   * 根据id批量查询商品
   */
  @GetMapping
  public List<ItemDTO> queryItemByIds(@RequestParam("ids") Collection<Long> ids);

  @PutMapping("/stock/deduct")
  public void deductStock(@RequestBody List<OrderDetailDTO> items);
}
