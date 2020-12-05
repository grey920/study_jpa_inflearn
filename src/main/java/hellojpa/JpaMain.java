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
			member.setUsername("hello");
			
			em.persist(member);
			
			em.flush();
			em.clear();
			// 영속성 컨텍스트가 깔끔하게 초기화됨
			
//			Member findMember = em.find(Member.class, member.getId());
			Member findMember = em.getReference(Member.class, member.getId());  
			System.out.println("findmember.username = " + findMember.getUsername()); //내부적으로 영속성 컨텍스트에 요청해 (초기화 요청) 실제 값을 가져오게 됨
			System.out.println("findmember.username = " + findMember.getUsername()); // 두번째부터는 초기화를 통해 target에 값이 있으므로 바로 값을 출력
//			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

	

}
