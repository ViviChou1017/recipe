package tw.com.ispan.service;

import java.util.List;

import tw.com.ispan.domain.FoodMixBean;
import tw.com.ispan.vo.FoodMixVo;

public interface FoodMixService {

	// 新增
	public boolean addFoodMix(FoodMixBean bean);
	
	public boolean addFoodMix(List<FoodMixBean> beans);

	// 食譜ID查詢
	public List<FoodMixBean> findFoodByRecipeId(Integer id);

	// 食譜 分類 ID查詢
	// rId = 食譜ID
	// cId = 分類ID
	public List<FoodMixBean> findFoodByRecipeIdAndClassificationById(Integer rId, Integer cId);

	// 食譜 分類 食材ID查詢
	// rId = 食譜ID
	// cId = 分類ID
	// iId = 食材ID
	public List<FoodMixBean> findFoodByRecipeIdAndClassificationAndIngredientById(Integer rId, Integer cId,
			Integer iId);
	
	
	// 食譜 分類 食材 料理ID查詢
	// rId = 食譜ID
	// cId = 分類ID
	// iId = 食材ID
	// fId = 料理ID
	public FoodMixBean findFoodByRecipeIdAndClassificationAndIngredientAndFoodByCookMethodById(Integer rId, Integer cId,
			Integer iId,Integer fid);

	// 料理ID 查詢
	public List<FoodMixBean> findFoodByCookMethodById(Integer id);

	// 更新
	public boolean upDate(FoodMixVo vo);
	// 食材ID查詢
		public List<FoodMixBean> findFoodByIngredientId(Integer id) ;

}
