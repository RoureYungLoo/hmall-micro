package com.hmall.cart.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hmall.api.client.CartClient;
import com.hmall.cart.domain.dto.CartFormDTO;
import com.hmall.cart.domain.po.Cart;
import com.hmall.cart.domain.vo.CartVO;
import com.hmall.cart.service.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Api(tags = "购物车相关接口")
@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@Slf4j
public class CartController implements CartClient {
  private final ICartService cartService;

  @ApiOperation("添加商品到购物车")
  @PostMapping
  public void addItem2Cart(@Valid @RequestBody CartFormDTO cartFormDTO) {
    cartService.addItem2Cart(cartFormDTO);
  }

  @ApiOperation("更新购物车数据")
  @PutMapping
  public void updateCart(@RequestBody Cart cart) {
    cartService.updateById(cart);
  }

  @ApiOperation("删除购物车中商品")
  @DeleteMapping("{id}")
  public void deleteCartItem(@Param("购物车条目id") @PathVariable("id") Long id) {
    cartService.removeById(id);
  }

  @ApiOperation("查询购物车列表")
  @GetMapping
  @SentinelResource(value = "queryMyCarts", fallback = "queryMyCartsFallback", blockHandler = "queryMyCartsBlockHandler")
  public List<CartVO> queryMyCarts() {
    return cartService.queryMyCarts();
  }

  /**
   * 非熔断限流触发的降级
   */
  public List<CartVO> queryMyCartsFallback(Throwable throwable) {
    log.info("非熔断限流触发的降级", throwable);
    return Collections.emptyList();
  }

  /**
   * 熔断限流触发的降级
   */
  public List<CartVO> queryMyCartsBlockHandler(BlockException e) {
    log.info("熔断限流触发的降级", e);
    return Collections.emptyList();
  }

  @ApiOperation("批量删除购物车中商品")
  @ApiImplicitParam(name = "ids", value = "购物车条目id集合")
  @DeleteMapping
  public void deleteCartItemByIds(@RequestParam("ids") List<Long> ids) {
    cartService.removeByItemIds(ids);
  }
}
