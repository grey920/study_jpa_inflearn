package hellojpa;

import java.util.List;

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
			
			Team teamB = new Team();
			teamB.setName("teamB");
			em.persist(teamB);

			Member member1 = new Member();
			member1.setUsername("member1");
			member1.setTeam(team);
			em.persist(member1);
			
			Member member2 = new Member();
			member2.setUsername("member2");
			member2.setTeam(teamB);
			em.persist(member2);

		
			em.flush();
			em.clear();

//			Member m = em.find(Member.class, member1.getId()); 
			
			List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList(); // 값이 다 채워져서 조인으로 나온다
		
			// SQL : select * from Member
			// SQL : select * from Team where TEAM_ID = xxx
			
			/*LAZY 로딩으로 바꾸면 Team에 대한 쿼리는 없이 select m from Member m 쿼리 하나만 나간다.
			 * => Team을 안쓰니까! Team은 프록시로 박혀있으니까 안나온다.*/
			
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
