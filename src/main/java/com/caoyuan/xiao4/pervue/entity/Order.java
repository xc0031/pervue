package com.caoyuan.xiao4.pervue.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 下单人
     */
    private Integer userId;

    /**
     * 总金额
     */
    private BigDecimal totalPrice;

    /**
     * 订单状态(1-已下单待支付
2-已支付待发货
3-已发货待收货
4-已收货待评价)
     */
    private Integer status;

    /**
     * 下单时间
     */
    private Date created;


}
