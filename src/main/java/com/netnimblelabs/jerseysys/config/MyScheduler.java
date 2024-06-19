/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.jerseysys.config;

/**
 *
 * @author admin
 */
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class MyScheduler {

    private static Scheduler scheduler;

    private MyScheduler() {
        // private constructor to prevent instantiation
    }

    public static Scheduler getInstance() {
        if (scheduler == null) {
            synchronized (MyScheduler.class) {
                if (scheduler == null) {
                    try {
                        scheduler = StdSchedulerFactory.getDefaultScheduler();
                        scheduler.start();
                    } catch (SchedulerException e) {
                        throw new RuntimeException("Failed to start the scheduler", e);
                    }
                }
            }
        }
        return scheduler;
    }
}

