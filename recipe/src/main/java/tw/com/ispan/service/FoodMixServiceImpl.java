package tw.com.ispan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.com.ispan.domain.FoodMixBean;
import tw.com.ispan.domain.FoodMixIdBean;
import tw.com.ispan.repository.FoodMixRepository;
import tw.com.ispan.vo.FoodMixVo;

@Service
@Transactional
public class FoodMixServiceImpl implements FoodMixService {
	
	@Autowired
	private FoodMixRepository foodMixRepository;
//新增
	@Override
	public boolean addFoodMix(FoodMixBean bean) {
		boolean result = false;
		
		if (bean != null) {
			result = foodMixRepository.addFoodMix(bean);
			//result = foodMixRepository.addFoodMix(bean);
		}
		return result;
	}
	//新增 多筆
	@Override
	public boolean addFoodMix(List<FoodMixBean> beans) {
		boolean result = false;
		result = foodMixRepository.addFoodMix(beans);
		return result;
	}
//查詢食譜id
	@Override
	public List<FoodMixBean> findFoodByRecipeId(Integer id) {
		try {
			return foodMixRepository.findFoodByRecipeId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	

	//查食譜and分類id
	@Override
	public List<FoodMixBean> findFoodByRecipeIdAndClassificationById(Integer rId, Integer cId) {
		try {
			return foodMixRepository.findFoodByRecipeIdAndClassificationById(rId,cId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	// 查食譜and分類and食材id
	@Override
	public List<FoodMixBean> findFoodByRecipeIdAndClassificationAndIngredientById(Integer rId, Integer cId,
			Integer iId) {
		try {
			return foodMixRepository.findFoodByRecipeIdAndClassificationAndIngredientById(rId,cId,iId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	// 查料理法id
	@Override
	public List<FoodMixBean> findFoodByCookMethodById(Integer id) {
		try {
			return foodMixRepository.findFoodByCookMethodById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	
	
	
	

	// 更新
	@Override
	public boolean upDate(FoodMixVo vo) {
		
		boolean result = false;
		try {
			FoodMixIdBean foodMixIdBean = new FoodMixIdBean();
			foodMixIdBean.setRecipeId(vo.getRecipeId());
			foodMixIdBean.setClassificationId(vo.getClassificationId());
			foodMixIdBean.setIngredientId(vo.getIngredientId());
			foodMixIdBean.setCookeryId(vo.getCookeryId());
			Optional<FoodMixBean> bean = foodMixRepository.findById(foodMixIdBean);
			if(null != bean) {
				FoodMixBean foodMixBean = bean.get();
				foodMixRepository.delete(foodMixBean);
				
				FoodMixIdBean newFoodMixIdBean = new FoodMixIdBean();
				newFoodMixIdBean.setRecipeId(vo.getRecipeId());
				newFoodMixIdBean.setClassificationId(vo.getClassificationId());
				newFoodMixIdBean.setIngredientId(vo.getNewIngredientId());
				newFoodMixIdBean.setCookeryId(vo.getCookeryId());
				FoodMixBean newFoodMixBean = new FoodMixBean();
				newFoodMixBean.setIngredientWeight(vo.getIngredientWeight());
				newFoodMixBean.setFoodMixId(newFoodMixIdBean);
				foodMixRepository.addFoodMix(newFoodMixBean);
				 
			}
			result =  true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	// 查食譜and分類and食材id and料理法id
	@Override
	public FoodMixBean findFoodByRecipeIdAndClassificationAndIngredientAndFoodByCookMethodById(Integer rId,
			Integer cId, Integer iId, Integer fid) {
		
		FoodMixIdBean foodMixIdBean = new FoodMixIdBean();
		foodMixIdBean.setRecipeId(rId);
		foodMixIdBean.setClassificationId(cId);
		foodMixIdBean.setIngredientId(iId);
		foodMixIdBean.setCookeryId(fid);
		Optional<FoodMixBean> findById = foodMixRepository.findById(foodMixIdBean);
		FoodMixBean foodMixBean = findById.get();
		
		return foodMixBean;
	}
	//查詢食材id
	@Override
	public List<FoodMixBean> findFoodByIngredientId(Integer id) {
		try {
			return foodMixRepository.findFoodByIngredientId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	

}
