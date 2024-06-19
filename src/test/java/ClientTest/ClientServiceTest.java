///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package ClientTest;
//
//import java.io.IOException;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.junit.Test;
//
///**
// *
// * @author admin
// */
//public class ClientServiceTest {
//    
//    
//    @Test 
//    public void Sample () throws IOException{
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("https://yahoo-finance127.p.rapidapi.com/search/aa")
//                .get()
//                .addHeader("x-rapidapi-key", "d75e476636msha08ea07fdb84f37p11a6ccjsnf6345c38ebe8")
//                .addHeader("x-rapidapi-host", "yahoo-finance127.p.rapidapi.com")
//                .build();
//
//        Response response = client.newCall(request).execute();
//        
//        System.out.println("The response: " + response);
//    
//    }
//    
//    
//    @Test
//    public void jSoupTest() throws IOException{
//        Document doc = Jsoup.connect("/Users/admin/Downloads/04470235_aa_2024-03-28 (1).xhtml").get();
//
//        // Extract employee headcount for 2023
//        Element headcount2023 = doc.select("div.crn fn1[ix\\:nonFraction[unitRef=Pure][contextRef=C]").first();
//        String employees2023 = headcount2023.text();
//
//        // Extract employee headcount for 2022
//        Element headcount2022 = doc.select("div.crn fn1[ix\\:nonFraction[unitRef=Pure][contextRef=F]").first();
//        String employees2022 = headcount2022.text();
//
//        System.out.println("2023 Employee Count: " + employees2023);
//        System.out.println("2022 Employee Count: " + employees2022);
//
//    
//    }
//    
//}
