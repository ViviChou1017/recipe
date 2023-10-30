package tw.com.ispan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.MemberDao;
import tw.com.ispan.domain.MemberBean;

@Repository
public interface MemberRepository extends MemberDao, JpaRepository<MemberBean, Integer> {

	List<MemberBean> findByAccountContaining(String infix);
	
	List<MemberBean>findByAccountContainingAndAccountNot(String account, String notAccount);
	
	MemberBean findByAccount(String account);
	
	List<MemberBean> findByAccountLike(String account);
	
}
