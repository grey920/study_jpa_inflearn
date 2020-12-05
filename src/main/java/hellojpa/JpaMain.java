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

			Member member1 = new Member();
			member1.setUsername("member1");
			em.persist(member1);

		
			em.flush();
			em.clear();

			Member refMember = em.getReference(Member.class, member1.getId());
			/*프록시 클래스 확인 방법*/
			System.out.println("refMember = "+ refMember.getClass()); // Proxy
			
			/*프록시 강제 초기화*/
			Hibernate.initialize(refMember); //(하이버네이트가 제공하는) 강제초기화
			//refMember.getUsername();// 강제 초기화 (강제 호출: JPA 표준은 강제초기화가 없음)
			
			/*프록시 인스턴스의 초기화 여부 확인*/
			System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember)); // 초기화가 안되었으면 false, 되어있으면 true
			
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
