package tw.com.ispan.dao;

import java.util.List;

import tw.com.ispan.domain.OrderBean;

public interface OrderDao {
	
	public List<OrderBean> findAllByMemberId(Integer memberId);

}
