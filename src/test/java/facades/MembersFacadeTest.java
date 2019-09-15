/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;


import entities.Members;
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
public class MembersFacadeTest {
    
    private static EntityManagerFactory emf;
    private static MembersFacade facade;
    
    public MembersFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = MembersFacade.getFacadeExample(emf);
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Members.deleteAllRows").executeUpdate();
            em.persist(new Members(1L, "Jan", "cph-j", "green"));
            em.persist(new Members(2L, "Hanne", "cph-h", "yellow"));
            em.persist(new Members(3L, "Knud", "cph-k", "red"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void getAllMembersTest(){
        assertEquals(3, facade.getAllMembers().size(), "Expects 3 rows in table");
    }
    
    @Test
    public void addMemberTest(){
        int sizeBefore = facade.getAllMembers().size();
        EntityManager em = emf.createEntityManager();
        
       
        facade.addMember(new Members(4L, "Didrik", "cph-d", "green"));
       
        
        int sizeAfter = facade.getAllMembers().size();
        
        assertEquals(sizeBefore +1, sizeAfter);
    }
}
