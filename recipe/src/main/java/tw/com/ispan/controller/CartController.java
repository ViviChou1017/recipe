package tw.com.ispan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import tw.com.ispan.domain.CartBean;
import tw.com.ispan.domain.ItemBean;
import tw.com.ispan.service.CartService;

@RestController
@RequestMapping
@CrossOrigin
public class CartController {
	
	@Autowired
	public CartService cartService;
	
	@PostMapping(path = "/cart/create")
	public ResponseEntity<?> create(HttpServletRequest request, @RequestBody CartBean param) {
		CartBean insert = cartService.create(param);
		if (insert != null) {
			return new ResponseEntity<>(insert, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(path = "/cart/findall")
	public ResponseEntity<?> findAll() {
		List<CartBean> result = cartService.findAll();
		if(result!=null && !result.isEmpty()) {
			ResponseEntity<List<CartBean>> response = ResponseEntity.ok().body(result);
			return response;
		} else {
			ResponseEntity<Void> response = ResponseEntity.noContent().build();
			return response;
		}
	}

}
