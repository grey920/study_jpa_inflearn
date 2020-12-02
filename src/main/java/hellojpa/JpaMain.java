package hellojpa;

import java.time.LocalDateTime;

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
			
			/* 나중에 event 기능으로 자동화할 수 있다.(로그인 세션 정보 읽어와서 넣어주기) */
			Member member = new Member();
			member.setUsername("user1");
			member.setCreatedBy("jung");
			member.setCreatedDate(LocalDateTime.now());
			
			em.persist(member);
			
			em.flush();
			em.clear();
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

}
