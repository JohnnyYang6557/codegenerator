package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class DynamicGenerator {

    public static void main(String[] args) throws IOException, TemplateException {
//        String projectPath = System.getProperty("user.dir") + File.separator + "code_generator_basic";
//        String inputPath = projectPath + File.separator + "src\\main\\resources\\templates\\MainTemplate.java.ftl";
//        String outputPath = projectPath + File.separator + "MainTemplate.java";
//        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
//        mainTemplateConfig.setAuthor("eggs");
//        mainTemplateConfig.setOutputText("output result：");
//        mainTemplateConfig.setLoop(true);
//        doGenerate(inputPath,outputPath,mainTemplateConfig);

    }

    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        //指定数字格式
        configuration.setNumberFormat("0.######");

        // 指定模板文件所在的路径
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);



        // 输出文件
        Writer out = new FileWriter(outputPath);

        // 模板输出
        template.process(model, out);
        out.close();
    }
}
