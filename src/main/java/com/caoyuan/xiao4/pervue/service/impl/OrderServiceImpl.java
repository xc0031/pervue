package com.caoyuan.xiao4.pervue.service.impl;

import com.caoyuan.xiao4.pervue.entity.Order;
import com.caoyuan.xiao4.pervue.mapper.OrderMapper;
import com.caoyuan.xiao4.pervue.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-08
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
