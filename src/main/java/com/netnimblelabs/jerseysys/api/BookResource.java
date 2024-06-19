package com.netnimblelabs.jerseysys.api;

import com.netnimblelabs.jerseysys.models.Book;
import com.netnimblelabs.jerseysys.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/books")
public class BookResource {

    @GET
    @Path("/hi")
    public String getHiGreeting() {
        return "hi";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(book);

        transaction.commit();
        session.close();

        return Response.status(Response.Status.CREATED).entity(book).build();
    }
}
