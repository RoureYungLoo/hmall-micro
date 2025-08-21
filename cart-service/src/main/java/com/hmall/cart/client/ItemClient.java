//package com.hmall.cart.client;
//
//import com.hmall.cart.domain.dto.ItemDTO;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Collection;
//import java.util.List;
//
///**
// * @author luruoyang
// */
//@FeignClient(name = "item-service", path = "/items")
//public interface ItemClient {
//
//  /**
//   * 根据id批量查询商品
//   */
//  @GetMapping
//  public List<ItemDTO> queryItemByIds(@RequestParam("ids") Collection<Long> ids);
//
//}
