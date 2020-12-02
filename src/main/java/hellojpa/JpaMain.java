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
		
			Movie movie = new Movie();
			movie.setDirector("aaaa");
			movie.setActor("bbbb");
			movie.setName("러브레터");
			movie.setPrice(10000);

			em.persist(movie);
			
			em.flush();
			em.clear();

			//Movie findMovie = em.find(Movie.class, movie.getId());
			//System.out.println("findMovie = "+ findMovie);
			// 만약 부모테이블로 조회한다면 => union을 사용한 아주 복잡한 쿼리 발생 (테이블을 다 조회하기 때문에)
			Item item = em.find(Item.class, movie.getId());
			System.out.println("item = " + item);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

}
