package tw.com.ispan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import tw.com.ispan.domain.IngredientImageBean;

public interface IngredientImageController {

	public ResponseEntity<?> findByIngredientImageAll();

	// 刪除
	public ResponseEntity<?> delInteger(Integer id);

	// 新增
	public IngredientImageBean addInteger(IngredientImageBean bean);

	public IngredientImageBean addInteger(MultipartFile file, String ingredientId);

	// 更新
	public ResponseEntity<?> updateInteger(Integer id, IngredientImageBean bean);

	// 查食材id
	public ResponseEntity<?> findByIngredientImageId(Integer id);

	// 查圖片id
	public ResponseEntity<?> findByImageId(Integer id);

}
