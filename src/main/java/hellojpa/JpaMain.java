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
        	
            // 영속
            Member findMember1 = em.find(Member.class, 101L); // 조회쿼리 o
            Member findMember2 = em.find(Member.class, 101L); // 조회쿼리 x
            
            // JPA가 영속 엔티티의 동일성을 보장한다.(==비교 보장) 마치 자바 컬렉션에서 똑같은 레퍼런스를 가진 객체를 꺼내면 ==비교값이 true가 나오는 것과 같다.
            // 1차 캐시가 있기 때문에 가능함. (1차 캐시로 반복 가능한 읽기 등급의 트랜잭션 격리 수준을 데이터베이스가 아닌 내플리케이션 차원에서 제공)
            System.out.println("result = " + (findMember1 == findMember2));// result = true
            
            tx.commit(); 
        } catch (Exception e) {
            tx.rollback(); 
        } finally {
            em.close();
        }
        emf.close();
    }

}
