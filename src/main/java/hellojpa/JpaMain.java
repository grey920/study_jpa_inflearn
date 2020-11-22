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
			/* 버퍼링 모아서 write 하기 */

			// 영속
			Member member1 = new Member(150L, "A");
			Member member2 = new Member(160L, "B");

			// 영속성 컨텍스트에 쿼리가 차곡차곡 쌓인다
			em.persist(member1);
			em.persist(member2);

			System.out.println("=============================");
			// 커밋 시점에 쿼리가 데이터베이스에 한번에 나간다. 왜 이렇게 하는거지?? -> [버퍼링]!!
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

}
