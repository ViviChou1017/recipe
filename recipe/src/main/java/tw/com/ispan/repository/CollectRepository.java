package tw.com.ispan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.CollectDao;
import tw.com.ispan.domain.CollectBean;
@Repository
public interface CollectRepository extends JpaRepository<CollectBean, Integer>, CollectDao {

public CollectBean findByCollectorNumberAndRecipeNumber(Integer collectorNumber,Integer recipeNumber );
}
