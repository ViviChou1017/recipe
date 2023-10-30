package tw.com.ispan.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.ispan.domain.CartProductBean;

@Repository
public class TrackDaoImpl implements TrackDao {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	public List<Object[]> followListWithName(Integer trackerNumber) {

//		select track_number, member_number, member_name, account from track,　member　where tracked_number = member_number AND tracker_number = 1002

		try {

//			 WHERE cpb.cartId = cb.id
//			String hql = "SELECT CartProductBean.* FROM CartBean, CartProductBean, ItemBean, MemberBean WHERE memberNumber = 1";
//			String hql = "Select cpb FROM CartBean as cb, CartProductBean cpb WHERE cpb.cartId = cb.id AND cb.id =2";
			String hql = "Select tb.trackNumber, mb.memberNumber, mb.account, mb.memberName, mb.avatar FROM TrackBean tb, MemberBean mb WHERE mb.memberNumber = tb.trackedNumber AND tb.trackerNumber = :trackerNumber";

			Query<Object[]> query = this.getSession().createQuery(hql, Object[].class);
			query.setParameter("trackerNumber", trackerNumber);
			List<Object[]> result = query.list();
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
