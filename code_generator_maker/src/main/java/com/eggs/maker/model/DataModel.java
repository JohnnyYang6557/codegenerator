package com.eggs.maker.model;

import lombok.Data;

/**
 * 静态模板配置
 */

@Data
public class DataModel {
    /*
     * 作者
     */
    private String author = "匿名作者";

    /*
     * 输出文本
     */
    private String outputText = "输出结果为：";

    /*
     * 是否循环
     */
    private boolean loop;
}
