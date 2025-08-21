package com.hmall.api.client;

import com.hmall.api.client.dto.ItemDTO;
import com.hmall.api.client.dto.OrderDetailDTO;
import com.hmall.common.utils.CollUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 方式二: 降级工厂实现 FallbackFactory
 *
 * @author luruoyang
 */
@Component
@Slf4j
public class ItemClientFallbackFactory implements FallbackFactory<ItemClient> {
  @Override
  public ItemClient create(Throwable cause) {
    // 降级类
    return new ItemClient() {
      @Override
      public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
        log.error("远程调用ItemClient#queryItemByIds方法出现异常，参数：{}", ids, cause);
        // cause.printStackTrace();
        return CollUtils.emptyList();
      }

      @Override
      public void deductStock(List<OrderDetailDTO> items) {
        // log.error("远程调用ItemClient#deductStock扣减库存失败,参数:{}", items, cause);
        cause.printStackTrace();
      }
    };
  }
}
