package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

import static com.yupi.generator.DynamicGenerator.doGenerate;
import static com.yupi.generator.StaticGenerator.copyFilesByHutool;

public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException {

        //静态文件生成
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);

        String srcPath = projectPath + File.separator +  "code_generator_demo_projects" + File.separator + "acm-template";
        String destPath = projectPath;
        copyFilesByHutool(srcPath,destPath);


        //在生成的静态文件基础上，生成动态文件生成
        //C:\Users\ycyg6\IdeaProjects\code_generator\acm-template\src\com\yupi\acm\MainTemplate.java
        String dynamicInputPath = projectPath + File.separator + "code_generator_basic" + File.separator+ "src\\main\\resources\\templates\\MainTemplate.java.ftl";
        String dynamicOutputPath = projectPath + File.separator + "acm-template\\src\\com\\yupi\\acm\\MainTemplate.java";
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("eggs");
        mainTemplateConfig.setOutputText("输出结果为：");
        mainTemplateConfig.setLoop(true);
        doGenerate(dynamicInputPath,dynamicOutputPath,mainTemplateConfig);
    }
}
