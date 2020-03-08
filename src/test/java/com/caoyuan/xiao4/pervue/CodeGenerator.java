package com.caoyuan.xiao4.pervue;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    /**
     * 实体类中使用的注解
     *
     * @TableField(value="names") //解决字段名不一致
     * @TableField(select=false) //字段不加入查询
     * @TableField(exist=false) //字段在数据表中不存在
     */

    ////mybatis-plus分页插件,放在启动类中
    //@Bean
    //public PaginationInterceptor paginationInterceptor() {
    //    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    //    // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
    //    paginationInterceptor.setOverflow(true);
    //    return paginationInterceptor;
    //}
    public static void main(String[] args) {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        //如 每张表都有一个创建时间、修改时间
        //而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
        //修改时，修改时间会修改，
        //虽然像Mysql数据库有自动更新机制，但像ORACLE的数据库就没有了，
        //使用公共字段填充功能，就可以实现，自动按场景更新了。
        //如下是配置
        TableFill createField = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill modifiedField = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        tableFillList.add(createField);
        tableFillList.add(modifiedField);

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局策略配置
        GlobalConfig gc = new GlobalConfig();
        //输出路径
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("caoyuan");        //设置作者
        gc.setBaseResultMap(true);      //Mapper.xml包含 ResultMap
        gc.setFileOverride(true);       //是否覆盖文件
        gc.setBaseColumnList(true);     //Mapper.xml包含sql片段
        gc.setOpen(false);              //输出是否打开
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // .setEntityName("%sEntity");                      默认null生成User
        // .setXmlName("%sMapper").setMapperName("%sDao");  默认UserMapper
        // .setServiceName("%sService")                     默认UserService
        // .setServiceImplName("%sServiceImpl")             默认UserServiceImpl
        // .setControllerName("%sController")               默认UserController
        mpg.setGlobalConfig(gc);

        // 数据源配置，通过该配置，指定需要生成代码的具体数据库
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);        // 数据库类型
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                String t = fieldType.toLowerCase();
                if (t.contains("date")) {  //默认生成的是localdatetime,这里改为Date
                    return DbColumnType.DATE;
                }
                if (t.contains("blob")) {  //默认生成的是Blob,这里改为byte[]
                    return DbColumnType.BYTE_ARRAY;
                }
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
        String database = scanner("数据库名");
        dsc.setUrl("jdbc:mysql://localhost:3306/" + database + "?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Hongkong");
        // dsc.setSchemaName("dataBaseName");

        dsc.setDriverName("com.mysql.cj.jdbc.Driver");  //mysql 6.0以上版本驱动
        //dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包名配置，通过该配置，指定生成代码的包路径
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));

        pc.setParent(CodeGenerator.class.getPackage().getName());   //设置包名

        //pc.setEntity("domain");                   // 这里是实体类包名，默认 entity
        // .setController("controller").setXml("mapper").setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 注入配置，通过该配置，可注入自定义参数等操作以实现个性化操作
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
                // 自定义输出文件名 ,如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mappers/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 模板配置，可自定义代码生成的模板，实现个性化操作
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 数据库表配置，通过该配置，可指定需要生成哪些表或者排除哪些表
        StrategyConfig strategy = new StrategyConfig();
        //数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //【实体】是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        // 把gmt_create,gmt_modified两个字段功能打开,添加和修改的时候,不用在代码里更新时间,它自动更新
        //strategy.setTableFillList(tableFillList);

        //这个开着的话,会把@RestController注解打开
        strategy.setRestControllerStyle(true);
        //这个开着的话,会把Controller上边的Mapping里边变成("/user-addr")这种,而不是("/userAddr")
        //strategy.setControllerMappingHyphenStyle(true);

        // 公共父类
        //strategy.setSuperEntityClass("你自己的父类实体类,没有就不用设置!");
        //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        //strategy.setSuperEntityColumns("id");

        //手动输入表名
        //strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //模糊匹配表名
        LikeTable likeTable = new LikeTable(scanner("表名,模糊匹配前缀,全选输入%"), SqlLike.RIGHT);
        strategy.setLikeTable(likeTable);

        //排除前缀(模块名_)
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        //手动输入要排除的前缀(cms_)
        strategy.setTablePrefix(scanner("要排除的前缀"));

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}