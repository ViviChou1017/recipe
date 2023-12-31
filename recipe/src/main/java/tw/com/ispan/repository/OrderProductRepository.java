package tw.com.ispan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.OrderProductDao;
import tw.com.ispan.domain.OrderProductBean;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProductBean, Integer>, OrderProductDao{

}
