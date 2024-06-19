///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.netnimblelabs.jerseysys.jobs;
//
///**
// *
// * @author admin
// */
//import com.netnimblelabs.jerseysys.models.CompanyRequest;
//import com.netnimblelabs.jerseysys.util.HibernateUtil;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.Query;
//import org.apache.logging.log4j.*;
//
//import java.util.Date;
//import java.util.List;
//
//
//public class FetchCompanyDataJob implements Job {
//
//    private static final Logger logger = LogManager.getLogger(FetchCompanyDataJob.class);
//    private MockCHServiceImplementation companyHouseService;
//
//    public FetchCompanyDataJob() {
//        this.companyHouseService = new MockCHServiceImplementation();
//    }
//
//    @Override
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//        logInfo("************** STARTING COMPANY HOUSE JOB **************");
//
//        List<CompanyRequest> companyRequests = getPendingCompanyRequests(100);
//        for (CompanyRequest request : companyRequests) {
//            try {
//                companyHouseService.getCompanyProfileInfo(request.getOrgNumber());
//                updateCompanyRequestStatus(request, "Completed", null);
//            } catch (Exception e) {
//                updateCompanyRequestStatus(request, "Failed", e.getMessage());
//                logError("Failed to fetch data for org number: " + request.getOrgNumber(), e);
//            }
//        }
//        logInfo("************** FETCH JOB COMPLETE **************");
//    }
//
//    private List<CompanyRequest> getPendingCompanyRequests(int limit) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = null;
//        List<CompanyRequest> companyRequests = null;
//        try {
//            transaction = session.beginTransaction();
//            Query<CompanyRequest> query = session.createQuery("FROM CompanyRequest WHERE status = :status", CompanyRequest.class);
//            query.setParameter("status", "Pending");
//            query.setMaxResults(limit);
//            companyRequests = query.list();
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            logError("Error fetching pending company requests", e);
//        } finally {
//            session.close();
//        }
//        return companyRequests;
//    }
//
//    private void updateCompanyRequestStatus(CompanyRequest request, String status, String errorMessage) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = null;
//        try {
//            transaction = session.beginTransaction();
//            request.setStatus(status);
//            request.setErrorMessage(errorMessage);
//            request.setLastAttemptAt(new Date());
//            session.update(request);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            logError("Error updating company request status", e);
//        } finally {
//            session.close();
//        }
//    }
//
//    private void logInfo(String message) {
//        System.out.println(" -- " + message);
////        log.debug(" -- " + message);
//    }
//
//    private void logError(String message, Exception e) {
//        System.err.println(" -- " + message);
////        log.error(" -- " + message, e);
//    }
//}
