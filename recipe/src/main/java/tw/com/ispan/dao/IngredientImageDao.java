package tw.com.ispan.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import tw.com.ispan.domain.IngredientImageBean;

public interface IngredientImageDao {
	
	public List<IngredientImageBean> findByIngredientImageAll();

//刪除
	public boolean delInteger(Integer id);

	// 新增
	public IngredientImageBean addInteger(IngredientImageBean bean);

	// 更新
	public IngredientImageBean updateInteger(IngredientImageBean bean);
	//查食材id
	public IngredientImageBean findByIngredientImageId(Integer id);
	//查圖片id
	public IngredientImageBean findByImageId(Integer id);
	
}
