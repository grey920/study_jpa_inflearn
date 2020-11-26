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
			// 저장
			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);
			
			Member member = new Member();
			member.setUsername("member1");
			// 멤버를 팀에 소속시킨다.
			member.setTeam(team); //team을 바로 넣으면 jpa가 알아서 PK값을 꺼내서 외래키로 사용함
			em.persist(member);
			
			/* DB로 나가는 쿼리를 눈으로 확인하고 싶다면 - 싱크 맞추고 초기화*/
			em.flush();
			em.clear();
		
			// 조회
			Member findMember = em.find(Member.class, member.getId());
			
			//찾아온 멤버의 소속을 알고싶을 때 - 팀을 바로 끄집어내서 쓸 수 있다.
			Team findTeam = findMember.getTeam();
			System.out.println("findTeam = " + findTeam.getName());
			
			// 수정
			// 100번 팀이 있다는 가정하에 가져와서 -> member의 팀을 setTeam으로 바꾸면
			// DB의 외래키값이 업데이트 된다.
			Team newTeam = em.find(Team.class, 100L);
			findMember.setTeam(newTeam);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

}
