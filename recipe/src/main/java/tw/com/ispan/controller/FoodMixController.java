package tw.com.ispan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import tw.com.ispan.domain.FoodMixBean;
import tw.com.ispan.vo.FoodMixVo;

public interface FoodMixController {
	// 新增
	public boolean addFoodMix(FoodMixBean bean);
	
	public boolean addFoodMix(List<FoodMixBean> beans);

	// 食譜ID查詢
	public List<FoodMixBean> findFoodByRecipeId(Integer id);
	// 食材ID查詢
	public List<FoodMixBean> findFoodByIngredientById(Integer id);

	// 食譜 分類 ID查詢
	public List<FoodMixBean> findFoodByRecipeIdAndClassificationById(FoodMixVo vo);

	// 食譜 分類 食材ID查詢
	public List<FoodMixBean> findFoodByRecipeIdAndClassificationAndIngredientById(FoodMixVo vo);
	
	// 食譜 分類 食材 料理 ID查詢
	public FoodMixBean findFoodByRecipeIdAndClassificationAndIngredientAndFoodByCookMethodById(FoodMixVo vo);

	// 料理ID 查詢
	public List<FoodMixBean> findFoodByCookMethodById(Integer id);

	// 更新
	public boolean upDate(FoodMixVo vo);

}
