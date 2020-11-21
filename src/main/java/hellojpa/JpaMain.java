package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        // 애플리케이션 로딩 시점에 딱 하나만 만들어야 한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 실제 트랜잭션 단위가 일어날 때마다 EntityManager가 만들어져야 한다. (데이터베이스 커넥션 받았다고 생각하자)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻기
        tx.begin(); // 데이터베이스 트랜잭션 시작

        /* 정석코드 : 문제가 발생해도 close할 수 있도록 try-catch 사용하기 */
        try {
            // Member를 저장하기
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            // 로직 만들어짐

            em.persist(member); // JPA에 저장

            tx.commit(); // 정상적일때 커밋
        } catch (Exception e) {
            tx.rollback(); // 문제가 생기면 롤백을 한다.
        } finally {
            // 작업이 다 끝나면 꼭 닫아주기
            em.close();
        }
        emf.close();
    }

}
