package tw.com.ispan.controller;

import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.domain.CourseBean;
import tw.com.ispan.service.CourseService;
import tw.com.ispan.util.DatetimeConverter;

@RestController
@RequestMapping(path = "/pages/ajax")
@CrossOrigin
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	
	@PostMapping(path = {"/course/findById"})
	public String findById(@RequestBody String json) {
		JSONObject responseJSON = new JSONObject();
		JSONObject obj = new JSONObject(json);
		Integer courseId = obj.isNull("courseId") ? null : obj.getInt("courseId");
		CourseBean bean = courseService.findById(courseId);
		responseJSON.put("CourseId",bean.getCourseId())
					.put("CourseName",bean.getCourseName())
					.put("RecipeId",bean.getRecipeId())
					.put("courseCreateDate",bean.getCourseCreateDate())
					.put("courseContent", bean.getCourseContent())
					.put("courseImg", bean.getCourseImg())
					.put("courseVideo", bean.getCourseVideo())
					.put("coursePrice", bean.getCoursePrice())
					.put("chef",bean.getChef());
		
		return responseJSON.toString();
					
	}
	
	
	
	

	@GetMapping(path = { "/course/page" })
	public List<CourseBean> getPages(@RequestBody String json) {
		try {
			// 接收資料
			// 驗證資料
			JSONObject obj = new JSONObject(json);

			Integer pageNumber = obj.isNull("pageNumber") ? null : obj.getInt("pageNumber");
			Integer pageSize = obj.isNull("pageSize") ? null : obj.getInt("pageSize");
			// 呼叫Service
			Page<CourseBean> page = courseService.getPages(pageNumber, pageSize);
			List<CourseBean> result = page.toList();
			// 根據結果回傳畫面
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping(path = { "/course/find/name" })
	public List<CourseBean> findByName(@RequestBody String json) {
		// 接收資料
		try {
			JSONObject obj = new JSONObject(json);

			String courseName = obj.isNull("courseName") ? null : obj.getString("courseName");
			Integer pageNumber = obj.isNull("pageNumber") ? null : obj.getInt("pageNumber");
			Integer pageSize = obj.isNull("pageSize") ? null : obj.getInt("pageSize");

			// 驗證資料(之後看情況要不要判斷name是不是null)

			// 呼叫service
			Page<CourseBean> page = courseService.findBycourseNameLikeAndPage(courseName, pageNumber, pageSize);
			List<CourseBean> result = page.toList();
			// 根據執行結果，結果導向view
			if (result != null) {
				// 成功的畫面
				return result;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// 失敗的畫面
		return null;

	}

	@PostMapping(path = { "/course/find/name/count" })
	public Integer countFindByName(@RequestBody String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String courseName = obj.isNull("courseName") ? null : obj.getString("courseName");

			Integer count = courseService.countFindByName(courseName);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}

	@PostMapping(path = { "/course/filter/byChef" })
	public List<CourseBean> filterByChef(@RequestBody String json) {

		try {
			// 接收資料
			JSONObject obj = new JSONObject(json);

			String chef = obj.isNull("chef") ? null : obj.getString("chef");
			Integer pageNumber = obj.isNull("pageNumber") ? null : obj.getInt("pageNumber");
			Integer pageSize = obj.isNull("pageSize") ? null : obj.getInt("pageSize");

			// 驗證資料(之後看情況要不要判斷chef是不是null)

			// 呼叫service
			Page<CourseBean> page = courseService.findBychefLikeAndPage(chef, pageNumber, pageSize);
			List<CourseBean> result = page.toList();
			System.out.println(result);

			// 根據執行結果，結果導向view(如果filter選的值跟輸入的值無關或是沒輸入導致找不到資料，要有提示字根使用者說請輸入正確關鍵字查詢)
			if (result != null) {
				// 成功的畫面
				return result;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// 失敗的畫面
		return null;
	}

	@PostMapping(path = { "/course/filter/byChef/count" })
	public Integer countFindByChef(@RequestBody String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String chef = obj.isNull("chef") ? null : obj.getString("chef");
			Integer count = courseService.countFindByChef(chef);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}

	@PostMapping(path = { "/course/filter/byPrice" })
	public List<CourseBean> getFilteredCoursesByPriceAndPage(@RequestBody String json) {

		// 接收資料
		try {
			JSONObject obj = new JSONObject(json);

			Integer price = obj.isNull("coursePrice") ? null : obj.getInt("coursePrice");
			Integer pageNumber = obj.isNull("pageNumber") ? null : obj.getInt("pageNumber");
			Integer pageSize = obj.isNull("pageSize") ? null : obj.getInt("pageSize");

			// 驗證資料(之後看情況要不要判斷price是不是null)

			// 呼叫service
			Page<CourseBean> page = courseService.getFilteredCoursesByPriceAndPage(price, pageNumber, pageSize);
			List<CourseBean> result = page.toList();

			// 根據執行結果，結果導向view(如果filter選的值跟輸入的值無關或是沒輸入導致找不到資料，要有提示字根使用者說請輸入正確關鍵字查詢)
			if (result != null) {
				// 成功的畫面
				return result;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// 失敗的畫面
		return null;
	}

	@PostMapping(path = { "/course/filter/byPrice/count" })
	public Integer countFindByPrice(@RequestBody String json) {

		try {
			JSONObject obj = new JSONObject(json);
			Integer price = obj.isNull("coursePrice") ? null : obj.getInt("coursePrice");
			Integer count = courseService.countFindByPrice(price);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}

	@PostMapping(path = { "/course/filter/byDate" })
	public List<CourseBean> findBycourseCreateDateAndPage(@RequestBody String json) {

		// 接收資料
		try {
			JSONObject obj = new JSONObject(json);
			String courseCreateDate = obj.isNull("courseCreateDate") ? null : obj.getString("courseCreateDate");
			Integer pageNumber = obj.isNull("pageNumber") ? null : obj.getInt("pageNumber");
			Integer pageSize = obj.isNull("pageSize") ? null : obj.getInt("pageSize");

			// 驗證資料(之後看情況要不要判斷courseCreateDate是不是null)

			// 呼叫service
			Page<CourseBean> page = courseService.findBycourseCreateDateAndPage(courseCreateDate, pageNumber, pageSize);
			List<CourseBean> result = page.toList();

			// 根據執行結果，結果導向view(如果filter選的值跟輸入的值無關或是沒輸入導致找不到資料，要有提示字根使用者說請輸入正確關鍵字查詢)
			if (result != null) {
				// 成功的畫面
				return result;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// 失敗的畫面
		return null;

	}

	@PostMapping(path = { "/course/filter/byDate/count" })
	public Integer countFindByDate(@RequestBody String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String courseCreateDate = obj.isNull("courseCreateDate") ? null : obj.getString("courseCreateDate");
			Integer count = courseService.countFindByDate(courseCreateDate);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}

	@PostMapping(path = { "/course/finds" })
	public List<CourseBean> findAll(@RequestBody String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer pageNumber = obj.isNull("pageNumber") ? null : obj.getInt("pageNumber");
			Integer pageSize = obj.isNull("pageSize") ? null : obj.getInt("pageSize");
			Page<CourseBean> list = courseService.getPages(pageNumber, pageSize);
		    List<CourseBean> result = list.toList();
		    return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}return null;
	}

	@GetMapping(path = { "/course/finds/count" })
	public Integer countFindAll() {
		Integer count = courseService.countFindAll();
		return count;
	}

	@GetMapping(path = { "/course/find/{id}" })
	public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
		CourseBean result = courseService.findById(id);
		if (result != null) {
			ResponseEntity<CourseBean> response = ResponseEntity.ok().body(result);
			return response;
		} else {
			ResponseEntity<Void> response = ResponseEntity.notFound().build();
			return response;
		}
	}

	@PostMapping(path = { "/course" })
	public String create(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();

		JSONObject obj = new JSONObject(json);

		Integer id = obj.getInt("courseId");

		if (courseService.exists(id)) {
			responseJson.put("message", "資料已存在");
			responseJson.put("success", false);
		} else {
			CourseBean course = courseService.insert(json);
			if (course != null) {
				responseJson.put("message", "新增成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "新增失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();

	}

	@DeleteMapping(path = { "/course/remove/{id}" })
	public String remove(@PathVariable(name = "id") Integer id) {

		JSONObject responseJson = new JSONObject();
		if (!courseService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			if (courseService.delete(id)) {
				responseJson.put("message", "刪除成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "刪除失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}

	@PutMapping(path = { "/course/update/{id}" })
	public String update(@PathVariable(name = "id") Integer id, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();

		if (!courseService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			CourseBean course = courseService.update(json);
			if (course != null) {
				responseJson.put("message", "修改成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "修改失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}

}
