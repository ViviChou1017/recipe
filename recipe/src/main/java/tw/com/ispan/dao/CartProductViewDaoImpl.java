package tw.com.ispan.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.ispan.domain.CartProductBean;
import tw.com.ispan.domain.CartProductViewBean;

@Repository
public class CartProductViewDaoImpl implements CartProductViewDao{
	
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}
	
	public List<CartProductViewBean> findAllByMemberNumber(Integer memberNumber) {
		
		
		
		//加DISTINCT避免重複欄位(因kitchenware_img)
		try {
			String hql = "Select DISTINCT cpvb FROM CartProductViewBean cpvb WHERE cpvb.id.memberNumber = :memberNumber";
			
//		String hql = "Select cpvb, kb.kitchenwareName, kb.kitchenwarePrice, cb.course.courseName, cb.coursePrice "
//				+ "FROM CartProductViewBean cpvb left join KitchenwareBean kb left join CourseBean cb "
//				+ "WHERE cpvb.kitchenwareId = kb.kitchenwareId OR cpvb.courseId = cb.courseId "
//				+ "AND cpvb.id = 1";
//		SELECT cart_product_view.*, kitchenware.kitchenware_name, course.course_name, kitchenware.kitchenware_price, course.course_price
//		FROM cart_product_view 
//		left JOIN kitchenware ON cart_product_view.kitchenware_id = kitchenware.kitchenware_id  
//		left JOIN course ON cart_product_view.course_id = course.course_id
			
			Query<CartProductViewBean> query = this.getSession().createQuery(hql, CartProductViewBean.class);
			query.setParameter("memberNumber", memberNumber);
			List<CartProductViewBean> list = query.list();
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



}
