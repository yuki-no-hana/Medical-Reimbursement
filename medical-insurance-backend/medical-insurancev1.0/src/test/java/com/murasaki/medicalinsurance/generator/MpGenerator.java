package com.murasaki.medicalinsurance.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 自动生成代码，隐藏功能
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MpGenerator {
	private static String dirverName = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://192.168.117.129:3306/bracelet?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8";
	private static String user = "root";
	private static String password = "123456";

	private static String author = "max";
	private static String outputDir = "D:/worksource/Bracelet/bracelet-back/src/main/java";
	private static String subPackageName = "demo";
	private static String tableName1 = "bstudent";

	@Test
	public void generatorSource() {
		AutoGenerator mpg = new AutoGenerator();

		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(outputDir);
		gc.setFileOverride(true);
		gc.setActiveRecord(false);
		gc.setEnableCache(false);
		gc.setBaseResultMap(false);
		gc.setBaseColumnList(false);
		gc.setAuthor(author);// 作者

		gc.setControllerName("%sCtl");
		gc.setServiceName("%sSer");
		gc.setServiceImplName("%sSerImpl");
		gc.setMapperName("%sDao");
		mpg.setGlobalConfig(gc);

		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setDriverName(dirverName);
		dsc.setUrl(url);
		dsc.setUsername(user);
		dsc.setPassword(password);

		mpg.setDataSource(dsc);

		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setInclude(new String[] { tableName1 });

		//strategy.setSuperServiceClass(superCls);
		strategy.setSuperServiceImplClass("ServiceImpl");
		strategy.setSuperMapperClass("BaseMapper");
		strategy.setEntityTableFieldAnnotationEnable(true);
		strategy.setEntityLombokModel(true);

		mpg.setStrategy(strategy);

		PackageConfig pc = new PackageConfig();
		pc.setParent("com.max.bracelet.braceletapi");
		pc.setController(subPackageName + ".api");
		pc.setService(subPackageName + ".service");
		pc.setServiceImpl(subPackageName + ".service.impl");
		pc.setMapper(subPackageName + ".dao");
		pc.setEntity(subPackageName + ".entity");
		mpg.setPackageInfo(pc);

		// 执行生成
		mpg.execute();
	}
}
