package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class CodeGenerator {
	/**模块名字**/
	public final static String MODULE_NAME="test";
	/**父级包名*/
	public final static String BASE_PACKAGE_PARENT="com.xiaoshabao";
	/**表名前缀，转换时会自动去除*/
	public final static String[] TABLE_PREFIX= { "td_m_", "td_ui_","td_" };
	
	
	

    public static void main(String[] args) {
    	
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("manxx");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://47.75.206.89:3306/shabao_test?useUnicode=true&characterEncoding=utf8&useSSL=false");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("master");
        dsc.setPassword("19911105man");
        mpg.setDataSource(dsc);
        
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        /*
        gc.setMapperName("%sDao");
        gc.setXmlName("%sDao");
    	gc.setServiceName("%sService");
    	gc.setServiceImplName("%sServiceImpl");
    	gc.setControllerName("%sController");
    	*/
    	//xml设置
    	gc.setBaseResultMap(true);// XML ResultMap
    	gc.setBaseColumnList(true);// XML columList
    	gc.setEnableCache(false);// XML 二级缓存

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(BASE_PACKAGE_PARENT);
        pc.setModuleName(MODULE_NAME);//可以单独设置输入模块名字
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setRestControllerStyle(true);
        strategy.setSuperServiceImplClass("com.xiaoshabao.base.mybatis.service.impl.BaseServiceMybatisImpl");
        strategy.setInclude(scanner("表名"));
//        strategy.setSuperEntityColumns("id");//公共字段
        strategy.setTablePrefix(TABLE_PREFIX);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
    
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
	public static String scanner(String tip) {
		String result = null;
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				result = ipt;
			}
		}
		scanner.close();
		if (result == null) {
			throw new MybatisPlusException("请输入正确的" + tip + "！");
		} else {
			return result;
		}
	}

}
