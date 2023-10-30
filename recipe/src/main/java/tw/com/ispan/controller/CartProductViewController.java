package tw.com.ispan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.domain.CartProductViewBean;
import tw.com.ispan.service.CartProductViewService;

@RestController
@RequestMapping(path = "")
@CrossOrigin
public class CartProductViewController {
	
	@Autowired
	private CartProductViewService cartProductViewService;
	
	@GetMapping(path = "/cartproductview/findbymembernumber/{memberNumber}")
	public ResponseEntity<?> findAllByMemberNumber(@PathVariable(name = "memberNumber")Integer memberNumber) {
		List<CartProductViewBean> result = cartProductViewService.findAllByMemberNumber(memberNumber);
		if(result!=null && !result.isEmpty()) {
			ResponseEntity<List<CartProductViewBean>> response = ResponseEntity.ok().body(result);
			return response;
		}else {
			ResponseEntity<Void> response = ResponseEntity.noContent().build();
			return response;
		}
	}

}
