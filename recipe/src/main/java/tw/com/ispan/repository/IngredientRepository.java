package tw.com.ispan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.IngredientDao;
import tw.com.ispan.domain.IngredientBean;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientBean,Integer>, IngredientDao {

	


}
