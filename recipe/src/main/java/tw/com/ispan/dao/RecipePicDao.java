package tw.com.ispan.dao;

import java.util.List;

import tw.com.ispan.domain.RecipePicBean;

public interface RecipePicDao {

	public List<RecipePicBean> findByRecipeId(Integer recipeId);
}
