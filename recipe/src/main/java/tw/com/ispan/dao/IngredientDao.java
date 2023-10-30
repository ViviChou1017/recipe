package tw.com.ispan.dao;

import java.util.List;

import tw.com.ispan.domain.ClassificationBean;
import tw.com.ispan.domain.IngredientBean;

public interface IngredientDao {
	public List<IngredientBean> findAll();

	public List<IngredientBean> findByIngredientName(String name);

//刪除
	public boolean delInteger(Integer id);

	// 新增
	public IngredientBean addInteger(IngredientBean bean);

	// 更新
	public IngredientBean updateInteger(IngredientBean bean);

    //查分類id
	public List<IngredientBean> findByclassificationId(Integer id);

	// 查食材id
	public IngredientBean findByIngredientId(Integer id);

	
	// 查食材ids
    public List<IngredientBean> findByIngredientIds(List<Integer> id);

}
