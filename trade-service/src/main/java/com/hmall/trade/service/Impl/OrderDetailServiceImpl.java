package com.hmall.trade.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.trade.domain.po.OrderDetail;
import com.hmall.trade.mapper.OrderDetailMapper;
import com.hmall.trade.service.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {
    private final OrderDetailMapper orderDetailMapper;
    @Override
    public List<OrderDetail> getByOrderId(Long orderId) {
        return orderDetailMapper.listByOrderId(orderId);
    }
}