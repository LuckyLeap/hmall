package com.hmall.api.Constants;

public interface CartClearMqConstants {
    String ClearCart_EXCHANGE_NAME = "trade.topic";

    String ClearCart_QUEUE_NAME = "cart.clear.queue";

    String ClearCart_ROUTING_KEY = "order.create";

}