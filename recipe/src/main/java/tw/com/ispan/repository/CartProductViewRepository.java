package tw.com.ispan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.CartProductViewDao;
import tw.com.ispan.domain.CartProductViewBean;

@Repository
public interface CartProductViewRepository extends JpaRepository<CartProductViewBean, Integer>, CartProductViewDao {

}
