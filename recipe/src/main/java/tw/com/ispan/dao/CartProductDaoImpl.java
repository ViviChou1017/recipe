package tw.com.ispan.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.ispan.domain.CartProductBean;

@Repository
public class CartProductDaoImpl implements CartProductDao {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	public List<CartProductBean> findAllByMemberNumber(Integer memberNumber) {
//		SELECT cart_product.quantity
//		FROM cart 
//		JOIN cart_product ON cart.id = cart_product.cart_id 
//		JOIN item ON cart_product.product_id = item.item_id

		 
		try {
			System.out.println("test CartProductBean findAll");
//			 WHERE cpb.cartId = cb.id
//			String hql = "SELECT CartProductBean.* FROM CartBean, CartProductBean, ItemBean, MemberBean WHERE memberNumber = 1";
//			String hql = "Select cpb FROM CartBean as cb, CartProductBean cpb WHERE cpb.cartId = cb.id AND cb.id =2";
			String hql = "Select cpb FROM CartBean cb, CartProductBean cpb WHERE cpb.cartId = cb.id AND cb.id = :memberNumber";

			Query<CartProductBean> query = this.getSession().createQuery(hql, CartProductBean.class);
					query.setParameter("memberNumber", memberNumber);
			List<CartProductBean> list = query.list();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}


}
