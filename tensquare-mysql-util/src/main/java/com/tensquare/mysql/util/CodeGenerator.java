package com.tensquare.mysql.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {


    public static void main(String[] args) {
        String moduleName = "notice";
        FastAutoGenerator.create("jdbc:mysql://10.0.50.55:3307/tensquare_" + moduleName + "?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&serverTimezone=GMT%2B8", "root", "root123@eCloud")
                .globalConfig(builder -> {
                    builder.author("hanlei") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\hanlei\\Documents\\Project\\ten-square\\tensquare-" + moduleName + "\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.tensquare") // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "C:\\Users\\hanlei\\Documents\\Project\\ten-square\\tensquare-" + moduleName + "\\src\\main\\resources\\mapper\\" + moduleName)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder
//                            .addInclude("LICENSE"); // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}