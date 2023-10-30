package tw.com.ispan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.FoodMixDao;
import tw.com.ispan.domain.FoodMixBean;
import tw.com.ispan.domain.FoodMixIdBean;

@Repository
public interface FoodMixRepository extends JpaRepository<FoodMixBean, FoodMixIdBean>, FoodMixDao {

}
