package com.lx.sell.dto;

import com.lx.sell.entity.OrderDetail;
import com.lx.sell.enums.OrderStatus;
import com.lx.sell.enums.PayStatus;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单详情传输对象
 *
 * @author Werdio丶
 * @since 2020-05-13 17:52:13
 */
@Data
public class OrderDTO {

    private String orderId;
    /**
     * 买家名字
     */
    private String buyerName;
    /**
     * 买家电话
     */
    private String buyerPhone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 买家微信openid
     */
    private String buyerOpenid;
    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;
    /**
     * 订单状态, 默认为 0, 新下单
     */
    private Integer orderStatus = OrderStatus.NEW.getCode();
    /**
     * 支付状态, 默认 0, 未支付
     */
    private Integer payStatus = PayStatus.WAIT.getCode();
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     *  订单商品列表
     */
    private List<OrderDetail> orderDetailList;
}
