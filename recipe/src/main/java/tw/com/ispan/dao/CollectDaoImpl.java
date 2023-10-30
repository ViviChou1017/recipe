package tw.com.ispan.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;

@Repository
public class CollectDaoImpl implements CollectDao {

	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
}
