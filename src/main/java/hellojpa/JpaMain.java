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
        	// 준영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");
            
            // 영속
            System.out.println("=== BRFORE ===");
            em.persist(member);
            System.out.println("=== AFTER ===");
            
            // 조회하기 - 조회용 sql이 나가는지가 중요하다 -> 안나감!! 왜?? 
            // -> em.persist(member)에서 1차 캐시에 저장되고, 똑같은 PK로 조회하기 때문에 1차 캐시에서 바로 조회한 것!
            Member findMember = em.find(Member.class, 101L);
            
            System.out.println("findMember = " + findMember.getId());
            System.out.println("findMember = " + findMember.getName());
            tx.commit(); 
        } catch (Exception e) {
            tx.rollback(); 
        } finally {
            em.close();
        }
        emf.close();
    }

}
