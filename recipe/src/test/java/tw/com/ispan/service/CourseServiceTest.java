package tw.com.ispan.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import tw.com.ispan.domain.CourseBean;

//@SpringBootTest
public class CourseServiceTest {

	@Autowired
	private CourseService courseService;

//	@Test
	public void testFindByCoursePriceLessThanEqual() {
		Page<CourseBean> ans = courseService.getFilteredCoursesByPriceAndPage(3000,1,2);
		List<CourseBean> list = ans.toList();
		Object[] arr = list.toArray();
		for(Object bean:arr) {
			CourseBean rest = (CourseBean)bean;
			System.out.println(rest.toString());
		}
	}

//	@Test
	public void testCountFindByDate() {
		String data = "2023-09-30";
		Integer count = courseService.countFindByDate(data);
		System.out.println(count);
	}
	
	
}
