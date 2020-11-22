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
            // 비영속 - JPA와 관련없는 상태. DB에도 안들어감
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");
            
            // 영속 상태 -엔티티 매니저 안에 있는 영속성 컨텍스트에 의해 member가 관리됨. DB에 저장x
            System.out.println("=== BRFORE ===");
            em.persist(member);
            em.detach(member); // 영속성 컨텍스트에서 다시 지운다.(관계가 없어짐)
            System.out.println("=== AFTER ===");
            
            tx.commit(); // 커밋하는 시점에 DB에 쿼리가 날라간다. 
        } catch (Exception e) {
            tx.rollback(); 
        } finally {
            em.close();
        }
        emf.close();
    }

}
