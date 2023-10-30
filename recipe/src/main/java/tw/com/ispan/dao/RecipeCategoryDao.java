package tw.com.ispan.dao;

import tw.com.ispan.domain.RecipeCategoryBean;

public interface RecipeCategoryDao {

	public abstract RecipeCategoryBean findByNumber(Integer id);
}
