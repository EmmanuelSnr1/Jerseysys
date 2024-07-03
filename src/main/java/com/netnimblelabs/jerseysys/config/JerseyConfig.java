/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.jerseysys.config;

import com.netnimblelabs.jerseysys.api.JobManagerAPI;
import org.glassfish.jersey.server.ResourceConfig;
/**
 * Jersey configuration class
 */


public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(JobManagerAPI.class);
        packages("com.netnimblelabs.jerseysys.api");
    }
}

