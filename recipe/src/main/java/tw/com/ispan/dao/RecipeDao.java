package tw.com.ispan.dao;

import java.util.List;

import org.json.JSONObject;

import tw.com.ispan.domain.RecipeBean;

public interface RecipeDao {

	public abstract List<RecipeBean> findAll(); //查全部
	
	
	
	public abstract List<RecipeBean> findRecipeByName(String name); //名稱模糊搜尋
	
	public List<RecipeBean> findByRecipeCategory(Integer RecipeCategory); //分類id搜尋
	
	public abstract boolean deleteRecipe(Integer id); //刪除資料
	
//	public abstract RecipeBean insertRecipe(RecipeBean bean); //新增資料
	
	public abstract RecipeBean updateRecipe(RecipeBean bean); //更新資料
	
//	public List<RecipeBean> findAllByCategory();

//	List<RecipeBean> find(JSONObject obj);
//
//	Long count(JSONObject obj);
}
