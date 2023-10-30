package tw.com.ispan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import tw.com.ispan.domain.CartProductBean;
import tw.com.ispan.domain.ItemBean;
import tw.com.ispan.service.CartProductService;

@RestController
@RequestMapping(path = "")
@CrossOrigin
public class CartProductController {
	
	@Autowired
	private CartProductService cartProductService;
	
//	@GetMapping(path = "/cartproduct/findbymemberid/{memberNumber}")
//	public ResponseEntity<?> findAllByMemberNumber(@PathVariable(name = "memberNumber") Integer memberNumber) {
//		List<CartProductBean> result = cartProductService.findAllByMemberNumber(memberNumber);
//		if(result!=null && !result.isEmpty()) {
//			ResponseEntity<List<CartProductBean>> response = ResponseEntity.ok().body(result);
//			return response;
//		} else {
//			ResponseEntity<Void> response = ResponseEntity.noContent().build();
//			return response;
//		}
//	}
//	
	@DeleteMapping(path = "/cartproduct/remove/{cartProductId}")
	public ResponseEntity<?> remove(@PathVariable(name = "cartProductId")Integer cartProductId){
		boolean result = cartProductService.remove(cartProductId);
		if(result) {
			ResponseEntity<Void> response = ResponseEntity.noContent().build();
			return response;
		}else {
			ResponseEntity<Void> response = ResponseEntity.notFound().build();
			return response;
		}
	}
	
	@DeleteMapping(path = "/cartproduct/removeall/{cartProductId}")
	public ResponseEntity<?> removeAll(@PathVariable(name = "cartProductId")Integer cartProductId){
		boolean result = cartProductService.removeAll(cartProductId);
		if(result) {
			ResponseEntity<Void> response = ResponseEntity.noContent().build();
			return response;
		}else {
			ResponseEntity<Void> response = ResponseEntity.notFound().build();
			return response;
		}
	}
	
	
	
	@PutMapping(path = "/cartproduct/modify/{cartProductId}")
	public ResponseEntity<?> modify(@PathVariable(name = "cartProductId")Integer cartProductId, @RequestBody String body){
		CartProductBean result = cartProductService.modify(body);
		if(result!=null) {
			ResponseEntity<CartProductBean> response = ResponseEntity.ok().body(result);
			return response;
		}else {
			ResponseEntity<Void> response = ResponseEntity.notFound().build();
			return response;
		}
	}
	
	@PostMapping(path = "/cartproduct/create")
	public ResponseEntity<?> create(HttpServletRequest request, @RequestBody CartProductBean param) {
		CartProductBean insert = cartProductService.create(param);
		if (insert != null) {
			return new ResponseEntity<>(insert, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	
	
	
	
	
	
}