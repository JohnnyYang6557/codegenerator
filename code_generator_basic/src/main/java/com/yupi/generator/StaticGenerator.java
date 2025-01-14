package com.yupi.generator;

import cn.hutool.core.io.FileUtil;
import lombok.val;

import java.io.File;

public class StaticGenerator {
    public static void main(String[] args) {
//        String projectPath = System.getProperty("user.dir");
//        System.out.println(projectPath);
//
//        String srcPath = projectPath + File.separator +  "code_generator_demo_projects" + File.separator + "acm-template";
//        String destPath = projectPath;
//        copyFilesByHutool(srcPath,destPath);
    }

    /**
     * 使用 Hutool 工具类复制文件或目录
     *
     * @param srcPath 源文件或目录路径
     * @param destPath 目标文件或目录路径
     * @return 操作结果信息
     */
    public static void copyFilesByHutool(String srcPath, String destPath) {
        // 使用 Hutool 的 FileUtil.copy 方法复制文件或目录，第三个参数 true 表示如果目标目录不存在则创建
        FileUtil.copy(srcPath,destPath,true);
        // 返回操作结果信息
    }

}
