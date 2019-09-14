/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import entities.Members;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dennis
 */
public class MembersFacade {
    
     private static MembersFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MembersFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MembersFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MembersFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<Members> getMembers(){
        EntityManager em = getEntityManager();
        TypedQuery tq = em.createNamedQuery("Members.getAll", Members.class);
        List<Members> members = (List) tq.getResultList();
        return members;
    }
    
    public void addMember(Members member){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(member);
        em.getTransaction().commit();
        em.close();
    }
}
