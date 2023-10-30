package tw.com.ispan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.CartDao;
import tw.com.ispan.dao.OrderDao;
import tw.com.ispan.domain.CartBean;
import tw.com.ispan.domain.OrderBean;

@Repository
public interface OrderRepository extends JpaRepository<OrderBean, Integer>, OrderDao {
	
	
	List<OrderBean> findByMemberId(Integer memberId);
	public List<OrderBean> findAllByMemberId(Integer memberId);
	
	

}
