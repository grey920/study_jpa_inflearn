package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);

			Member member1 = new Member();
			member1.setUsername("member1");
			member1.setTeam(team);
			em.persist(member1);

		
			em.flush();
			em.clear();

			Member m = em.find(Member.class, member1.getId()); // Member만 먼저 조회
			
			System.out.println("m = " + m.getTeam().getClass()); // class hellojpa.Team$HibernateProxy$1ATEZBtf <- 프록시 객체가 리턴
			
			System.out.println("============");
			m.getTeam().getName(); //실제 Team의 어떤 속성을 사용하면 그때  프록시 객체가 초기화되면서 쿼리가 나가고 값이 셋팅된다.
			System.out.println("============");
			
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
