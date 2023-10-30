package tw.com.ispan.dao;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.json.JSONException;
import org.json.JSONObject;
import tw.com.ispan.domain.CourseBean;
import tw.com.ispan.repository.CourseRepository;
import tw.com.ispan.util.DatetimeConverter;

//@SpringBootTest
public class CourseDaoImplTest {

	@Autowired
	private CourseRepository courseRepository;

//	@Test
//	public void testFindByChef() {
//		List<CourseBean> ans = courseRepository.findByChef("L");
//		Object[] arr = ans.toArray();
//		for (Object rest : arr) {
//			CourseBean bean = (CourseBean) rest;
//			System.out.println(bean.toString());
//		}
//
//	}
	
	
	
	//@Test
//	public void testFindByPrice() {
//		List<CourseBean> ans = courseRepository.findByPrice(50000000);
//		Object[] arr = ans.toArray();
//		for(Object rest : arr) {
//			CourseBean bean = (CourseBean) rest;
//			System.out.println(bean.toString());
//		}
//	}
	
	
//	@Test
	public void testFindByDate() {
		String d = "2023-09-24";
		Date date = DatetimeConverter.parse(d,"yyyy-mm-dd");
		List<CourseBean> ans = courseRepository.findByDate(date);
		System.out.println(date);
		Object[] arr = ans.toArray();
		for(Object rest : arr) {
			CourseBean bean = (CourseBean) rest;
			System.out.println(bean.toString());
		}
	}
}