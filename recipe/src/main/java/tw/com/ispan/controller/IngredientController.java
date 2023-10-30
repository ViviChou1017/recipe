package tw.com.ispan.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import tw.com.ispan.domain.IngredientBean;
import tw.com.ispan.domain.IngredientImageBean;
import tw.com.ispan.vo.IngredientsVo;

public interface IngredientController {
	public ResponseEntity<?> findAll();

	public List<IngredientBean> findByIngredientName(String name);

	// 刪除
	public ResponseEntity<?> delInteger(Integer id);

	// 新增
	public IngredientBean addInteger(IngredientBean bean);

	// 更新
	public ResponseEntity<?> updateInteger(Integer id, IngredientBean bean);

//查分類id
	public List<IngredientBean> findByclassificationId(Integer id);

	public IngredientsVo getIngredient(Integer id);

	// 查食材id
		public ResponseEntity<?> findByIngredientId(Integer id);

		

}
