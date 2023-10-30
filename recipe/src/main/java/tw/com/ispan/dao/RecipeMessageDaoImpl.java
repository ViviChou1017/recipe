package tw.com.ispan.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.ispan.domain.RecipeMessageBean;

@Repository
public class RecipeMessageDaoImpl implements RecipeMessageDao {
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
//	@Override
//	public RecipeMessageBean intsert(RecipeMessageBean bean) {
//		if(bean != null && bean.getId()!=null) {
//			RecipeMessageBean temp = this.getSession().get(RecipeMessageBean.class, bean.getId());
//			if(temp!=null) {
//				this.getSession().persist(bean);
//				return bean;
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public RecipeMessageBean update(RecipeMessageBean bean) {
//		return null;
//	}
//
//	@Override
//	public boolean delete(Integer id) {
//		return false;
//	}

}
