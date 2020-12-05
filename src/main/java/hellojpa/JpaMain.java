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
			Member findMember = em.getReference(Member.class, member.getId());  //getReference 호출 시점에는 select쿼리가 안나감. 그런데 값이 실제 사용되는 시점에서 나감
			System.out.println("findMember = "+ findMember.getClass());
			System.out.println("findmember.id = " + findMember.getId()); // 쿼리가 안나감.-> reference를 찾을때 이미 id값을 넣어줬기 때문에 필요없다.
			System.out.println("findmember.username = " + findMember.getUsername()); //username은 DB에 있고 영속성컨텍스트엔 없기 때문에 JPA가 없다고 판단하고 select쿼리를 날림
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
