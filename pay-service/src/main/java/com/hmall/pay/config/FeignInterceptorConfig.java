package com.hmall.pay.config;

import com.hmall.common.utils.UserContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luruoyang
 */
@Configuration
@Slf4j
public class FeignInterceptorConfig {

  @Bean
  public RequestInterceptor requestInterceptor() {
    return new RequestInterceptor() {
      @Override
      public void apply(RequestTemplate template) {
        Long userId = UserContext.getUser();
        if (userId != null) {
          log.info("********************* Feign Interceptor *************************");
          template.header("user-info", userId.toString());
          log.info("");
        }
      }
    };
  }
}
