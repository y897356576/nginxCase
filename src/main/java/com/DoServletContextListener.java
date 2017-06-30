package com;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by 石头 on 2017/6/20.
 */
public class DoServletContextListener implements ServletContextListener {

    //随web应用的启动而启动，只初始化一次，随web应用的停止而销毁

    private Logger logger = LogManager.getLogger("myLogger");

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext初始化完成...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        CacheManager cacheManager = CacheManager.create();
        Cache cache = cacheManager.getCache("ehcacheFir");

        logger.info("[From Fir] Current quantity_all is : " + cache.get("count_all").getObjectValue().toString());
        logger.info("[From Fir] Current quantity_fir is : " + cache.get("count_fir").getObjectValue().toString());

        cache.flush();
        cacheManager.shutdown();

        System.out.println("ServletContext销毁完成...");
    }
}
