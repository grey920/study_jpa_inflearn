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
			Member member = em.find(Member.class, 150L);
			member.setName("AAAAA"); // dirty checking -> update
			
			//em.detach(member); // 이제 JPA에서 관리하지 않음 (트랜잭션을 커밋할 때 아무일도 일어나지 않는다. setName했어도 업데이트x)
			em.clear(); // 영속성 컨텍스트 안을 통채로 날리는 것
			
			Member member2 = em.find(Member.class, 150L); // 1차 캐시에 다시 올리기 때문에 쿼리가 또 나간다 (test case 작성하고 싶을떄, 눈으로 보고 싶을 떄)
			
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
