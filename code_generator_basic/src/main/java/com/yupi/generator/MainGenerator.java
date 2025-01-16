package com.yupi.generator;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.jar.Manifest;

import static com.yupi.generator.DynamicGenerator.doGenerate;
import static com.yupi.generator.StaticGenerator.copyFilesByHutool;

public class MainGenerator {
    public static void doGenerate(Object model) throws TemplateException, IOException {

        //静态文件生成
        //获取项目目录
        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();
        String srcPath = parentFile + File.separator + "code_generator_demo_projects"+ File.separator + "acm-template";
        String destPath = projectPath;
        copyFilesByHutool(srcPath, destPath);


        //在生成的静态文件基础上，生成动态文件生成
        //C:\Users\ycyg6\IdeaProjects\code_generator\acm-template\src\com\yupi\acm\MainTemplate.java
        String dynamicInputPath = projectPath + File.separator  + "src\\main\\resources\\templates\\MainTemplate.java.ftl";
        String dynamicOutputPath = projectPath + File.separator + "acm-template\\src\\com\\yupi\\acm\\MainTemplate.java";
        DynamicGenerator.doGenerate(dynamicInputPath, dynamicOutputPath, model);
        System.out.println("MainGenerator done.");
    }

    public static void main(String[] args) throws TemplateException, IOException {
//        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
//        mainTemplateConfig.setAuthor("eggs");
//        mainTemplateConfig.setLoop(true);
//        mainTemplateConfig.setOutputText("output result = ");
//        doGenerate(mainTemplateConfig);
    }
}
