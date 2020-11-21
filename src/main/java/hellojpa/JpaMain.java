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
            // JPAL은 객체지향 쿼리 : Member 객체를 대상으로 쿼리를 짠다. (대상이 테이블이 아닌 객체이다.)
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();
          
            for(Member member : result) {
                System.out.println("member.name = "+ member.getName());
            }
            
            tx.commit();
        } catch (Exception e) {
            tx.rollback(); 
        } finally {
            em.close();
        }
        emf.close();
    }

}
