package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
/* 플러시
 * 영속성 컨텍스트의 변경사항과 데이터베이스를 맞추는 작업
 * */
public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			// 영속
			
			Member member = new Member(200L, "member200");
			em.persist(member); // 영속성 컨텍스트에 담긴다.
			
			em.flush(); // 데이터베이스에 쿼리가 이 시점에 바로 나간다. (강제호출)
			
			System.out.println("=============================");
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

}
