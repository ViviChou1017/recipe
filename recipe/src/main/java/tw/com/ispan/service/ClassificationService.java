package tw.com.ispan.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import tw.com.ispan.domain.ClassificationBean;
import tw.com.ispan.domain.IngredientImageBean;

public interface ClassificationService {
	
	public List<ClassificationBean> findAll();

	public List<ClassificationBean> findByClassificationName(String name);

	//刪除
	public boolean delInteger(Integer id);

	// 新增
	public ClassificationBean addClassification(ClassificationBean bean);

	// 更新
	public ClassificationBean updateClassification(ClassificationBean bean);
	
	//查id
	public ClassificationBean findClassificationById(Integer id); 
	
	//查ids
	public List<ClassificationBean> findClassificationByIds(List<Integer> id); 
}
