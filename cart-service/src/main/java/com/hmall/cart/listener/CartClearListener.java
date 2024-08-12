package com.hmall.cart.listener;

import com.hmall.api.Constants.CartClearMqConstants;
import com.hmall.cart.service.ICartService;
import com.hmall.common.utils.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
@Slf4j
public class CartClearListener {
    private final ICartService cartService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = CartClearMqConstants.ClearCart_QUEUE_NAME, durable = "true"),
            exchange = @Exchange(name = CartClearMqConstants.ClearCart_EXCHANGE_NAME),
            key = CartClearMqConstants.ClearCart_ROUTING_KEY
    ))
    public void listenCartClear(Collection<Long> itemIds, Message message){
        Long userId = message.getMessageProperties().getHeader("userId");
        log.info("监听到清空购物车的消息用户id:{},商品id: {}",userId, itemIds);
        UserContext.setUser(userId);
        cartService.removeByItemIds(itemIds);
    }
}
