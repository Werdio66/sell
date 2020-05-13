package com.lx.sell.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 卖家信息
 *
 * @author Werdio丶
 * @since 2020-05-12 12:39:26
 */
@Data
public class SellerInfo implements Serializable {
    private static final long serialVersionUID = -12634912884533020L;
    
    private String id;
    
    private String username;
    
    private String password;
    /**
    * 微信openid
    */
    private String openid;
    /**
    * 创建时间
    */
    private LocalDateTime createTime;
    /**
    * 修改时间
    */
    private LocalDateTime updateTime;
}