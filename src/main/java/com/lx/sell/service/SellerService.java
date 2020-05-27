package com.lx.sell.service;

import com.lx.sell.entity.SellerInfo;

/**
 * @author Werdio丶
 * @since 2020-05-25 15:49:20
 */
public interface SellerService {

    /**
     *  通过 openid 查询卖家信息
     * @param openid        微信 openid
     * @return              卖家信息
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
