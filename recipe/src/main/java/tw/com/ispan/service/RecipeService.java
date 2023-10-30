package tw.com.ispan.service;

import java.util.List;


import org.json.JSONObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import tw.com.ispan.domain.RecipeBean;

public interface RecipeService {


	public List<RecipeBean> findAll(); //查全部

//	public List<RecipeBean> findAll(); //查全部
	
//	public abstract List<RecipeBean> findAllByCategoryId(Integer id); //join分類查全部
	
	public RecipeBean findByRecipeId(Integer recipeId);
	
	public Page<RecipeBean> findByRecipeName(String recipeName,  Integer page, Integer size);//用名字查詢+分頁


	public List<RecipeBean> findByUploaderId(Integer uploaderId);
	
	

//	public List<RecipeBean> findRecipeByName(String name); // 名稱模糊搜尋

	public Integer findRecipeByNameCount(String name); // 名稱模糊搜尋總比數


	public boolean deleteRecipe(Integer id); // 刪除資料

	public abstract RecipeBean insertRecipe(RecipeBean bean); // 新增資
	
//	List<RecipeBean> find(JSONObject obj);

	public RecipeBean updateRecipe(String json); // 更新資料
	
//	List<RecipeBean> find(JSONObject obj);
	
	public Integer countByAll(); //查全部總比數
	
	public Page<RecipeBean> findRecipesByCategory(Integer recipesCategory, Integer page, Integer size);//分類查詢+分頁
	
	public Integer countByRecipeCategory(Integer RecipeCategory);//分類搜尋總比數

//	RecipeBean updateRecipe(String json);


}
