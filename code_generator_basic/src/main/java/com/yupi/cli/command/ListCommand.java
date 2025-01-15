package com.yupi.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

@Command(name = "list",mixinStandardHelpOptions = true,description = "列出文件")
public class ListCommand implements Runnable {

    @Override
    public void run() {
        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();
        String inputPath = new File(parentFile, "code_generator_demo_projects/acm-template").getAbsolutePath();
        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }
}
