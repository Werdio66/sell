package com.lx.sell.utils;

import java.util.UUID;

/**
 * 生成主键
 *
 * @author Werdio丶
 * @since 2020-05-14 14:43:06
 */
public class KeyUtil {

    /**
     *  生成 8 位字母和数字组合 字符串
     */
    public static String getUniqureKey(){
        return UUID.randomUUID().toString().substring(28);
    }


    public static void main(String[] args) {
        System.out.println(getUniqureKey());
    }
}
