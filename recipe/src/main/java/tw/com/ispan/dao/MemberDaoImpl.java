package tw.com.ispan.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tw.com.ispan.domain.MemberBean;

@Repository
public class MemberDaoImpl implements MemberDao {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	public List<Object[]> recipesWithPics(Integer uploaderId) {

//		select recipe.id, recipe.recipe_name, recipe.upload_date,t1.id ,t1.pic_base64
//		 from (select ROW_NUMBER() OVER (PARTITION BY recipe_pic.recipe_id order by recipe_pic.recipe_id asc) as ROW_ID ,* from recipe_pic) as t1
//		 join recipe  on t1.recipe_id = recipe.id
//		 where t1.ROW_ID = 1 AND recipe.uploader_id = 1 

		try {

			String sql = "select recipe.id, recipe.recipe_name, recipe.upload_date,t1.id ,t1.pic_base64 from (select ROW_NUMBER() OVER (PARTITION BY recipe_pic.recipe_id order by recipe_pic.recipe_id asc) as ROW_ID ,* from recipe_pic) as t1 join recipe on t1.recipe_id = recipe.id where t1.ROW_ID = 1 AND recipe.uploader_id = :uploaderId";

			Query<Object[]> query = this.getSession().createNativeQuery(sql, Object[].class);
			query.setParameter("uploaderId", uploaderId);
			List<Object[]> result = query.list();
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;

	}

	public List<Object[]> collectedRecipesWithPics(Integer collectorNumber) {

//		select collect.collect_number, collect.recipe_number,recipe.uploader_id, recipe.recipe_name, collect.collect_date ,t1.pic_base64
//		from (select ROW_NUMBER() OVER (PARTITION BY recipe_pic.recipe_id order by recipe_pic.recipe_id asc) as ROW_ID ,* from recipe_pic) as t1
//		join collect  on t1.recipe_id = collect.recipe_number
//		join recipe on recipe.id = t1.recipe_id
//		where t1.ROW_ID = 1 AND collect.collector_number = 1 
				
		try {

			String sql = "select collect.collect_number, collect.recipe_number,recipe.uploader_id, member.member_name, recipe.recipe_name, collect.collect_date ,t1.pic_base64 from (select ROW_NUMBER() OVER (PARTITION BY recipe_pic.recipe_id order by recipe_pic.recipe_id asc) as ROW_ID ,* from recipe_pic) as t1 join collect  on t1.recipe_id = collect.recipe_number join recipe on recipe.id = t1.recipe_id join member on member.member_number = recipe.uploader_id where t1.ROW_ID = 1 AND collect.collector_number = :collectorNumber";

			Query<Object[]> query = this.getSession().createNativeQuery(sql, Object[].class);
			query.setParameter("collectorNumber", collectorNumber);
			List<Object[]> result = query.list();
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	public List<Object[]> allIngredient() {
		
		try {
			
			String sql = "SELECT TOP (8) ingredient.ingredient_id, ingredient_name, ingredient_image from ingredient join ingredient_image on ingredient_image_id = ingredient.ingredient_id ";
			
			Query<Object[]> query = this.getSession().createNativeQuery(sql, Object[].class);
			List<Object[]> result = query.list();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
//	SELECT TOP (8) ingredient.ingredient_id, ingredient_name, ingredient_image from ingredient join ingredient_image on ingredient_image_id = ingredient.ingredient_id 

}

//	@Override
//	public MemberBean findByAccount(String account) {
//
//		CriteriaBuilder builder = session.getCriteriaBuilder();
//		CriteriaQuery<MemberBean> criteria = builder.createQuery(MemberBean.class);
//
////		from product
//		Root<MemberBean> root = criteria.from(MemberBean.class);
//
////		where
//		List<Predicate> predicates = new ArrayList<>();
//
////		account equal ?
//		if (account != null && account.length() != 0) {
//			predicates.add(builder.equal(root.get("account"), account));
//		}
//
////		where id=? and name like ? and price > ? and make < ? and expire >= ?
//		if (predicates != null && !predicates.isEmpty()) {
//			Predicate[] array = predicates.toArray(new Predicate[1]);
//			criteria = criteria.where(array);
//		}
//		TypedQuery<MemberBean> typedQuery = session.createQuery(criteria);
//		MemberBean result = typedQuery.getSingleResult();
//
//		if (result != null) {
//			return result;
//		} else {
//			return null;
//		}
//	}
//
//	@Override
//	public List<MemberBean> findByAccountLike(String account) {
//		
//		try {
//			CriteriaBuilder builder = session.getCriteriaBuilder();
//			CriteriaQuery<MemberBean> criteria = builder.createQuery(MemberBean.class);
//
////		from product
//			Root<MemberBean> root = criteria.from(MemberBean.class);
//			
////		where
//			List<Predicate> predicates = new ArrayList<>();
//
////		account like ?
//			if (account != null && account.length() != 0) {
//				predicates.add(builder.like(root.get("account"), "%"+account+"%" ));
//			}
//
////		where id=? and name like ? and price > ? and make < ? and expire >= ?
//			if (predicates != null && !predicates.isEmpty()) {
//				Predicate[] array = predicates.toArray(new Predicate[1]);
//				criteria = criteria.where(array);
//			}
//			TypedQuery<MemberBean> typedQuery = session.createQuery(criteria);
//			List<MemberBean> result = typedQuery.getResultList();
//
//			if(result != null) 
//				return result;
//		
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//}
