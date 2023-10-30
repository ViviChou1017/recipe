package tw.com.ispan.dao;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tw.com.ispan.domain.CourseBean;

public interface CourseDao {

	public List<CourseBean> findByDate(Date date);
	
	public List<CourseBean> findByPrice(Integer price);
	
	public List<CourseBean> findByChef(String chef);
	
	public List<CourseBean> findByName(String name);
	
	public CourseBean update(CourseBean bean);
}
