package com.eggs.maker.meta.enums;


import lombok.Getter;

/**
 * 模型类型注解
 */

@Getter
public enum ModelTypeEnum {

    STRING("字符串","string"),
    BOOLEAN("布尔","boolean");

    private final String text;
    private final String value;


    ModelTypeEnum(String text, String value){
        this.text = text;
        this.value = value;

    }

}
