package com.space.db.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * mybatis-plus代码生成器
 */
public class MybatisPlusCodeGenerator {
    /**
     * 数据库配置
     */
    private static final String URL = "jdbc:mysql://localhost:3306/relax_space?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123";

    private static final String JAVA_PATH = System.getProperty("user.dir") + "/db/src/main/java";

    private static final String XML_PATH = System.getProperty("user.dir") + "/db/src/main/resources/mapper";

    private static final List<String> TABLE_NAME_LIST = Arrays.asList(
            "rs_forum_blog",
            "rs_forum_blog_content",
            "sys_account",
            "sys_file_storage",
            "sys_schedule",
            "sys_session",
            "sys_user"
    );

    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(URL,USER_NAME,PASSWORD);
    public static void main(String[] args) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                .globalConfig(builder -> builder
                        .author("zjy")
                        .outputDir(JAVA_PATH)
                        .disableOpenDir()
                )
                .packageConfig(builder -> builder
                        .parent("com.space")
                        .moduleName("db")
                        .entity("entity")
                        .mapper("mapper")
                        .xml("mapper.xml")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("web")
                        .pathInfo(Collections.singletonMap(OutputFile.xml,XML_PATH))
                )
                .strategyConfig(builder -> builder
                        .addInclude(TABLE_NAME_LIST)
                        .addTablePrefix("rs_forum_","sys_")
                        // 实体配置构建者
                        .entityBuilder()
                        .enableLombok()
                        .enableChainModel()
                        .idType(IdType.ASSIGN_ID)
                        .logicDeletePropertyName("deletedFlag")
                        .addTableFills(new Property("deletedFlag", FieldFill.INSERT))
                        .addTableFills(new Column("add_time",FieldFill.INSERT))
                        .addTableFills(new Column("add_member",FieldFill.INSERT))
                        .addTableFills(new Column("edit_time",FieldFill.INSERT_UPDATE))
                        .addTableFills(new Column("edit_member",FieldFill.INSERT_UPDATE))
                        .fileOverride()
                        // Mapper配置构建者
                        .mapperBuilder()
                        .formatMapperFileName("%sMapper")
                        .formatXmlFileName("%sMapper")
                        // 控制器配置构建者
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .build()
                )
                // 自定义模板配置
                .templateConfig(builder -> builder
                        .disable(TemplateType.CONTROLLER,TemplateType.SERVICE,TemplateType.SERVICEIMPL)
                        .build()
                )
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
