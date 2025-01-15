package com.yupi.cli.command;


import cn.hutool.core.bean.BeanUtil;
import com.yupi.generator.MainGenerator;
import com.yupi.model.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "generate",mixinStandardHelpOptions = true,description = "生成模板文件")
@Data
public class GenerateCommand implements Callable<Integer> {


    @Option(names = {"-l", "--loop"}, description = "是否循环", arity = "0..1", interactive = true,echo = true)
    private boolean loop;

    @Option(names = {"-a", "--author"}, description = "作者名称", arity = "0..1", interactive = true,echo = true)
    private String author = "匿名作者";

    @Option(names = {"-o", "--outputText"}, description = "输出文本", arity = "0..1", interactive = true,echo = true)
    private String outputText = "sum = ";

    @Override
    public Integer call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        System.out.println("配置信息" + mainTemplateConfig);
        MainGenerator.doGenerate(mainTemplateConfig);
        return 0;
    }
}
