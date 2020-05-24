package com.lx.sell.utils;

import com.lx.sell.enums.CodeEnum;

/**
 * @author Werdio丶
 * @since 2020-05-24 13:35:00
 */
public class EnumUtil {

    public static <T extends CodeEnum>T getMsgByCode(Integer code, Class<T> clazz){

        for (T enumConstant : clazz.getEnumConstants()) {
            if (enumConstant.getCode().equals(code)){
                return enumConstant;
            }
        }

        return null;
    }
}
