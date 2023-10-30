package tw.com.ispan.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.persistence.PersistenceContext;
import tw.com.ispan.domain.RecipePicBean;

public class RecipePicDaoImpl implements RecipePicDao {
	
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	@Override
	public List<RecipePicBean> findByRecipeId(Integer recipeId){
		if(recipeId != null) {
			Query<RecipePicBean> query = this.getSession().createQuery("FROM RecipePicBean WHERE recipeId = :recipe_id",RecipePicBean.class);
			query.setParameter("recipe_id",recipeId);
			List<RecipePicBean> result = query.list();
			return result;
		}
		return null;
	}
}
