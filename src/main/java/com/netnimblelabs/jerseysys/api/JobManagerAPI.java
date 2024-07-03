/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.jerseysys.api;

/**
 *
 * @author admin
 */

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.InputStream;
import java.util.List;
import com.netnimblelabs.jerseysys.jobs.JobManager;
import com.netnimblelabs.jerseysys.util.CsvUtils;
import java.io.FileInputStream;

@Path("/jobs")
public class JobManagerAPI {

    @POST
    @Path("/addFetchHeadcountJob")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFetchHeadcountJob(String filePath) {
        try {
        filePath = "/Users/admin/NetBeansProjects/jerseysys/src/main/java/com/netnimblelabs/jerseysys/util/Nums.csv";
        InputStream inputStream = new FileInputStream(filePath);
        List<String> orgNumbers = CsvUtils.readCsv(inputStream); // Utility to read CSV file
            int collectionId = JobManager.initializeJob(orgNumbers);
            return Response.ok("Fetch Headcount Job added successfully with CollectionID: " + collectionId).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to add job").build();
        }
    }

    @GET
    @Path("/checkAllJobs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkAllJobs() {
        try {
            JobManager.checkAllJobs();
            return Response.ok("Checked all jobs successfully").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to check all jobs").build();
        }
    }

    @GET
    @Path("/checkJobState")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkJobState(@QueryParam("jobName") String jobName, @QueryParam("jobGroup") String jobGroup) {
        try {
            JobManager.checkJobState(jobName, jobGroup);
            return Response.ok("Checked job state successfully").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to check job state").build();
        }
    }

    @GET
    @Path("/checkJobDetails")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkJobDetails(@QueryParam("jobName") String jobName, @QueryParam("jobGroup") String jobGroup) {
        try {
            JobManager.checkJobDetails(jobName, jobGroup);
            return Response.ok("Checked job details successfully").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to check job details").build();
        }
    }

    @GET
    @Path("/checkRunningJobs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkRunningJobs() {
        try {
            JobManager.checkRunningJobs();
            return Response.ok("Checked running jobs successfully").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to check running jobs").build();
        }
    }
}

