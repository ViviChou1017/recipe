package tw.com.ispan.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.persistence.PersistenceContext;
import tw.com.ispan.domain.KitchenImgBean;

public class KitchenImgDaoImpl implements KitchenImgDao {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	@Override
	public KitchenImgBean update(KitchenImgBean bean) {
		if (bean != null && bean.getKitchenwareId() != null) {
			KitchenImgBean temp = this.getSession().get(KitchenImgBean.class, bean.getKitchenwareImgId());
			if (temp != null) {
				return (KitchenImgBean) this.getSession().merge(bean);
			}
		}

		return null;
	}

	@Override
	public List<KitchenImgBean> findBykitchenwareId(Integer kitchenwareId) {
		if (kitchenwareId != null) {
			Query<KitchenImgBean> query = this.getSession()
					.createQuery("FROM KitchenImgBean WHERE kitchenwareId = :kitchenware_id", KitchenImgBean.class);
			query.setParameter("kitchenware_id", kitchenwareId);
			List<KitchenImgBean> result = query.list();
			return result;
		}
		return null;
	}
}
