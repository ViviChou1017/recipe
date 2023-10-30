package tw.com.ispan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.micrometer.common.util.StringUtils;
import tw.com.ispan.domain.IngredientBean;
import tw.com.ispan.domain.RecipeBean;
import tw.com.ispan.repository.IngredientRepository;
import tw.com.ispan.vo.IngredientsVo;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {
	// private static final Date ingredientdate = null;

	// private final String INGREDIENT_DATETIME = "yyyy-MM-dd HH:mm:ss";

	@Autowired
	private IngredientRepository ingredientRepository;
	private String name;

	// 查全部
	@Override
	public List<IngredientBean> findAll() {
		try {
			return ingredientRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//刪除
	@Override
	public boolean delInteger(Integer id) {
		try {
			return ingredientRepository.delInteger(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//新增
	@Override
	public IngredientBean addInteger(IngredientBean bean) {
		IngredientBean result = null;
		if (bean != null && !StringUtils.isBlank(bean.getIngredientName())) {
			result = ingredientRepository.addInteger(bean);

		}
		return result;
	}

//修改
	@Override
	public IngredientBean updateInteger(IngredientBean bean) {
		IngredientBean result = null;
		try {
			if (bean != null) {
				result = ingredientRepository.updateInteger(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

//查詢名字
	@Override
	public List<IngredientBean> findByIngredientName(String name) {
		try {
			return ingredientRepository.findByIngredientName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	// 查分類id
	@Override
	public List<IngredientBean> findByclassificationId(Integer id) {
		try {
			return ingredientRepository.findByclassificationId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	// 查食材id
	@Override
	public IngredientBean findByIngredientId(Integer id) {
		try {
			return ingredientRepository.findByIngredientId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<IngredientBean> findByIngredientId(List<Integer> id) {
		List<IngredientBean> beans = ingredientRepository.findByIngredientIds(id);
		return beans;
	}

	
	
	
	
}
