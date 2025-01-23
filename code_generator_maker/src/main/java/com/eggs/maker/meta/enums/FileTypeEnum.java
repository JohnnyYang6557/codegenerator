package com.eggs.maker.meta.enums;


import lombok.Getter;

/**
 * 文件类型注解
 */

@Getter
public enum FileTypeEnum {

    DIR("目录","text"),
    FILE("文件","file");

    private final String text;
    private final String value;


    FileTypeEnum(String text, String value){
        this.text = text;
        this.value = value;

    }

}
