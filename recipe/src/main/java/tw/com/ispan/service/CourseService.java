package tw.com.ispan.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;
import tw.com.ispan.domain.CourseBean;
import tw.com.ispan.repository.CourseRepository;
import tw.com.ispan.util.DatetimeConverter;
import tw.com.ispan.util.MyException;

@Service
@Transactional(rollbackOn = Exception.class)
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	// 查詢全部
	public List<CourseBean> findAll() {
		try {
			List<CourseBean> result = courseRepository.findAll();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 查詢全部總共有幾筆資料
	public Integer countFindAll() {
		try {
			List<CourseBean> list = courseRepository.findAll();
			Integer count = 0;
			for (CourseBean arr : list) {
				count++;
				System.out.println(count);
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ID查詢
	public CourseBean findById(Integer id) {
		if (id != null) {
			Optional<CourseBean> optional = courseRepository.findById(id);
			if (optional != null && optional.isPresent()) {
				return optional.get();
			}
		}

		return null;
	}

	// 新增
	public CourseBean insert(String json) {

		try {

			JSONObject obj = new JSONObject(json);

			// 所有欄位都不能是null所以只要是null就會報MyException
			if (obj.isNull("courseId")) {
				throw new MyException("courseId不可為null");
			} else if (obj.isNull("courseName")) {
				throw new MyException("courseName不可為null");
			} else if (obj.isNull("recipeId")) {
				throw new MyException("recipeId不可為null");
			} else if (obj.isNull("courseCreateDate")) {
				throw new MyException("courseCreateDate不可為null");
			} else if (obj.isNull("courseContent")) {
				throw new MyException("courseContent不可為null");
			} else if (obj.isNull("courseImg")) {
				throw new MyException("courseImg不可為null");
			} else if (obj.isNull("courseVideo")) {
				throw new MyException("courseVideo不可為null");
			} else if (obj.isNull("coursePrice")) {
				throw new MyException("coursePrice不可為null");
			} else if (obj.isNull("chef")) {
				throw new MyException("chef不可為null");
			}

			// 確定不為null之後再把值抓出來
			Integer courseId = obj.getInt("courseId");
			String courseName = obj.getString("courseName");
			Integer recipeId = obj.getInt("recipeId");
			String courseCreateDate = obj.getString("courseCreateDate");
			String courseContent = obj.getString("courseContent");
			String courseImg = obj.getString("courseImg");
			String courseVideo = obj.getString("courseVideo");
			Integer coursePrice = obj.getInt("coursePrice");
			String chef = obj.getString("chef");

			// 值抓出來後再賦值給新的bean
			CourseBean bean = new CourseBean();
			bean.setCourseName(courseName);
			bean.setRecipeId(recipeId);
			bean.setCourseCreateDate(DatetimeConverter.parse(courseCreateDate, "yyy-mm-dd"));
			bean.setCourseContent(courseContent);
			bean.setCourseImg(courseImg);
			bean.setCourseVideo(courseVideo);
			bean.setCoursePrice(coursePrice);
			bean.setChef(chef);

			CourseBean result = courseRepository.save(bean);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 資料存在不存在
	public boolean exists(Integer id) {
		if (courseRepository.findById(id).isPresent()) {
			return true;
		}
		return false;
	}

	// 刪除
	public boolean delete(Integer id) {
		try {
			courseRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 更新
	public CourseBean update(String json) {
		try {
			JSONObject obj = new JSONObject(json);

			// 所有欄位都不能是null所以只要是null就會報MyException
			if (obj.isNull("courseId")) {
				throw new MyException("courseId不可為null");
			} else if (obj.isNull("courseName")) {
				throw new MyException("courseName不可為null");
			} else if (obj.isNull("recipeId")) {
				throw new MyException("recipeId不可為null");
			} else if (obj.isNull("courseCreateDate")) {
				throw new MyException("courseCreateDate不可為null");
			} else if (obj.isNull("courseContent")) {
				throw new MyException("courseContent不可為null");
			} else if (obj.isNull("courseImg")) {
				throw new MyException("courseImg不可為null");
			} else if (obj.isNull("courseVideo")) {
				throw new MyException("courseVideo不可為null");
			} else if (obj.isNull("coursePrice")) {
				throw new MyException("coursePrice不可為null");
			} else if (obj.isNull("chef")) {
				throw new MyException("chef不可為null");
			}

			// 確定不為null之後再把值抓出來
			Integer courseId = obj.getInt("courseId");
			String courseName = obj.getString("courseName");
			Integer recipeId = obj.getInt("recipeId");
			String courseCreateDate = obj.getString("courseCreateDate");
			String courseContent = obj.getString("courseContent");
			String courseImg = obj.getString("courseImg");
			String courseVideo = obj.getString("courseVideo");
			Integer coursePrice = obj.getInt("coursePrice");
			String chef = obj.getString("chef");

			CourseBean bean = courseRepository.findById(courseId).get();
			bean.setCourseId(courseId);
			bean.setCourseName(courseName);
			bean.setRecipeId(recipeId);
			bean.setCourseCreateDate(DatetimeConverter.parse(courseCreateDate, "yyyy-mm-dd"));
			bean.setCourseContent(courseContent);
			bean.setCourseImg(courseImg);
			bean.setCourseVideo(courseVideo);
			bean.setCoursePrice(coursePrice);
			bean.setChef(chef);

			CourseBean result = courseRepository.update(bean);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 篩選廚師
	public List<CourseBean> filterByChef(JSONObject obj) {
		String chef = obj.isNull("chef") ? null : obj.getString("chef");
		if (chef != null && !chef.isEmpty()) {
			List<CourseBean> result = courseRepository.findByChef(chef);
			return result;
		}

		return null;
	}

	// 全部資料分頁 pageNumber=第幾頁，pageSize=一頁幾筆資料，sortBy=排序依據(目前沒用到)
	public Page<CourseBean> getPages(int pageNumber, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Order.asc("courseId")));
		return courseRepository.findAll(pageRequest);
	}

	// 廚師名稱分類+分頁功能-->chef=想要搜尋的廚師名稱，pageNumber=第幾頁，pageSize=一頁幾筆資料
	public Page<CourseBean> findBychefLikeAndPage(String chef, Integer pageNumber, Integer pageSize) {
		if (chef != null && chef.length() != 0) {
			PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
			String name = "%" + chef + "%";
			return courseRepository.findBychefLike(name, pageRequest);
		}
		return Page.empty();
	}
	
	
	//查詢廚師名稱的總比數
	public Integer countFindByChef(String name) {
		Integer count = 0;
		List<CourseBean> list = courseRepository.findByChef(name);
		for (CourseBean arr : list) {
			count++;
			System.out.println(count);
		}return count;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	// 課程名稱查詢+分頁功能-->courseName=想要搜尋的課程名稱，pageNumber=第幾頁，pageSize=一頁幾筆資料
	public Page<CourseBean> findBycourseNameLikeAndPage(String courseName, Integer pageNumber, Integer pageSize) {
		if (courseName != null && courseName.length() != 0) {
			PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
			String name = "%" + courseName + "%";
			return courseRepository.findBycourseNameLike(name, pageRequest);
		}
		return Page.empty();
	}

	// findByName總共有幾筆
	public Integer countFindByName(String name) {
		
			Integer count = 0;
			List<CourseBean> list = courseRepository.findByName(name);
			for (CourseBean arr : list) {
				count++;
				System.out.println(count);
			}return count;
			
	}

	// 日期分類+分頁功能-->courseCreateDate=想要搜尋的日期，pageNumber=第幾頁，pageSize=一頁幾筆資料
	public Page<CourseBean> findBycourseCreateDateAndPage(String courseCreateDate, Integer pageNumber,
			Integer pageSize) {
		if (courseCreateDate != null && courseCreateDate.length() != 0) {
			java.util.Date date = DatetimeConverter.parse(courseCreateDate, "yyyy-MM-dd");

			// 添加排序参数，按照 courseCreateDate 降序排序
			PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize,
					Sort.by(Sort.Order.desc("courseCreateDate")));
			return courseRepository.findBycourseCreateDateLessThanEqual(date, pageRequest);
		}
		return Page.empty();
	}

	//日期查詢總比數
	public Integer countFindByDate(String courseCreateDate) {
		Integer count = 0;
		Date date = DatetimeConverter.parse(courseCreateDate,"yyyy-MM-dd");
		List<CourseBean> list = courseRepository.findByDate(date);
		for(CourseBean arr : list) {
			count ++;
		}
		return count;
	}
	
	
	
	
	
	
	
	
	
	

	// 價格分類+分頁功能-->coursePrice=想要搜尋的價格，pageNumber=第幾頁，pageSize=一頁幾筆資料
	public Page<CourseBean> getFilteredCoursesByPriceAndPage(Integer coursePrice, Integer pageNumber,
			Integer pageSize) {

		if (coursePrice != null) {
			PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
			return courseRepository.findByCoursePriceLessThanEqual(coursePrice, pageRequest);
		}
		return Page.empty();

	}

	//價格查詢總比數
	public Integer countFindByPrice(Integer price){
		Integer count = 0;
		List<CourseBean> list = courseRepository.findByPrice(price);
		for(CourseBean arr : list) {
			count ++;
		}
		return count;
	}
}
