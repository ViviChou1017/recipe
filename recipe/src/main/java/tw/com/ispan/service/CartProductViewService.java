package tw.com.ispan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.domain.CartProductViewBean;
import tw.com.ispan.repository.CartProductViewRepository;

@Service
@Transactional
public class CartProductViewService {
	
	@Autowired
	private CartProductViewRepository cartProductViewRepository;
	
	public List<CartProductViewBean> findAllByMemberNumber(Integer number) {
		try {
			return cartProductViewRepository.findAllByMemberNumber(number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public boolean deleteByItemId(Integer number) {
		
		if(number!=null) {
			cartProductViewRepository.deleteById(number);
			return true;
		}
		return false;
		
	}
	
	

}
