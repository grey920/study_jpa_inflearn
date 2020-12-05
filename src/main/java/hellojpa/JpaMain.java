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

			Member member1 = new Member();
			member1.setUsername("member1");
			em.persist(member1);

		
			em.flush();
			em.clear();

			Member refMember = em.getReference(Member.class, member1.getId());
			System.out.println("refMember = " + refMember.getClass()); // class hellojpa.Member$HibernateProxy$NwKcwPip
			
			Member findMember = em.find(Member.class, member1.getId()); // 프록시로 한 번 조회가 되면 em.find()로 불러와도 프록시 객체가 나온다. (==비교 보장을 위해)
			System.out.println("findMember = " +  findMember.getClass()); // class hellojpa.Member$HibernateProxy$NwKcwPip
			
			/* 프록시든 아니든 개발에 크게 문제가 없게 개발하는 것이 중요. (refMember ==  findMember)를 맞춘다!! */
																	
			System.out.println("a == a: " + (refMember ==  findMember)); // true (==비교가 무조건 보장된다)
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}


}
