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

			Member reference = em.getReference(Member.class, member1.getId());
			Member m1 = em.find(Member.class, member1.getId());
			System.out.println("m1 = " + m1.getClass()); //m1 = class hellojpa.Member 즉, 프록시가 아닌 진짜 객체가 출력
			
			System.out.println("reference = " + reference.getClass()); // reference도 프록시가 아닌 Member이다.
																	// 1. 영속성컨텍스트에 이미 있으니까 (성능상)
																	// 2. 한 트랜잭션 안에서 같은 값을 보장한다. (==비교를 true로 만들기 위해)
			System.out.println("a == a: " + (m1 == reference)); // 한 영속성 컨텍스트에서 같은 pk값으로 가져왔다면 항상 true를 반환한다
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}


}
