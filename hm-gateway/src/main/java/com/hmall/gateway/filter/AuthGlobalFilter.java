package com.hmall.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.hmall.gateway.config.AuthProperties;
import com.hmall.gateway.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 网关身份认证 全局过滤器
 */
@RequiredArgsConstructor
@Component
@EnableConfigurationProperties({AuthProperties.class})
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {

  private final AuthProperties authProperties;

  private final JwtTool jwtTool;

  private AntPathMatcher antPathMatcher = new AntPathMatcher();

  /**
   * 过滤逻辑
   */
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    // 获取请求实例,响应实例
    ServerHttpRequest req = exchange.getRequest();
    ServerHttpResponse resp = exchange.getResponse();

    // 获取请求路径
    String path = req.getURI().getPath();

    // 是否直接放行
    List<String> excludePaths = authProperties.getExcludePaths();
    long count = excludePaths.stream().filter(x -> antPathMatcher.match(x, path)).count();
    if (count > 0) {
      log.info("网关身份认证排除: {}", path);
      return chain.filter(exchange);
    }

    // 获取令牌
    String token = req.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
    if (StrUtil.isBlank(token)) {
      log.info("网关身份认证失败: {} token is blank", path);
      resp.setStatusCode(HttpStatus.UNAUTHORIZED);
      return resp.setComplete();
    }

    // 校验令牌
    try {
      Long userId = jwtTool.parseToken(token);
      // 放行
      log.info("网关身份认证成功: {} token is valid", path);
      // 保存用户信息到请求头
      req.mutate().header("user-info", userId.toString());
      return chain.filter(exchange);
    } catch (Exception e) {
      e.printStackTrace();
      // 不放行
      log.info("网关身份认证失败: {} token is invalid", path);
      resp.setStatusCode(HttpStatus.UNAUTHORIZED);
      return resp.setComplete();
    }
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
