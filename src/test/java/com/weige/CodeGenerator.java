package com.weige;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.100.66/xdb";
        String username = "root";
        String password = "Weige@309";
        String author = "laocai";
        String outputDir = "D:\\java-dev\\admin\\src\\main\\java";
        String basePackage = "com.weige";
        String moduleName = "sys";
        String mapperLocation = "D:\\java-dev\\admin\\src\\main\\resources\\mapper\\" + moduleName;
        String tableName = "x_user,x_menu,x_role,x_role_menu,x_user_role";
        String tablePrefix = "x_";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // ��������
                            //.enableSwagger() // ���� swagger ģʽ
                            //.fileOverride() // �����������ļ�
                            .outputDir(outputDir); // ָ�����Ŀ¼
                })
                .packageConfig(builder -> {
                    builder.parent(basePackage) // ���ø�����
                            .moduleName(moduleName) // ���ø���ģ����
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // ����mapperXml����·��
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName) // ������Ҫ���ɵı���
                            .addTablePrefix(tablePrefix); // ���ù��˱�ǰ׺
                })
                .templateEngine(new FreemarkerTemplateEngine()) // ʹ��Freemarker����ģ�壬Ĭ�ϵ���Velocity����ģ��
                .execute();
    }

}
