package tw.com.ispan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.ItemDao;
import tw.com.ispan.domain.ItemBean;
@Repository
public interface ItemRepository extends JpaRepository<ItemBean, Integer>,ItemDao{

	boolean existsByKitchenwareId(Integer kitchenwareId);

	boolean existsByCourseId(Integer courseId);

	void deleteByKitchenwareId(Integer kitchenwareId);

	void deleteByCourseId(Integer courseId);


}
