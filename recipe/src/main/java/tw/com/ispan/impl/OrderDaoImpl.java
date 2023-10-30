package tw.com.ispan.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.persistence.PersistenceContext;
import tw.com.ispan.dao.OrderDao;
import tw.com.ispan.domain.OrderBean;

public class OrderDaoImpl implements OrderDao {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	public List<OrderBean> findAllByMemberId(Integer memberId) {

		try {
			String hql = "Select memberId, note FROM OrderBean ob WHERE ob.memberId = :memberId";
//			.memberId, ob.note

			Query<OrderBean> query = this.getSession().createQuery(hql, OrderBean.class);
			query.setParameter("memberId", memberId);
			List<OrderBean> list = query.list();
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
