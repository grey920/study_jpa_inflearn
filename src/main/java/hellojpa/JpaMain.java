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
			
			// 팀 생성
			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);
			
			// 멤버 생성
			Member member = new Member();
			member.setUsername("member1");
			// 연관관계 값 셋팅 - 연관관계 펀의 메소드 1. Member쪽에서 하기
			//member.changeTeam(team); // 연관관계의 주인에 값 셋팅  // **
			em.persist(member); // 외래 키 찾아서 jpa가 insert
			
			// 연관관계 값 셋팅 - 연관관계 펀의 메소드 2. Team쪽에서 하기 
			// 연관관계 주인은 Member에 있는 team이고 값을 셋팅하는 건 내 맘대로!
			// Member에서 team을 넣든, Team에서 member를 넣든 한쪽에만 넣으면 된다
			team.addMember(member);
			
//			team.getMembers().add(member); // 읽기 전용이라 jpa에서 이 값을 쓰지 않는다. - 연관관계 편의 메소드로 Member클래스의 setTeam 안에 넣기 //**

			/* DB로 나가는 쿼리를 눈으로 확인하고 싶다면 - 싱크 맞추고 초기화*/
//			em.flush();
//			em.clear();
		
			Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
			List<Member> members = findTeam.getMembers(); // 팀 컬렉션에 아무것도 없는 상태   
			
			System.out.println("=======================================");
			for (Member m : members) {
				System.out.println("members = " +findTeam);
			}
			System.out.println("=======================================");
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

}
