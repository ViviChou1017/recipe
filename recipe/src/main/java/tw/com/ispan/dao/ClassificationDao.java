package tw.com.ispan.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import tw.com.ispan.domain.ClassificationBean;
import tw.com.ispan.domain.IngredientBean;

public interface ClassificationDao {

	public List<ClassificationBean> findAll();

	public List<ClassificationBean> findByClassificationName(String name);

    //刪除
	public boolean delInteger(Integer id);

	// 新增
	public ClassificationBean addClassification(ClassificationBean bean);

	// 更新
	public ClassificationBean updateClassification(ClassificationBean bean);


	public ClassificationBean findClassificationById(Integer id);
	
	
}
