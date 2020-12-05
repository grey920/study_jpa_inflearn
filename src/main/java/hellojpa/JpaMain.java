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

			/*애플리케이션 개발시 대부분 Member를 쓸때 Team을 같이 사용한다면 즉시로딩*/
			Member m = em.find(Member.class, member1.getId()); // 이 시점에서 Member와 Team을 조인해서 한 방에 조회
			
			System.out.println("m = " + m.getTeam().getClass()); // class hellojpa.Team <- 프록시가 아닌 진짜 객체 (즉시로딩이기 때문에 프록시가 필요없다.)
			
			System.out.println("============");
			m.getTeam().getName(); // 프록시가 아니라 진짜 객체가 나온거기 때문에 초기화가 여기 필요 없다. (쿼리X)
			System.out.println("teamName = " + m.getTeam().getName()); // 바로 teamA가 출력됨
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
