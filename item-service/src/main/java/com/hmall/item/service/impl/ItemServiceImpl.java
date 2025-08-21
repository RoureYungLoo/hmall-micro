package com.hmall.item.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.api.client.dto.ItemDTO;
import com.hmall.api.client.dto.OrderDetailDTO;
import com.hmall.common.exception.BizIllegalException;
import com.hmall.common.utils.BeanUtils;
import com.hmall.item.domain.po.Item;
import com.hmall.item.mapper.ItemMapper;
import com.hmall.item.service.IItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author 虎哥
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

  @Override
  @Transactional
  public void deductStock(List<OrderDetailDTO> items) {
    for (OrderDetailDTO item : items) {
      int i = baseMapper.updateStock(item);
      if (i <= 0) {
        throw new BizIllegalException("库存不足！");
      }
    }
  }

  @Override
  public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
//    try {
//      TimeUnit.MILLISECONDS.sleep(3000);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
    return BeanUtils.copyList(listByIds(ids), ItemDTO.class);
  }
}
