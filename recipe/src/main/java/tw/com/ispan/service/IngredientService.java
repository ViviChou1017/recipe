package tw.com.ispan.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import tw.com.ispan.domain.ClassificationBean;
import tw.com.ispan.domain.IngredientBean;
import tw.com.ispan.domain.RecipeBean;
import tw.com.ispan.vo.IngredientsVo;

public interface IngredientService {
	public List<IngredientBean> findAll();

	public List<IngredientBean> findByIngredientName(String name);

	//刪除
	public boolean delInteger(Integer id);

	// 新增
	public IngredientBean addInteger(IngredientBean bean);

	// 更新
	public IngredientBean updateInteger(IngredientBean bean);
    
	//查分類id
	public List<IngredientBean> findByclassificationId(Integer id);

	// 查食材id
	public IngredientBean findByIngredientId(Integer id);
	
	// 查食材ids
	public List<IngredientBean> findByIngredientId(List<Integer> id);
	
	
}
