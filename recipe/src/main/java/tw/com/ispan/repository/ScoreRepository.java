package tw.com.ispan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.ScoreDao;
import tw.com.ispan.domain.ScoreBean;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreBean, Integer>,ScoreDao {

	boolean existsByRaterNumberAndRecipeNumber(Integer raterNumber,
			Integer recipeNumber);
	
	public ScoreBean findByRaterNumberAndRecipeNumber(Integer raterNumber, Integer recipeNumber);
	
	public List<ScoreBean> findByRecipeNumber(Integer recipeNumber);
}
