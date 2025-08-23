package com.hmall.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义全局过滤器
 *
 * @author luruoyang
 */
@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {
  /**
   * 过滤逻辑
   */
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    log.info("自定义全局过滤器");
    /* 放行 */
    return chain.filter(exchange);
  }

  @Override
  public int getOrder() {
    /* 优先级, 值越小优先级越高 */
    return 0;
  }
}
