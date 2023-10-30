package tw.com.ispan.controller;

import java.net.URI;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import tw.com.ispan.domain.CartProductViewBean;
import tw.com.ispan.domain.OrderBean;
import tw.com.ispan.repository.OrderRepository;
import tw.com.ispan.service.OrderService;

@RestController
@RequestMapping
@CrossOrigin
public class OrderController {

	@Autowired
	public OrderRepository orderRepository;

	@Autowired
	private OrderService orderService;

	@PostMapping(path = "/order/create")
	public ResponseEntity<?> create(HttpServletRequest request, @RequestBody OrderBean param) {
		OrderBean insert = orderService.create(param);
		if (insert != null) {
			return new ResponseEntity<>(insert, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(path = "/order/findallbymemberid/{memberId}")
	public ResponseEntity<?> findAllByMemberId(@PathVariable(name = "memberId")Integer memberId) {
		List<OrderBean> result = orderService.findAllByMemberId(memberId);
		if(result!=null && !result.isEmpty()) {
			ResponseEntity<List<OrderBean>> response = ResponseEntity.ok().body(result);
			return response;
		}else {
			ResponseEntity<Void> response = ResponseEntity.noContent().build();
			return response;
		}
	}
	
	
	
}
