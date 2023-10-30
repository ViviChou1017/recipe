package tw.com.ispan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.KitchenImgDao;
import tw.com.ispan.domain.KitchenImgBean;
@Repository
public interface KitchenImgRepository extends JpaRepository<KitchenImgBean, Integer>,KitchenImgDao{

}
