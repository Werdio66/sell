package com.lx.sell.service.impl;

import com.lx.sell.dao.SellerInfoDao;
import com.lx.sell.entity.SellerInfo;
import com.lx.sell.service.SellerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Werdio丶
 * @since 2020-05-25 15:51:14
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Resource
    private SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoDao.findByOpenid(openid);
    }


}
