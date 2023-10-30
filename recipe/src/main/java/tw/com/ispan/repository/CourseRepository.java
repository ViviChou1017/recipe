package tw.com.ispan.repository;


import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.CourseDao;
import tw.com.ispan.domain.CourseBean;
@Repository
public interface CourseRepository extends JpaRepository<CourseBean , Integer>,CourseDao{
	//用方法的名字來表示是等於、小於等於、大於等於，以及模糊查詢
	public  Page<CourseBean> findByCoursePriceLessThanEqual(Integer price, Pageable pageable);
	
	public  Page<CourseBean> findBycourseCreateDateLessThanEqual(Date date , Pageable pageable);
	
	public  Page<CourseBean> findBycourseNameLike(String courseName,Pageable pageable);
	
	public  Page<CourseBean> findBychefLike(String chef,Pageable pageable);
}
