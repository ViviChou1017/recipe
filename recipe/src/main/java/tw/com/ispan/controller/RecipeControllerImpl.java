package tw.com.ispan.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.domain.RecipeBean;
import tw.com.ispan.repository.RecipeRepository;
import tw.com.ispan.service.RecipeService;
import tw.com.ispan.util.DatetimeConverter;

@RestController
@RequestMapping("/recipe")
@CrossOrigin
public class RecipeControllerImpl implements RecipeController {
	@Autowired
	private RecipeService recipeService;

	@Autowired
	private RecipeRepository recipeRepository;
	
	
	@PostMapping("/findByRecipeId")
	public RecipeBean findById(@RequestBody String json) {
		try {
			JSONObject obj = new JSONObject(json);
	        Integer recipeId = obj.isNull("recipeId") ? null : obj.getInt("recipeId");
	        return recipeService.findByRecipeId(recipeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//名字模糊查詢分頁OK
	@PostMapping("/pagename")
	public Page<RecipeBean> getrecipePageName(@RequestBody String json) {
	    try {
	        JSONObject obj = new JSONObject(json);
	        Integer page = obj.isNull("page") ? null : obj.getInt("page");
	        Integer size = obj.isNull("size") ? null : obj.getInt("size");
	        String name = obj.isNull("recipeName") ? null : obj.getString("recipeName");

	        return recipeService.findByRecipeName(name, page, size);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
	//名字模糊查詢總比數
		@Override
		@PostMapping("/findRecipeByName/count")
		public Integer findRecipeByName(@RequestBody String json) {
			JSONObject obj = new JSONObject(json);
			String recipeName = obj.isNull("recipeName")?null:obj.getString("recipeName");
			return  recipeService.findRecipeByNameCount(recipeName);
			
		}
	
	
	
	
	//查全部分頁
	@PostMapping("/page")
	public ResponseEntity<Page<RecipeBean>> getrecipePage(@RequestBody String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer page = obj.isNull("page")? null : obj.getInt("page");
			Integer size = obj.isNull("size")? null : obj.getInt("size");
			
			Pageable pageable = PageRequest.of(page - 1, size); // 頁碼從0開始，所以要減1
			
			Page<RecipeBean> recipePage;
			recipePage = recipeRepository.findAll(pageable);
			return new ResponseEntity<>(recipePage, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//查全部總比數
	@GetMapping("/countByAll")
	public Integer countByAll() {
		Integer count = recipeService.countByAll();
		return count;
	}
	
	
	
	//分類查詢+分頁
	@PostMapping("/page/recipesCategory")
    public Page<RecipeBean> getRecipesByCategory(@RequestBody String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer recipeCategory = obj.isNull("recipeCategory")?null:obj.getInt("recipeCategory");
			Integer page = obj.isNull("page")? null : obj.getInt("page");
			Integer size = obj.isNull("size")? null : obj.getInt("size");
			return recipeService.findRecipesByCategory(recipeCategory, page, size);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			
		}
		return null;
    }
	
	
	
	
	//分類搜尋總比數
	@PostMapping("/recipeCategory")
	public Integer countByRecipeCategory(@RequestBody String json) {
		JSONObject obj = new JSONObject(json);
		Integer recipeCategory = obj.isNull("recipeCategory")?null:obj.getInt("recipeCategory");
		return recipeService.countByRecipeCategory(recipeCategory);
	}
	
	
	
//	//join分類查全部
//	@Override
//	@PostMapping("/findAll")
//	public List<RecipeBean> findAllByCategory(@RequestBody String json) {
//		JSONObject obj = new JSONObject(json);
//		Integer categoryId = obj.isNull("categoryId")? null : obj.getInt("categoryId");
//		List<RecipeBean> recipes = recipeRepository.findAllByCategory(categoryId);
//		return recipes;
//	}
	
//	@Override
//	@GetMapping("/findAll")
//	public List<RecipeBean> findAll() {
//		List<RecipeBean> Recipe = recipeService.findAll();
//		return Recipe;
//	}

	

	//刪除
	@Override
	@DeleteMapping("deleteRecipe/{id}")
	public String deleteRecipe(@PathVariable("id") Integer id) {
		JSONObject responseJson = new JSONObject();

		if (recipeService.deleteRecipe(id)) {
			responseJson.put("message", "刪除成功");
			responseJson.put("success", true);
		} else {
			responseJson.put("message", "刪除失敗");
			responseJson.put("success", false);
		}
		return responseJson.toString();
	}
	
	//新增
	@Override
	@PostMapping("/insertRecipe")
	public RecipeBean insertRecipe(@RequestBody String body) {
		try {
			JSONObject obj = new JSONObject(body);

//			Integer id = obj.getInt("id");
			String recipeName = obj.getString("recipeName");
			Integer uploaderId = obj.getInt("uploaderId");
			String uploadDate = obj.getString("uploadDate");
			Integer category = obj.getInt("category");
			Integer vageCategory = obj.isNull("vageCategory")?null:obj.getInt("vageCategory");
			Integer prepTime = obj.getInt("prepTime");
			String introduction = obj.getString("introduction");
			Integer rank = obj.isNull("rank")?null: obj.getInt("rank");
			String videoUrl =obj.isNull("videoUrl")?null: obj.getString("videoUrl");
			JSONArray step =obj.isNull("step")?null: obj.getJSONArray("step");
			String step2 = step.toString();

			RecipeBean bean = new RecipeBean();
			LocalDateTime currentDateTime = LocalDateTime.now();
			Date currentDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
//			bean.setId(id);
			bean.setRecipeName(recipeName);
			bean.setUploaderId(uploaderId);
			bean.setUploadDate(currentDate);
			bean.setCategory(category);
			bean.setVageCategory(vageCategory);
			bean.setPrepTime(prepTime);
			bean.setIntroduction(introduction);
			bean.setRank(rank);
			bean.setVideoUrl(videoUrl);
			bean.setStep(step2);

			RecipeBean result = recipeService.insertRecipe(bean);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	public RecipeBean insertRecipe(@RequestBody RecipeBean bean) {
//		RecipeBean result = recipeService.insertRecipe(bean);
//		return result;
//	}

	//更新
	@Override
	@PutMapping(path = "/updateRecipe/{id}")
	public String updateRecipe(@PathVariable("id") Integer id, @RequestBody String body) {
		JSONObject responseJson = new JSONObject();
		RecipeBean recipe = null;
		try {
			recipe = recipeService.updateRecipe(body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (recipe == null) {
			responseJson.put("message", "修改失敗");
			responseJson.put("success", false);
		} else {
			responseJson.put("message", "修改成功");
			responseJson.put("success", true);
		}
		return responseJson.toString();
	}


}
