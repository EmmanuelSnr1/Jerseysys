package Jobs;

import com.netnimblelabs.jerseysys.jobs.JobManager;
import com.netnimblelabs.jerseysys.util.CsvUtils;
import com.netnimblelabs.jerseysys.util.HibernateUtil;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author admin
 */
public class JobsTest {
    
    @Ignore
    @Test
    public void JobTest() throws Exception {
        String filePath = "/Users/admin/NetBeansProjects/jerseysys/src/main/java/com/netnimblelabs/jerseysys/util/Nums.csv";
        InputStream inputStream = new FileInputStream(filePath);
        List<String> orgNumbers = CsvUtils.readCsv(inputStream); // Utility to read CSV file

//        System.out.println(orgNumbers);
        int collectionId = JobManager.initializeJob(orgNumbers);

        try {
            Thread.sleep(4000); // Sleep for 10 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

//    @Test
//    public void main() {
//        JobManager jobManager = new JobManager();
//
//        // Schedule the job
//        jobManager.doFetchCompanyProfileInfoJob();
//
//        // Keep the application running to allow job execution
//        try {
//            Thread.sleep(300000); // Run for 5 minutes for testing
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Shutdown Hibernate session factory
//        HibernateUtil.shutdown();
//    }
