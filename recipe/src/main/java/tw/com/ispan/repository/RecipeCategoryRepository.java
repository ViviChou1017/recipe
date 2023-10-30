package tw.com.ispan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.RecipeCategoryDao;
import tw.com.ispan.domain.RecipeCategoryBean;

@Repository
public interface RecipeCategoryRepository extends JpaRepository<RecipeCategoryBean, Integer>, RecipeCategoryDao {

	
}
