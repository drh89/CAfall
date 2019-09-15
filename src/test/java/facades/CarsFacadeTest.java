package facades;

import utils.EMF_Creator;
import entities.Cars;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class CarsFacadeTest {

    private static EntityManagerFactory emf;
    private static CarsFacade facade;

    public CarsFacadeTest() {
    }

    //@BeforeAll
//    public static void setUpClass() {
//        emf = EMF_Creator.createEntityManagerFactory(
//                "pu",
//                "jdbc:mysql://localhost:3307/CAfall_test",
//                "dev",
//                "ax2",
//                EMF_Creator.Strategy.CREATE);
//        facade = CarsFacade.getFacadeExample(emf);
//    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = CarsFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Cars.deleteAllRows").executeUpdate();
            em.persist(new Cars(1L, "Mercedes-benz", "E350", "10/04-2016", 2017L, 268L, 16000L, 5L, 719000L));
            em.persist(new Cars(2L, "VW", "Golf", "03/07-2010", 2010L, 115L, 206000L, 5L, 130000L));
            em.persist(new Cars(3L, "VW", "Golf", "03/07-2013", 2013L, 125L, 226222L, 5L, 132020L));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

     
    @Test
    public void getAllCarsTest() {
        assertEquals(3, facade.getAllCars().size(), "Expects two rows in the database");
    }
    
    
    @Test
    public void addCarTest(){
        int sizeBefore = facade.getAllCars().size();
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Cars(4L, "BMW", "E300", "02/08-2018", 2018L, 114L, 0L, 2L, 200000L));
        em.getTransaction().commit();
        em.close();
        
        int sizeAfter = facade.getAllCars().size();
        
        assertEquals(sizeBefore + 1, sizeAfter);
        
    }
    @Test
    public void getCarsByMake(){
        assertEquals(2, facade.getCarsByMake("VW").size());
        assertEquals(1, facade.getCarsByMake("Mercedes-benz").size());
        
        
    }
        

}
