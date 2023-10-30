package tw.com.ispan.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.RecipeDao;
import tw.com.ispan.domain.RecipeBean;


@Repository
public interface RecipeRepository extends JpaRepository<RecipeBean, Integer>, RecipeDao {

	//@Query(value = "SELECT r.*,c.recipe_category FROM recipe r  JOIN  recipe_category c ON r.category = c.id WHERE c.id =:categoryId",nativeQuery = true)
	public List<RecipeBean> findAllByCategory(Integer categoryId);
	 
	Page<RecipeBean> findByCategory(Integer category, Pageable pageable);//用分類查詢+分頁
	
	
	Page<RecipeBean> findByRecipeNameLike (String recipeName, Pageable pageable);//用名字查詢+分頁
	
	
	public List<RecipeBean> findByUploaderId(Integer uploaderId);
	
	
	
	
	
}
