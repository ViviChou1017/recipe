package tw.com.ispan.dao;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.domain.CartProductViewBean;
import tw.com.ispan.domain.OrderBean;
import tw.com.ispan.repository.CartProductRepository;
import tw.com.ispan.repository.CartProductViewRepository;
import tw.com.ispan.repository.OrderRepository;
import tw.com.ispan.service.CartProductService;
import tw.com.ispan.service.OrderService;

//@SpringBootTest

public class CartProductViewDaoImplTest {

	@Autowired
	private CartProductViewRepository cartProductViewRepository;
	
	@Autowired
	private	CartProductRepository cartProductRepository;
	
	@Autowired
	private CartProductService cartProductService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
//	@Test
//	public void testCreate() {
//		
//		JSONObject json = new JSONObject();
//		json.put("note", "sss");
//		put("id", 1).
//		OrderBean create2 = orderService.create);
//		System.out.println(create2.toString());
//	}
	
//	@Test
	public void testFindAll() {

		List<CartProductViewBean> findAll = cartProductViewRepository.findAllByMemberNumber(1);
		System.out.println(findAll);
	}
	
//	@Test
	public void testFindAllBy() {

		List<OrderBean> findAll = orderRepository.findByMemberId(1005);
		System.out.println(findAll);
	}
	
//	@Test
	public void testDeleteByItemId() {

		cartProductRepository.deleteById(4);
		List<CartProductViewBean> findAll = cartProductViewRepository.findAllByMemberNumber(1);
		System.out.println(findAll);
	}
	
//	@Test
	public void testRemove() {

		boolean remove = cartProductService.remove(3004);
		List<CartProductViewBean> findAll = cartProductViewRepository.findAllByMemberNumber(1);
		System.out.println(findAll);
		System.out.println(remove);
	}

}
