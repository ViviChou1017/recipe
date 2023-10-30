package tw.com.ispan.dao;

import java.util.List;

import tw.com.ispan.domain.CartProductViewBean;

public interface CartProductViewDao {
	
	public List<CartProductViewBean> findAllByMemberNumber(Integer memberNumber);
	

}
