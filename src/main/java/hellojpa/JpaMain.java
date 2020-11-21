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
            /* 회원 수정 */
            Member findMember = em.find(Member.class, 1L); // 대상 엔티티 클래스, PK
            findMember.setName("HelloJPA");
            
            // 수정할 때 em.persist(findMember); 안해도 된다 -> 마치 자바 컬렉션처럼 설계되었기 때문에! setter로 끝남
            
            /* JPA를 통해 엔티티를 가져오면 JPA가 그걸 관리하기 시작한다 (findMember)
             * 변경이 생겼다 판단되면 트랜잭션 커밋하기 직전에 update 쿼리를 만들어서 날리고 트랜잭션이 커밋된다. 
             * */
          
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
