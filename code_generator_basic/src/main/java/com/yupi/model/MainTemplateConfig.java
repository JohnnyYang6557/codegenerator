package com.yupi.model;

import lombok.Data;

/**
 * 静态模板配置
 */

@Data
public class MainTemplateConfig {
    private String author = "匿名作者";

    private String outputText = "输出结果为：";

    private boolean loop;
}
