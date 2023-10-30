package tw.com.ispan.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.domain.KitchenwareBean;

//@SpringBootTest
public class kitchenwareServiceTest {

	@Autowired
	private kitchenwareService kitchenwareService;
	
	
//	@Test
	public void testFindById() {
		KitchenwareBean result = kitchenwareService.findById(1);
		System.out.println(result.toString());
	}
	
	
	
	//@Test
	public void testFindByName() {
		List<KitchenwareBean> result = kitchenwareService.findByName("菜刀");
		System.out.println(result.toString());
	}
}
