package com.eggs.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.eggs.maker.generator.JarGenerator;
import com.eggs.maker.generator.ScriptGenerator;
import com.eggs.maker.generator.file.DynamicFileGenerator;
import com.eggs.maker.meta.Meta;
import com.eggs.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;


public abstract class GenerateTemplate {
    public void doGenerate() throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMeta();
        System.out.println(meta);
        //输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated" + File.separator + "acm-template-pro-generator";
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        //1. 复制原始文件
        String sourceCopyDestPath = copySource(meta, outputPath);

        //2. 生成代码
        generateCode(meta, outputPath);

        //3. 构建 jar 包
        String jarPath = buildJar(outputPath, meta);

        //4. 封裝脚本
        String shellOutputFilePath = buildScript(outputPath, jarPath);

        //5. 生成精简版的程序
        buildDist(outputPath, jarPath, shellOutputFilePath, sourceCopyDestPath);

    }

    protected void buildDist(String outputPath, String jarPath, String shellOutputFilePath, String sourceCopyDestPath) {
        String distOutPutPath = outputPath + "-dist";
        //拷贝jar包
        String targetAbsolutePath = distOutPutPath + File.separator + "target";
        FileUtil.mkdir(targetAbsolutePath);
        String jarAbsolutePath = outputPath + File.separator + jarPath;
        FileUtil.copy(jarAbsolutePath, targetAbsolutePath, true);
        //拷贝脚本文件
        FileUtil.copy(shellOutputFilePath, distOutPutPath, true);
        FileUtil.copy(shellOutputFilePath + ".bat", distOutPutPath, true);
        //拷贝源模板文件
        FileUtil.copy(sourceCopyDestPath, distOutPutPath, true);
    }


    protected String buildScript(String outputPath, String jarPath) {
        String shellOutputFilePath = outputPath + File.separator + "generator";
        ScriptGenerator.doGenerator(shellOutputFilePath, jarPath);
        return shellOutputFilePath;
    }

    protected String buildJar(String outputPath, Meta meta) throws IOException, InterruptedException {
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        JarGenerator.doGenerate(outputPath);
        return jarPath;
    }

    protected void generateCode(Meta meta, String outputPath) throws IOException, TemplateException {
        //读取resources目录
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();
//        String inputResourcePath = "C:\\Users\\ycyg6\\IdeaProjects\\codegenerator\\code_generator_maker\\src\\main\\resources";
//        System.out.println(inputResourcePath);

        //java包的基础路径
        String outputBasePackage = meta.getBasePackage();
        //com/eggs
        String outBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java" + File.separator + outBasePackagePath;

        String inputFilePath;
        String outputFilePath;
        //model.DataModel
        inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "model" + File.separator + "DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.ConfigCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.GenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.ListCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.CommandExecutor
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // Main
        inputFilePath = inputResourcePath + File.separator + "templates/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);


        // generator.DynamicGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/DynamicGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/DynamicGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.MainGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/MainGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/MainGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.StaticGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/StaticGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/StaticGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);


        // pom.xml
        inputFilePath = inputResourcePath + File.separator + "templates/pom.xml.ftl";
        outputFilePath = outputPath + File.separator + "pom.xml";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        //生成README.md
        inputFilePath = inputResourcePath + File.separator + "templates/README.md.ftl";
        outputFilePath = outputPath + File.separator + "READ.md";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
    }

    protected String copySource(Meta meta, String outputPath) {
        //从原始模板文件路径复制到生成代码包中
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        String sourceCopyDestPath = outputPath + File.separator + ".source";
        FileUtil.copy(sourceRootPath, sourceCopyDestPath, true);
        return sourceCopyDestPath;
    }


}
