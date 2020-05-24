package com.lx.sell.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Werdio丶
 * @since 2020-05-23 09:52:49
 */
@Configuration
public class WechatPayConfig {

    @Autowired
    private WechatAccoutConfig accoutConfig;

//    @Bean
//    public BestPayServiceImpl bestPayService(){
//        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
//
//        WxPayConfig wxPayConfig = new WxPayConfig();
//
//        wxPayConfig.setAppId(accoutConfig.getMpAppId());
//        wxPayConfig.setAppSecret(accoutConfig.getMpSecret());
//        wxPayConfig.setMchId(accoutConfig.getMchId());
//        wxPayConfig.setMchKey(accoutConfig.getMchKey());
//        wxPayConfig.setKeyPath(accoutConfig.getKeyPath());
//        wxPayConfig.setNotifyUrl(accoutConfig.getNotifyUrl());
//
//        bestPayService.setWxPayConfig(wxPayConfig);
//        return bestPayService;
//    }

    @Bean
    public WxPayConfig wxPayConfig(){
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(accoutConfig.getMpAppId());
        wxPayConfig.setMchId(accoutConfig.getMchId());
        wxPayConfig.setMchKey(accoutConfig.getMchKey());
        wxPayConfig.setKeyPath(accoutConfig.getKeyPath());
        wxPayConfig.setNotifyUrl(accoutConfig.getNotifyUrl());

        // 交易类型
        wxPayConfig.setTradeType(WxPayConstants.TradeType.JSAPI);

        return wxPayConfig;
    }

    @Bean
    public WxPayService wxPayService(){
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(wxPayConfig());
        return wxPayService;
    }
}
