package com.eggs.maker.generator.file;

import cn.hutool.core.io.FileUtil;

public class StaticFileGenerator {

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
