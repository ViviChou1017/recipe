package tw.com.ispan.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import tw.com.ispan.domain.RecipeBean;

public interface RecipeController {
	
//	public List<RecipeBean> findAll(); //查全部
	
//	public abstract List<RecipeBean> findAllByCategory(String json); //join分類查全部

	public Integer findRecipeByName(String json); // 名稱模糊搜尋總比數
	
	public Integer countByRecipeCategory(String json);//分類id查詢

	public String deleteRecipe(Integer id); // 刪除資料

//	public RecipeBean insertRecipe(RecipeBean bean); // 新增資料

	public String updateRecipe(Integer id,String body); // 更新資料
	
//	List<RecipeBean> find(JSONObject obj);

	public RecipeBean insertRecipe(String body);
	
	public ResponseEntity<Page<RecipeBean>> getrecipePage( String json);
}



