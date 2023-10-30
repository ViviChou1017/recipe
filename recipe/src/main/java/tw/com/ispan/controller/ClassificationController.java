package tw.com.ispan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import tw.com.ispan.domain.ClassificationBean;
import tw.com.ispan.domain.IngredientBean;

public interface ClassificationController {

	public ResponseEntity<?> findAll();

	public List<ClassificationBean> findByClassificationName(String name);

	//刪除
	public ResponseEntity<?> delInteger(Integer id);

	// 新增
	public ClassificationBean addClassification(ClassificationBean bean);

	// 更新
	public ResponseEntity<?> updateClassification(Integer id,ClassificationBean bean);
//查id
	public ResponseEntity<?> selectByClassificationId(Integer id);
	public ResponseEntity<?> selectByIngredientId(Integer id);
	
}