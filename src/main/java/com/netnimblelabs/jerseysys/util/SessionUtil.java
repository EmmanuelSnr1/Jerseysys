/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.jerseysys.util;

import org.hibernate.StatelessSession;

/**
 *
 * @author admin
 */

public class SessionUtil {

    @FunctionalInterface
    public interface SessionOperation<T> {

        T operate(StatelessSession session) throws Exception;
    }

//    public static <T> T executeStatelessTransaction(SessionOperation<T> operation) throws Exception {
//        try (AutoCloseableStatelessSession autoCloseableSession = new AutoCloseableStatelessSession(HibernateUtil.getSessionFactory().openStatelessSession())) {
//
//            StatelessSession session = autoCloseableSession.getSession();
//            session.beginTransaction();
//
//            try {
//                T result = operation.operate(session);
//                session.getTransaction().commit();
//                return result;
//            } catch (Exception e) {
//                session.getTransaction().rollback();
//                throw e; // rethrow the exception
//            }
//        } // autoCloseableSession will be automatically closed here
//    }
//}
}
