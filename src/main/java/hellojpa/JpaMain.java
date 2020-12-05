package hellojpa;

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

			Member member1 = new Member();
			member1.setUsername("member1");
			em.persist(member1);

		
			em.flush();
			em.clear();

			Member refMember = em.getReference(Member.class, member1.getId());
			System.out.println("refMember = "+ refMember.getClass()); // Proxy
			
			//em.detach(refMember); // 영속성컨텍스트에서 관리 안함
			//em.close();
			em.clear(); // 끊긴건 아니지만 영속성 컨텍스트가 깨끗해짐
			
			refMember.getUsername(); // 초기화 - 쿼리나가야 함
			/* could not initialize proxy [hellojpa.Member#1] - no Session 
			 * org.hibernate.LazyInitializationException:
			 * 보통 트랜잭션 끝나고 나서 프록시를 조회할 때 이런 에러를 많이 만난다.
			 */
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
	}


}
