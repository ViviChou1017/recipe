package tw.com.ispan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.micrometer.common.util.StringUtils;
import tw.com.ispan.domain.ClassificationBean;
import tw.com.ispan.domain.IngredientBean;
import tw.com.ispan.domain.IngredientImageBean;
import tw.com.ispan.repository.IngredientImageRepository;

@Service
@Transactional
public class IngredientImageServiceImpl implements IngredientImageService {
	@Autowired

	private IngredientImageRepository ingredientImageRepository;
	//查詢食材id
	@Override
	public IngredientImageBean findByIngredientImageId(Integer id) {
		try {
			return ingredientImageRepository.findByIngredientImageId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	
	
	
	
//查全部

	@Override
	public List<IngredientImageBean> findByIngredientImageAll() {
		try {
			return ingredientImageRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//刪除
	@Override
	public boolean delInteger(Integer id) {
		try {
			return ingredientImageRepository.delInteger(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//新增
	@Override
	public IngredientImageBean addInteger(IngredientImageBean bean) {
		IngredientImageBean result = null;
		if (bean != null && !StringUtils.isBlank(bean.getIngredientImage())) {
			result = ingredientImageRepository.addInteger(bean);

		}
		return result;
	}
//修改
	@Override
	public IngredientImageBean updateInteger(IngredientImageBean bean) {
		IngredientImageBean result = null;
		try {
			if (bean != null) {
				result=ingredientImageRepository.updateInteger(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}




	@Override
	public IngredientImageBean findByImageId(Integer id) {
		try {
			return ingredientImageRepository.findByImageId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	
	
	

}
