/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.jerseysys.util;

/**
 *
 * @author admin
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    public static List<String> readCsv(InputStream inputStream) {
        List<String> orgNumbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                orgNumbers.add(line.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orgNumbers;
    }
}

