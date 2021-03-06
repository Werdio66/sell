package com.lx.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;

/**
 * @author Werdio丶
 * @since 2020-05-19 07:56:11
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccoutConfig {

    private String mpAppId;

    private String mpSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;
}
