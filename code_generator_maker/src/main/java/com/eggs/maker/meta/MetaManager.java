package com.eggs.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

public class MetaManager {

    //Meta对象单例模式
    private static volatile  Meta meta;
    public  static Meta getMeta(){
        if (meta == null){
            synchronized (MetaManager.class){
                if (meta == null) {
                    meta = initMeta();
                }
            }
        }
        return meta;
    }


    private static Meta initMeta(){
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta bean = JSONUtil.toBean(metaJson, Meta.class);
        //TODO 检验配置文件，处理默认值
        return bean;
    }


//    public static void main(String[] args) {
//        String metaJson = ResourceUtil.readUtf8Str("meta.json");
//        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
//        System.out.println(newMeta);
//    }
}
