package tw.com.ispan.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.ispan.domain.RecipeCategoryBean;

@Repository
public class RecipeCategoryDaoImpl implements RecipeCategoryDao {

	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
		
	@Override
	public RecipeCategoryBean findByNumber(Integer id) {
		if(id != null) {
			return this.getSession().get(RecipeCategoryBean.class, id);
		}
		return null;
	}

}
