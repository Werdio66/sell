package com.lx.sell.service.impl;

import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.service.WxPayService;
import com.lx.sell.dto.OrderDTO;
import com.lx.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Werdio丶
 * @since 2020-05-23 10:18:44
 */
@Slf4j
@Service
public class PayServiceImpl implements PayService {
    //    @Autowired
//    private BestPayServiceImpl bestPayService;

    @Autowired
    private WxPayService wxpayService;

    private static final String ORDER_NAME = "微信点餐订单";
//
//    @Override
//    public void create(OrderDTO orderDTO) {
//        PayRequest payRequest = new PayRequest();
//
//        payRequest.setOpenid(orderDTO.getBuyerOpenid());
//        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
//        payRequest.setOrderId(orderDTO.getOrderId());
//        payRequest.setOrderName(ORDER_NAME);
//        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MWEB);
//
//        PayResponse payResponse = bestPayService.pay(payRequest);
//        log.info("【微信支付】create ");
//        log.info("【微信支付】payRequest = {}， payResponse = {}", payRequest, payResponse);
//    }


    @Override
    public void create(OrderDTO orderDTO) {

        try{
            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            orderRequest.setBody("主题")
                    .setOutTradeNo(orderDTO.getOrderId())
                    .setTotalFee(BaseWxPayRequest.yuanToFen(orderDTO.getOrderAmount().toString())) //元转成分
                    .setOpenid(orderDTO.getBuyerOpenid())
                    .setSpbillCreateIp("192.168.47.128")
                    .setTimeStart("yyyyMMddHHmmss")
                    .setTimeExpire("yyyyMMddHHmmss");
            wxpayService.createOrder(orderRequest);
            // TODO: 微信支付需要商户平台，后续有了账号在做
        }catch (Exception e){
            log.error("【微信支付】, orderId = {}, 错误信息 = {}",orderDTO.getOrderId(), e.getMessage());
            e.printStackTrace();
        }

    }
}
