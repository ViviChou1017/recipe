package tw.com.ispan.dao;

import java.util.List;

import tw.com.ispan.domain.CartProductBean;

public interface CartProductDao {
	
	public List<CartProductBean> findAllByMemberNumber(Integer number);

}
