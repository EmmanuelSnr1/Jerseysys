package com.netnimblelabs.jerseysys.util;

import com.netnimblelabs.jerseysys.config.MyScheduler;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

@WebListener
public class MyApplicationStartup implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(MyApplicationStartup.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Display ASCII art
        System.out.println(" _______  _        _______ \n"
                + "(  ____ \\( (    /|(  ____ )\n"
                + "| (    \\/|  \\  ( || (    )|\n"
                + "| (_____ |   \\ | || (____)|\n"
                + "(_____  )| (\\ \\) ||     __)\n"
                + "      ) || | \\   || (\\ (   \n"
                + "/\\____) || )  \\  || ) \\ \\__\n"
                + "\\_______)|/    )_)|/   \\__/\n"
                + "                           ");

        // Initialize Hibernate
        try {
            HibernateUtil.getSessionFactory();
            logger.info("Hibernate SessionFactory initialized successfully.");
        } catch (Exception e) {
            logger.error("Failed to initialize Hibernate SessionFactory", e);
        }

        // Start the scheduler
        MyScheduler.getInstance();
        logger.info("Scheduler started successfully.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup code if necessary
        try {
            Scheduler scheduler = MyScheduler.getInstance();
            if (scheduler != null) {
                scheduler.shutdown();
                logger.info("Scheduler shut down successfully.");
            }
        } catch (SchedulerException e) {
            logger.error("Failed to shut down the scheduler", e);
        }

        // Close Hibernate SessionFactory
        try {
            HibernateUtil.shutdown();
            logger.info("Hibernate SessionFactory shut down successfully.");
        } catch (Exception e) {
            logger.error("Failed to shut down Hibernate SessionFactory", e);
        }
    }
}
