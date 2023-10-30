package tw.com.ispan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.CookingMethodDao;
import tw.com.ispan.domain.CookingMethodBean;
import tw.com.ispan.domain.MemberBean;

@Repository
public interface CookingMethodRepository extends JpaRepository<CookingMethodBean, Integer>, CookingMethodDao {
	
	List<CookingMethodBean> findByMethodNameContaining(String methodName);
	

}
