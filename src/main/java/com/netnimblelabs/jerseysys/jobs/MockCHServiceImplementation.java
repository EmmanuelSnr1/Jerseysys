/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.jerseysys.jobs;

/**
 *
 * @author admin
 */
import java.util.Random;

public class MockCHServiceImplementation {
    private Random random = new Random();

    public void getCompanyProfileInfo(String orgNumber) throws Exception {
        // Simulate network delay
        Thread.sleep(1000);

        // Simulate success or failure
        if (random.nextBoolean()) {
            System.out.println("Fetched profile for org number: " + orgNumber);
        } else {
            throw new Exception("Failed to fetch profile for org number: " + orgNumber);
        }
    }
}

