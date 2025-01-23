package com.eggs.maker.generator.main;

import freemarker.template.TemplateException;

import java.io.IOException;

public class MainGenerator extends GenerateTemplate{

    @Override
    protected void buildDist(String outputPath, String jarPath, String shellOutputFilePath, String sourceCopyDestPath) {
        System.out.println("do not buildDist");
    }



}
