/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.jerseysys.util;

/**
 *
 * @author admin
 */
import com.netnimblelabs.jerseysys.config.MyScheduler;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.apache.log4j.Logger;

@WebListener
public class MyApplicationStartup implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(MyApplicationStartup.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Display ASCII art
//        ASCIIArtUtil.displayASCIIArt("Your Application Name");
        // Start the scheduler

        System.out.println(" _______  _        _______ \n"
                + "(  ____ \\( (    /|(  ____ )\n"
                + "| (    \\/|  \\  ( || (    )|\n"
                + "| (_____ |   \\ | || (____)|\n"
                + "(_____  )| (\\ \\) ||     __)\n"
                + "      ) || | \\   || (\\ (   \n"
                + "/\\____) || )  \\  || ) \\ \\__\n"
                + "\\_______)|/    )_)|/   \\__/\n"
                + "                           ");
        
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
    }
}
