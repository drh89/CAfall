package facades;

import entities.Cars;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CarsFacade {

    private static CarsFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CarsFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CarsFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarsFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
    
    public List<Cars> getCars() {
        EntityManager em = getEntityManager();
        TypedQuery tq = em.createNamedQuery("cars.getAll", Cars.class);
        List<Cars> cars = tq.getResultList();
        return cars;
    }
    
    public List<Cars> getCarsByMake(String name){
        EntityManager em = getEntityManager();
        TypedQuery tq = em.createNamedQuery("cars.make", Cars.class);
        tq.setParameter("name",name);
        List<Cars> cars = (List) tq.getResultList();
        return cars;
    }

    public void populate(Cars car) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
        em.close();

        

    }

}
