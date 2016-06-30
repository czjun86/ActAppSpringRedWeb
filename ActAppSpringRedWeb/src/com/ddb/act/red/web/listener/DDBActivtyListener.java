package com.ddb.act.red.web.listener;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import kkd.common.dao.dbuitl.JDBC;
import kkd.common.logger.LogWriter;
import kkd.common.util.ConfigUtil;

import com.ddb.activity.base.config.ActConfig;
import com.ddb.activity.base.util.DDBActivtyDataSourceProviderImpl;

import ddb.util.pubappaccess.PublicAppManager;
import ddb.webconf.resfactory.ConfigurePool;
import ddb.webconf.resource.conf.IConfigure;

/**
 * ActCardSystem 广告初始处理
 *
 */
public class DDBActivtyListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent even) {
    	LogWriter.setDefaultLogger();
    	if(ActConfig.getGlobalDebug()){
    		ConfigUtil.setDefaultConfig(ActConfig.getGlobalDebugKey()+"config");
    		JDBC.setDefultDataSourceProvider(DDBActivtyDataSourceProviderImpl.getInstance(), "test");
    	}else{
    		JDBC.setDefultDataSourceProvider(DDBActivtyDataSourceProviderImpl.getInstance(), "java:comp/env/jdbc/ddbActivity");
    	}
    	//加载pubApps
    	initPubApps(even.getServletContext());
    	loadConfig();

    	LogWriter.debug(even.getServletContext().getContextPath()+" contextInitialized");
    } 
    
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
	
	public void loadConfig(){
		//1设置项目名
	    ConfigurePool.setProjectName("ActAppSpringRedWeb");
	    //2取标准配置文件对象,这个引用外部系统可以持有
	    IConfigure ic = ConfigurePool.getStdConfig();
	    //3读取配置的键值对值
	    com.ddb.act.red.util.ConfigUtil.configure=ic;
	}
	
	private void initPubApps(ServletContext context) {
		InputStream istream = null;
		try {
			istream = context.getResourceAsStream("/WEB-INF/pubApps.xml");
			// 初始化pubApps
			PublicAppManager pam = PublicAppManager.getInstance();
			pam.init(istream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (istream != null){
				try {
					istream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
