package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
/* 객체를 테이블에 맞추어 모델링 - 외래키 식별자를 직접 다룸*/
public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			// 팀 생성
			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);
			
			// 멤버생성
			Member member = new Member();
			member.setUsername("member1");
			
			// 멤버를 팀에 소속시킨다.
			member.setTeamId(team.getId()); // 이게 좀 객체지향스럽지 않다. 
			em.persist(member);
		
			Member findMember = em.find(Member.class, member.getId());
			
			//찾아온 멤버의 소속을 알고싶을 때 - 연관관계가 없기 때문에 JPA에게 계속 물어봐야 한다.
			Long findTeamId = findMember.getTeamId();
			Team findTeam = em.find(Team.class, findTeamId);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

}
