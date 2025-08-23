package com.hmall.common;

import cn.hutool.core.util.StrUtil;
import com.hmall.common.utils.UserContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息拦截器
 *
 * @author luruoyang
 */
@Component
public class UserInfoInterceptor implements HandlerInterceptor {
  /**
   * 拦截之前
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String userId = request.getHeader("user-info");
    if (StrUtil.isNotBlank(userId)) {
      UserContext.setUser(Long.valueOf(userId));
    }
    return true;
  }

  /**
   * 处理完毕
   */
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    // 清除用户信息
    UserContext.removeUser();
  }
}
