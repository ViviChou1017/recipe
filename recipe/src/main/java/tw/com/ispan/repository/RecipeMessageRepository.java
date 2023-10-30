package tw.com.ispan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.RecipeMessageDao;
import tw.com.ispan.domain.RecipeMessageBean;

@Repository
public interface RecipeMessageRepository extends JpaRepository<RecipeMessageBean, Integer>, RecipeMessageDao {

	public List<RecipeMessageBean> findByRecipeId(Integer recipeId);
	
	public RecipeMessageBean findByRecipeIdAndId(Integer recipeId,Integer id);
}
