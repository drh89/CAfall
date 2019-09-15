/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Jokes;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dennis
 */
public class JokesFacade {
    
    private static JokesFacade instance;
    private static EntityManagerFactory emf;
    
    //private to ensure singleton
    private JokesFacade(){}
    
    public static JokesFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokesFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<Jokes> getAllJokes(){
        EntityManager em = getEntityManager();
        TypedQuery tq = em.createNamedQuery("Jokes.getAll", Jokes.class);
        List<Jokes> jokes = (List) tq.getResultList();
        return jokes;
        
    }
    
    public Jokes getJokeById(int id){
        EntityManager em = getEntityManager();
        TypedQuery tq = em.createNamedQuery("Jokes.id", Jokes.class);
        tq.setParameter("id", id);
        Jokes joke = (Jokes) tq.getSingleResult();
        return joke;
    }
    
    public Jokes getRandomJoke(){
        List<Jokes> jokes = getAllJokes();
        Random rand = new Random();
        Jokes joke = jokes.get(rand.nextInt(jokes.size()));
        return joke;
    }
    
    public void addJoke(Jokes joke){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(joke);
        em.getTransaction().commit();
        em.close();
    }
    
    
    
    
}
