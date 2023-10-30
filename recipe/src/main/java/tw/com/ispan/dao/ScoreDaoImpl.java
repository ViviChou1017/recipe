package tw.com.ispan.dao;

import java.util.List;

import org.hibernate.Session;

import jakarta.persistence.PersistenceContext;
import tw.com.ispan.domain.ScoreBean;

public class ScoreDaoImpl implements ScoreDao {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	@Override
	public List<ScoreBean> findAll() {
		List<ScoreBean> Score = this.getSession().createQuery("from ScoreBean", ScoreBean.class).list();
		return Score;
	}

	@Override
	public ScoreBean findByRatNumber(Integer ratNumber) {
		if (ratNumber != null) {
			return this.getSession().get(ScoreBean.class, ratNumber);
		}
		return null;
	}
	//刪除
	@Override
	public boolean deleteScore(Integer id) {
		if(id != null) {
			ScoreBean temp = this.getSession().get(ScoreBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}

	@Override
	public ScoreBean insertScore(ScoreBean bean) {
		if (bean != null && bean.getRatNumber()!=null) {
			ScoreBean temp = this.getSession().get(ScoreBean.class, bean.getRatNumber());
			if (temp == null) {
				 bean = this.getSession().merge(bean);
				return bean;
			}
		}
		return null;
	}
	
	@Override
	public ScoreBean updateScore(ScoreBean bean) {
		if(bean != null && bean.getRatNumber() != null) {
			ScoreBean temp = this.getSession().get(ScoreBean.class, bean.getRatNumber());
			if(temp!=null) {
				return (ScoreBean) this.getSession().merge(bean);
			}
		}
		return null;
	}
}
