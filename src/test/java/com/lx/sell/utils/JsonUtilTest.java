package com.lx.sell.utils;

import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Werdio丶
 * @since 2020-05-24 15:56:16
 */
class JsonUtilTest {

    @Data
    static class Dog {
        private String name;
        public Dog(String name){
            this.name = name;
        }
    }

    @Test
    void toJson() {
        Dog dog = new Dog("小狗");
        String json = JsonUtil.toJson(dog);
        System.out.println(json);
    }

    @Test
    void formJson() {
        String json = "{\"name\":\"小狗\"}";
        Dog dog = JsonUtil.formJson(json, new TypeToken<Dog>() {
        }.getType());

        System.out.println(dog);
    }
}