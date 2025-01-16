package com.eggs.maker.generator.file;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

import static com.eggs.maker.generator.file.StaticFileGenerator.copyFilesByHutool;

public class FileGenerator {
    public static void doGenerate(Object model) throws TemplateException, IOException {

        //静态文件生成
        //获取项目目录
        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();
        String srcPath = parentFile + File.separator + "code_generator_demo_projects"+ File.separator + "acm-template";
        copyFilesByHutool(srcPath, projectPath);


        //在生成的静态文件基础上，生成动态文件生成
        //C:\Users\ycyg6\IdeaProjects\codegenerator\acm-template\src\com\yupi\acm\MainTemplate.java
        String dynamicInputPath = projectPath + File.separator  + "src\\main\\resources\\templates\\MainTemplate.java.ftl";
        String dynamicOutputPath = projectPath + File.separator + "acm-template\\src\\com\\yupi\\acm\\MainTemplate.java";
        DynamicFileGenerator.doGenerate(dynamicInputPath, dynamicOutputPath, model);
        System.out.println("MainGenerator done.");
    }

}
