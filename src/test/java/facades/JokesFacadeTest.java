/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Jokes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

/**
 *
 * @author Dennis
 */
public class JokesFacadeTest {

    private static EntityManagerFactory emf;
    private static JokesFacade facade;

    public JokesFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = JokesFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
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
    public void getAllJokesTest() {
        assertEquals(3, facade.getAllJokes().size());
    }

    @Test
    public void addJokeTest() {
        int sizeBefore = facade.getAllJokes().size();

        facade.addJoke(new Jokes(4L, "bla bla", "test", "test", "test"));

        int sizeAfter = facade.getAllJokes().size();

        assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test
    public void getRandomJokeTest() {
        Jokes joke = facade.getRandomJoke();
        List<Jokes> jokes = facade.getAllJokes();
        jokes.forEach((joke1) -> {
            if (joke1.equals(joke)) {
                assertTrue(joke1.equals(joke1));
            }
        });

    }
//    @Test
//    public void getJokeById(){
//        
//        
//        Jokes joke = facade.getJokeById(7);
//        
//        assertEquals("Blind Guy", joke.getTitle());
//        assertEquals("Blind jokes", joke.getType());
//    }
}
