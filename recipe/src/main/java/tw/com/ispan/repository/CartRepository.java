package tw.com.ispan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.CartDao;
import tw.com.ispan.domain.CartBean;

@Repository
public interface CartRepository extends JpaRepository<CartBean, Integer>, CartDao {
	
	boolean existsByUserAccountId(Integer userAccountId);


}
