package tw.com.ispan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.CartProductDao;
import tw.com.ispan.domain.CartProductBean;

@Repository
public interface CartProductRepository extends JpaRepository<CartProductBean, Integer>, CartProductDao{

	boolean existsByProductId(Integer productId);

}
