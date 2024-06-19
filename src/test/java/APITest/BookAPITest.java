/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APITest;

/**
 *
 * @author admin
 */
import com.netnimblelabs.jerseysys.api.BookResource;
import com.netnimblelabs.jerseysys.models.Book;
import com.netnimblelabs.jerseysys.util.HibernateUtil;
import jakarta.ws.rs.client.Entity;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;

public class BookAPITest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(BookResource.class)
            .packages("org.glassfish.jersey.media.json");
    }

//    @Before
//    public void setUp() throws Exception {
//        super.setUp();
//        // Initialize database and insert test data if necessary
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        super.tearDown();
//        // Clean up database after tests
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.createQuery("delete from Book").executeUpdate();
//        transaction.commit();
//        session.close();
//    }
    
//    @Test
//    public void testAddBook() {
//        
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Book book = new Book();
//        
//        
//        book.setId(1L);
//        book.setTitle("48 Laws of Power");
//        book.setAuthor("Robert Green");
//        
//        Transaction transaction = session.beginTransaction();
////        session.save(book);
//
//        Long userId = 1L;
//        Book returnedBook = (Book) session.get(Book.class, userId);
//        System.out.println("Testing things " + returnedBook);
//        session.close();
//        
//        
//    }
    
//    @Test
//    public void testAddBook() {
//        Book book = new Book();
//        book.setTitle("New Book");
//        book.setAuthor("New Author");
//
//        Response response = target("/books").request(MediaType.APPLICATION_JSON)
//                .post(Entity.entity(book, MediaType.APPLICATION_JSON));
//
//        assertEquals("Should return status 201", 201, response.getStatus());
//
//        Book returnedBook = response.readEntity(Book.class);
////        assertNotNull("Should return created book", returnedBook);
////        assertNotNull("Book ID should not be null", returnedBook.getId());
////        assertEquals("Book title should match", "New Book", returnedBook.getTitle());
////        assertEquals("Book author should match", "New Author", returnedBook.getAuthor());
//    }

//    @Test
//    public void testGetBooks() {
//        Response response = target("/books").request().get();
//
//        assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
//        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
//
//        String content = response.readEntity(String.class);
//        assertNotNull("Content of response should not be null", content);
//        // Additional assertions can be added here to verify the content
//    }

//    @Before
//    public void setUp() throws Exception {
//        super.setUp();
//        // Initialize database and insert test data
//        Book book1 = new Book();
//        book1.setTitle("Test Book 1");
//        book1.setAuthor("Author 1");
//
//        Book book2 = new Book();
//        book2.setTitle("Test Book 2");
//        book2.setAuthor("Author 2");
//
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(book1);
//        session.save(book2);
//        transaction.commit();
//        session.close();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        super.tearDown();
//        // Clean up database after tests
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.createQuery("delete from Book").executeUpdate();
//        transaction.commit();
//        session.close();
//    }
//    @Test
//    public void testGetBooks() {
//        Response response = target("/books").request(MediaType.APPLICATION_JSON).get();
//        assertEquals("Should return status 200", 200, response.getStatus());
//
//        List<Book> books = response.readEntity(new GenericType<List<Book>>() {});
//        assertNotNull("Should return list of books", books);
//        assertEquals("Should return two books", 2, books.size());
//    }
//    @Test
//    public void testAddBook() {
//        Book book = new Book();
//        book.setTitle("New Book");
//        book.setAuthor("New Author");
//
//        Response response = target("/books").request(MediaType.APPLICATION_JSON)
//                .post(Entity.entity(book, MediaType.APPLICATION_JSON));
//
//        assertEquals("Should return status 200", 200, response.getStatus());
//
//        Book returnedBook = response.readEntity(Book.class);
//        assertNotNull("Should return created book", returnedBook);
//        assertNotNull("Book ID should not be null", returnedBook.getId());
//        assertEquals("Book title should match", "New Book", returnedBook.getTitle());
//        assertEquals("Book author should match", "New Author", returnedBook.getAuthor());
//    }
}
