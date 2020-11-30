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
			Member member = new Member();
			member.setUsername("member1");
			
			em.persist(member);
			
			Team team = new Team();
			team.setName("teamA");
			// 애매ㅐ~~ Team 테이블이 아니라 Member테이블에 있는 외래키를 업데이트 시켜야 해..
			// 업데이트 쿼리가 한 번 더 나간다.
			team.getMembers().add(member);
			
			em.persist(team);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

}
