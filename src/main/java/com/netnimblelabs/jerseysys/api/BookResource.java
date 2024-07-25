package com.netnimblelabs.jerseysys.api;

import com.netnimblelabs.jerseysys.models.Book;
import com.netnimblelabs.jerseysys.util.HibernateUtil;
import com.netnimblelabs.jerseysys.util.SessionUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/books")
public class BookResource {

    @GET
    @Path("/hi")
    public String getHiGreeting() {
        return "hi";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        //using the session.get version is used when you would want to carry out operation on a single item. 
        //takes a class and an serializable 
        
//        List<Book> books = new ArrayList<>();
//
//    try {
//        books = SessionUtil.executeStatelessTransaction(session -> {
//            // Assuming you have a method to retrieve all book IDs first
//            List<Integer> bookIds = session.createQuery("select id from Book").list();
//            
//            // Fetch each book by its ID
//            for (Integer id : bookIds) {
//                Book book = session.get(Book.class, id);
//                books.add(book);
//            }
//            return books;
//        });
//    } catch (Exception ex) {
//        Logger.getLogger(BookResource.class.getName()).log(Level.SEVERE, null, ex);
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving books").build();
//    }
        
        List<Book> books;

        try {
            books = SessionUtil.executeStatelessTransaction(session -> {
                return session.createQuery("from Book").list();
            });
        } catch (Exception ex) {
            Logger.getLogger(BookResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving books").build();
        }

        return Response.ok(books).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {

        try {
            SessionUtil.executeStatelessTransaction(session -> {
                session.insert(book);
                return null;
            });
        } catch (Exception ex) {
            Logger.getLogger(BookResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.status(Response.Status.CREATED).entity(book).build();
    }
}
