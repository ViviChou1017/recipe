package tw.com.ispan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.IngredientImageDao;
import tw.com.ispan.domain.IngredientImageBean;

@Repository
public interface IngredientImageRepository extends JpaRepository<IngredientImageBean, Integer>, IngredientImageDao {

	
}
