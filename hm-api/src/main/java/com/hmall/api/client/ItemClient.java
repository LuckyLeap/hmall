package com.hmall.api.client;


import com.hmall.api.dto.ItemDTO;
import com.hmall.api.dto.OrderDetailDTO;
import com.hmall.api.fallback.ItemClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@FeignClient(value = "item-service",fallbackFactory = ItemClientFallbackFactory.class)
public interface ItemClient {
    @GetMapping("/items")
    List<ItemDTO>  queryItemsbyIds(@RequestParam("ids") Collection<Long> ids);
    @PutMapping("/items/stock/deduct")
    public void deductStock(@RequestBody List<OrderDetailDTO> items);

    @PutMapping("/items")
    public void updateItem(@RequestBody ItemDTO item);

    @GetMapping("/items/{id}")
    public ItemDTO queryItemById(@PathVariable("id") Long id);
}