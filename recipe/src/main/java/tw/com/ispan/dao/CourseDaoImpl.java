package tw.com.ispan.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.persistence.PersistenceContext;
import tw.com.ispan.domain.CourseBean;

public class CourseDaoImpl implements CourseDao {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}


	
	
	
	@Override
	public List<CourseBean> findByName(String name) {
		Query<CourseBean> query = this.getSession().createQuery("FROM CourseBean WHERE courseName like:course_name",
				CourseBean.class);
		query.setParameter("course_name", "%" + name + "%");
		List<CourseBean> result = query.list();
		return result;
	}

	
	
	
	
	@Override
	public CourseBean update(CourseBean bean) {
		if (bean != null && bean.getCourseId() != null) {
			CourseBean temp = this.getSession().get(CourseBean.class, bean.getCourseId());
			if (temp != null) {
//				CourseBean result = this.getSession().merge(bean);
//				return result;
				return (CourseBean) this.getSession().merge(bean);
			}
		}
		return null;
	}

	
	
	@Override
	public List<CourseBean> findByChef(String chef) {
				String hql = "FROM CourseBean WHERE chef LIKE :chef_name ";
				Query<CourseBean> query = this.getSession().createQuery(hql, CourseBean.class);
				query.setParameter("chef_name", "%" + chef + "%");
				List<CourseBean> result = query.list();
				return result;
			
	}
	
	
	//findByPrice
	@Override
	public List<CourseBean> findByPrice(Integer price){
		 String hql = "FROM CourseBean WHERE coursePrice <= :course_price ORDER BY coursePrice DESC";
			Query<CourseBean> query = this.getSession().createQuery(hql,CourseBean.class);
			query.setParameter("course_price", price);
			List<CourseBean> result = query.list();
			return result;
	 }
	
	
//	@Override
//	public List<CourseBean> findByPrice(Integer price){
//		String hql = "FROM CourseBean WHERE coursePrice <= :course_price ORDER BY coursePrice DESC";
//		Query<CourseBean> query = this.getSession().createQuery(hql,CourseBean.class);
//		query.setParameter("course_price", price);
//		List<CourseBean> result = query.list();
//		return result;
//	}
	
	@Override
	public List<CourseBean> findByDate(Date date){
		String hql = "FROM CourseBean WHERE courseCreateDate <= :course_create_date ORDER BY courseCreateDate DESC";
		Query<CourseBean> query = this.getSession().createQuery(hql,CourseBean.class);
		query.setParameter("course_create_date", date);
		List<CourseBean> result = query.list();
		return result;
	} 
	
	
	

}
