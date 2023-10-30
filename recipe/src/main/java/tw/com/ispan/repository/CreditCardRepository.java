package tw.com.ispan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.CreditCardDao;
import tw.com.ispan.domain.CreditCardBean;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardBean, Integer>, CreditCardDao {
	
	

}
