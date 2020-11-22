package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); 

        try {
        	
            // 조회 쿼리가 한 번만 나간다
            Member findMember1 = em.find(Member.class, 101L); // 조회쿼리 o
            Member findMember2 = em.find(Member.class, 101L); // 조회쿼리 x
            
            tx.commit(); 
        } catch (Exception e) {
            tx.rollback(); 
        } finally {
            em.close();
        }
        emf.close();
    }

}
