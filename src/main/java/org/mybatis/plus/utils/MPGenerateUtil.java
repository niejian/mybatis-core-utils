package org.mybatis.plus.utils;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import org.mybatis.plus.vo.GenerateConfigVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @desc: org.mybatis.plus.utils.MPGenerateUtil
 * @author: niejian9001@gmail.com
 * @date: 2019-09-16 11:09
 */
public class MPGenerateUtil {
    private static final Logger logger = LoggerFactory.getLogger(MPGenerateUtil.class);


    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (!StringUtils.isEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    public static void generate(GenerateConfigVo generateConfigVo) {

        if (null == generateConfigVo) {
            return;
        }


        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        //final String relateProjectPath = scanner("请输入项目相对路劲：如 demo-parent/demo-user");

        final String relateProjectPath = generateConfigVo.getRelativeProjectPath();
        if (StringUtils.isEmpty(relateProjectPath)) {
            logger.error("请输入项目相对路劲：如 demo-parent/demo-user");
            return;
        }
        //输入模块名 admin, base product
        String eshopModelName = generateConfigVo.getModelName();
        //String eshopModelName = scanner("请输入模块名：如 order");
        if (StringUtils.isEmpty(eshopModelName)) {
            logger.error("请输入模块名：如 order");
            return;
        }
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setDateType(DateType.ONLY_DATE);
        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/eshop-"+eshopModelName+"/src/main/java");
        gc.setOutputDir(projectPath + "/" +relateProjectPath + "/src/main/java");
        gc.setOpen(false);
        //设置为true，xml文件能生成baseResultMap等信息
        gc.setBaseColumnList(true).setBaseResultMap(true);
        gc.setAuthor("code generator");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
//        String jdbcurl = scanner("请输入数据库url：如 localhost:3306");
//        String dbName = scanner("请输入数据库名：如 demo");
        String jdbcUrl = generateConfigVo.getJdbcUrl();
        if (StringUtils.isEmpty(eshopModelName)) {
            logger.error("请输入链接数据库url：如 localhost:3306");
            return;
        }
        dsc.setUrl(jdbcUrl);
        //dsc.setUrl("jdbc:mysql://"+jdbcurl+"/"+ dbName +"?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        //String dbUserName = scanner("请输入数据库账户名");
        String dbUserName = generateConfigVo.getDbUserName();
        if (StringUtils.isEmpty(dbUserName)) {
            logger.error("请输入数据库账户名");
            return;
        }
        dsc.setUsername(dbUserName);
        //String dbPwd = scanner("请输入数据库密码");
        String dbPwd = generateConfigVo.getDbPwd();
        if (StringUtils.isEmpty(dbUserName)) {
            logger.error("请输入数据库密码");
            return;
        }
        dsc.setPassword(dbPwd);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();

        //String basePackeName = scanner("请输入基本包路径，如：cn.com.org");
        String basePackeName = generateConfigVo.getBasePackeName();
        if (StringUtils.isEmpty(dbUserName)) {
            logger.error("请输入基本包路径，如：cn.com.org");
            return;
        }

        final String basePackeNameTmp = basePackeName;

        pc.setParent(basePackeName + "." + eshopModelName);
        mpg.setPackageInfo(pc);



        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {

            @Override
            public String outputFile(TableInfo tableInfo) {
                String modelName = pc.getModuleName();
                if (null == modelName) {
                    modelName = "";
                }
                // 通过输入的模块名字定义输出文件名
//
                String basePackagePath = basePackeNameTmp.replace(".", "/");
                // xml文件生成到resource目录下
                return projectPath + "/" + relateProjectPath + "/src/main/resources/mapper/" +  tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//                return projectPath + "/" + relateProjectPath + "/src/main/java/" + basePackagePath +"/mapper/" + modelName
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//                return projectPath +  eshopModelName + "/src/main/java/cn/com/eshop/" + eshopModelName +"/mapper/" + modelName
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig = new TemplateConfig()
                .setController("templates/controller.java")
                .setEntity("templates/entity.java")
                .setServiceImpl("templates/serviceImpl.java");

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表前缀

        List<String> tableNames = generateConfigVo.getTableNames();
        if (CollectionUtils.isEmpty(tableNames)) {
            logger.error("请输入对应的表名");
        }
        strategy.setInclude(tableNames.stream().toArray(String[] ::new));



        //strategy.setTablePrefix(new String[]{tablePrefix});
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("cn.com.vandesr.admin.entity");
        // 是否试用lomok
        Boolean useLomok = generateConfigVo.getUseLomok();
        if (null == useLomok) {
            useLomok = false;
        }
        strategy.setEntityLombokModel(useLomok);
        strategy.setRestControllerStyle(true);
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");

//        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();


    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        list.add("sys_menu");
//        list.add("sys_role");
        list.add("sys_user");
        GenerateConfigVo vo = new GenerateConfigVo();
        vo.setBasePackeName("cn.com.demo.admin");
        vo.setUseLomok(false);
        vo.setTableNames(list);
        vo.setRelativeProjectPath("middleware/middleware-mybatis");
        vo.setModelName("admin");
        vo.setJdbcUrl("jdbc:mysql://yun2:3306/bluestart?useUnicode=true&useSSL=false&characterEncoding=utf8");
        vo.setDbUserName("root");
        vo.setDbPwd("code4fun1234");

        generate(vo);
    }



}
