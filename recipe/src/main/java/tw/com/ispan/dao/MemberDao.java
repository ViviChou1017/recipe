package tw.com.ispan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tw.com.ispan.domain.MemberBean;


public interface MemberDao {

	List<Object[]> recipesWithPics(Integer uploaderId);
	
	List<Object[]> collectedRecipesWithPics(Integer collectorNumber);
	
	public List<Object[]> allIngredient();
	
//	MemberBean findByAccount(String account);
//	
//	List<MemberBean> findByAccountLike(String account);
}
