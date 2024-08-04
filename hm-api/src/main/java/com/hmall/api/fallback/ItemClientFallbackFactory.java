package com.hmall.api.fallback;

import com.hmall.api.client.ItemClient;
import com.hmall.api.dto.ItemDTO;
import com.hmall.api.dto.OrderDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Slf4j
public class ItemClientFallbackFactory implements FallbackFactory<ItemClient> {
    @Override
    public ItemClient create(Throwable cause) {
        return new ItemClient() {
            @Override
            public List<ItemDTO> queryItemsbyIds(Collection<Long> ids) {
                log.error("查询商品服务失败", cause);
                return Collections.emptyList();
            }

            @Override
            public void deductStock(List<OrderDetailDTO> items) {
                log.error("扣减库存服务失败", cause);
                throw new RuntimeException("扣减库存服务失败");
            }

            @Override
            public void updateItem(ItemDTO item) {
                log.error("更新商品服务失败", cause);
                throw new RuntimeException("更新商品服务失败");
            }

            @Override
            public ItemDTO queryItemById(Long id) {
                log.error("查询商品服务失败", cause);
                return null;
            }


        };
    }
}
