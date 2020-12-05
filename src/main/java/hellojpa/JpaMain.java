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
			
			Member member = em.find(Member.class, 1L);
			
			printMember(member);
			
//			printMemberAndTeam(member);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

	/* 유저 이름만 출력하는 경우 - 연관관계가 걸려있다는 이유로 사용하지도 않는 Team 정보를 끌로오면 손해! -> '프록시'가 해결*/
	private static void printMember(Member member) {
		System.out.println("member = " + member.getUsername());
	}

	/* Member와 Team 정보를 같이 출력하는 메소드 */
	private static void printMemberAndTeam(Member member) {
		String username = member.getUsername();
		System.out.println("username = " + username);
		
		Team team = member.getTeam();
		System.out.println("team = " + team.getName());
	}

}
