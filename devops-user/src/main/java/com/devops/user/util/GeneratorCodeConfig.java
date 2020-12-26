package com.devops.user.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/9/27
 * @description：
 */
public class GeneratorCodeConfig {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        //Applications/work/project/springcloud/springcloud-user/src/main/java
        gc.setOutputDir(projectPath + "/springcloud-user/src/main/java");
        gc.setAuthor("shuai");
        gc.setOpen(false);
        //覆盖已有文件
        gc.setFileOverride(true);
        //实体属性 Swagger2 注解
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);
        // 数据源配置
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/user_info?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                //System.out.println("转换类型：" + fieldType);
                //tinyint转换成Boolean
                if (fieldType.toLowerCase().contains("tinyint")) {
                    return DbColumnType.INTEGER;
                }
                //将数据库中datetime转换成date
                if (fieldType.toLowerCase().contains("datetime")) {
                    return DbColumnType.DATE;
                }
                if (fieldType.toLowerCase().contains("smallint")) {
                    return DbColumnType.INTEGER;
                }
                return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
            }
        });
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.springcloud.user");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);


        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig.setMapper("/templates/mapper.java");
//        templateConfig.setXml(null);
        //空字符串不生成controller，否则会生成controller
        templateConfig.setController("");

        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        strategy.setEntityLombokModel(true);
        // 公共父类
//        strategy.setSuperControllerClass("com.baomidou.ant.com.devops.base.common.BaseController");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        List<String> tables = new ArrayList<>();
//        tables.add("user_info");
        tables.add("user_role_rel");
        strategy.setInclude(tables.toArray(new String[0]));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
