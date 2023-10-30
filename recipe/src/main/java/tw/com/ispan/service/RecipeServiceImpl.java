package tw.com.ispan.service;


import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;
import tw.com.ispan.domain.RecipeBean;
import tw.com.ispan.repository.RecipeRepository;
import tw.com.ispan.service.RecipeService;



import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.domain.RecipeBean;
import tw.com.ispan.repository.RecipeRepository;

@Service
@Transactional

public class RecipeServiceImpl implements RecipeService {
	@Autowired
	private RecipeRepository recipeRepository;
	public String name;
	

	@Override
	public List<RecipeBean> findAll() {
		try {
			return recipeRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


//	//join分類查全部
//	@Override
//	public List<RecipeBean> findAllByCategoryId(Integer categoryId) {
//		List<RecipeBean> recipes = recipeRepository.findAllByCategory(categoryId);
//		return recipes;
//	}
	
//	@Override
//	public List<RecipeBean> findAll() {
//		try {
//			return recipeRepository.findAll();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}


	@Override
	public RecipeBean findByRecipeId(Integer recipeId) {
		try {
			Optional<RecipeBean> bean = recipeRepository.findById(recipeId);
			RecipeBean result = bean.get();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//用名字查詢+分頁
		@Override
		public Page<RecipeBean> findByRecipeName(String recipeName,  Integer page, Integer size){
			PageRequest pageable = PageRequest.of(page - 1, size); // 創建分頁請求
			return recipeRepository.findByRecipeNameLike("%"+recipeName+"%", pageable);
		}
	
	

	//名字模糊查詢總比數
	@Override
	public Integer findRecipeByNameCount(String name) {
		if(name != null &&  name.length()!=0) {
			Integer count = 0;
			List<RecipeBean> list = recipeRepository.findRecipeByName(name);
			for(RecipeBean arr:list) {
				count ++;
			}
			return count;
		}
		return 0;
	}
	
	


	//刪除

	@Override
	public boolean deleteRecipe(Integer id) {
		try {
			return recipeRepository.deleteRecipe(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	
	
	
	
	
	

	

	
	//新增
	@Override
	public RecipeBean insertRecipe(RecipeBean bean) {
		if(bean != null && bean.getRecipeName()!=null) {
			 return  recipeRepository.save(bean);
		}
		return null;
	}
	
	
	//更新
	@Override
	public RecipeBean updateRecipe(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.getInt("id");
			String recipeName = obj.getString("recipeName");
			Integer uploaderId = obj.getInt("uploaderId");
			String uploadDate = obj.getString("uploadDate");
			Integer category = obj.getInt("category");
			Integer vageCategory = obj.getInt("vageCategory");
			Integer prepTime = obj.getInt("prepTime");
			String introduction = obj.getString("introduction");
			Integer rank = obj.getInt("rank");
			String videoUrl = obj.getString("videoUrl");
			String step = obj.getString("step"); 

			RecipeBean update = new RecipeBean();
			LocalDateTime currentDateTime = LocalDateTime.now();
			Date currentDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
			update.setId(id);
			update.setRecipeName(recipeName);
			update.setUploaderId(uploaderId);
			update.setUploadDate(currentDate);		
			update.setCategory(category);
			update.setVageCategory(vageCategory);
			update.setPrepTime(prepTime);
			update.setIntroduction(introduction);
			update.setRank(rank);
			update.setVideoUrl(videoUrl);
			update.setStep(step);
			
			return recipeRepository.updateRecipe(update);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	


	//查全部總比數
	@Override
	public Integer countByAll() {
		Integer count = 0;
		List<RecipeBean> list = recipeRepository.findAll();
		for(RecipeBean arr : list) {
			count ++;
		}
		return count;
	}

	
	
	
	
	
	//分類查詢+分頁
	@Override
	public Page<RecipeBean> findRecipesByCategory(Integer recipesCategory, Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page - 1, size); // 创建分页请求
        return recipeRepository.findByCategory(recipesCategory, pageable);
    }
	
	
	
	
	//分類搜尋總比數
	public Integer countByRecipeCategory(Integer RecipeCategory) {
		if(RecipeCategory!= null) {
			Integer count = 0;
			List<RecipeBean> list = recipeRepository.findByRecipeCategory(RecipeCategory);
			for(RecipeBean arr : list) {
				count++;
			}
			return count;
		}
		return 0;
	}


	@Override
	public List<RecipeBean> findByUploaderId(Integer uploaderId) {
		
		if(uploaderId != null) {
			return recipeRepository.findByUploaderId(uploaderId);
		}
		
		
		return null;
	}




}
