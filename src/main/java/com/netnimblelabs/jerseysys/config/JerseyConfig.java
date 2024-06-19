/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.jerseysys.config;

/**
 *
 * @author admin
 */
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api") // Set the base path for your JAX-RS resources
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("com.netnimblelabs.jerseysys.api");
    }
}
