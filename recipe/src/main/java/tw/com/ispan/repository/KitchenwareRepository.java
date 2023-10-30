package tw.com.ispan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.KitchenwareDao;
import tw.com.ispan.domain.KitchenwareBean;
@Repository
public interface KitchenwareRepository extends JpaRepository<KitchenwareBean, Integer>,KitchenwareDao{

}
