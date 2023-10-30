package tw.com.ispan.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.domain.CartProductBean;
import tw.com.ispan.domain.CartProductViewBean;
import tw.com.ispan.repository.CartProductRepository;
import tw.com.ispan.repository.CartProductViewRepository;

//@SpringBootTest
public class CartProductDaoImplTests {
	
	@Autowired
	private CartProductRepository cartProductRepository;
	
	
	
//	@Test
	public void testFindAll() {
		
//		List<CartProductBean> findAll = cartProductRepository.findAllByMemberNumber(1);
		
//		System.out.println(findAll);
	}

}
