/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entities.Jokes;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static rest.MembersResourceTest.startServer;
import utils.EMF_Creator;

/**
 *
 * @author Dennis
 */
public class JokesResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    public JokesResourceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.CREATE);

        httpServer = startServer();

        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;

        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void tearDownClass() {
        httpServer.shutdownNow();

    }

    @BeforeEach
    public void setUp() {

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Jokes.deleteAllRows").executeUpdate();
            em.createNamedQuery("Jokes.deleteAllRows").executeUpdate();
            em.persist(new Jokes(1L, "Blind Guy", "A blind guy walked into a bar, and a chair, and a table....", "Mean", "Blind jokes"));
            em.persist(new Jokes(2L, "Uncle Joke ", "My boss told me to have a good day... so i went home", "Weird", "Boss jokes"));
            em.persist(new Jokes(3L, "Lame Peter Pan", "Why is Peter Pan always flying? He neverlands", "Peter Pan", "Peter Pan jokes"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/jokes").then().statusCode(200);
    }

    @Test
    public void testDummyMsg() throws Exception {
        given()
                .contentType("application/json")
                .get("/jokes/").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("msg", equalTo("Hello World"));
    }

    @Test
    public void getAllJokesTest() {
        given().when().get("/jokes/all").then().statusCode(200);

    }

    @Test
    public void getRandonJokeTest() {
        given().when().get("/jokes/random").then().statusCode(200);

    }

//    @Test
//    public void populateTest() {
//        given().when().get("/jokes/populate").then().statusCode(200);
//
//    }

//    @Test
//    public void getJokeById(){
//        given()
//                .contentType("application/json")
//                .get("/jokes/").then()
//                .assertThat()
//                .statusCode(HttpStatus.OK_200.getStatusCode())
//                .body("msg", equalTo("Hello World"));
//        
//    }
}
