package com.rm.pais;


import java.util.logging.Logger;

import com.rm.pais.internal.DataRepo;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {

	
    @Inject
	private DataRepo dataRepoSingleton;

    private static final Logger logger = Logger.getLogger(BootstrapListener.class.getName());
    
	@Override
    public void contextInitialized(ServletContextEvent sce) {
        // Code to be executed during application startup
		logger.info("Bootstrap proccess started");
        
		
        // Initialize singleton that will retrieve external data and store and periodically refresh
		logger.info("initialize REST Client and data Repo");
        dataRepoSingleton.initialize();
        
        logger.info("Bootstrap proccess finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Code to be executed during application shutdown
    	logger.info("Application is shutting down...");
    }
}
