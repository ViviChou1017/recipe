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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import tw.com.ispan.domain.ItemBean;
import tw.com.ispan.service.ItemService;

@RestController
@RequestMapping
@CrossOrigin
public class ItemController {
	
	@Autowired
	public ItemService itemService;
	
	@PostMapping(path = "/item/create")
	public ResponseEntity<?> create(HttpServletRequest request, @RequestBody ItemBean param) {
		System.out.println("/item/create");
		ItemBean insert = itemService.create(param);
		if (insert != null) {
			return new ResponseEntity<>(insert, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(path = "/item/findall")
	public ResponseEntity<?> findAll() {
		List<ItemBean> result = itemService.findAll();
		if(result!=null && !result.isEmpty()) {
			ResponseEntity<List<ItemBean>> response = ResponseEntity.ok().body(result);
			return response;
		} else {
			ResponseEntity<Void> response = ResponseEntity.noContent().build();
			return response;
		}
	}
	
	@DeleteMapping(path = "/item/remove/{kitchenwareId}/{courseId}")
	public ResponseEntity<?> remove(@PathVariable(name = "kitchenwareId")Integer kitchenwareId, @PathVariable(name = "courseId")Integer courseId){
		boolean result = itemService.remove(kitchenwareId, courseId);
		if(result) {
			ResponseEntity<Void> response = ResponseEntity.noContent().build();
			return response;
		}else {
			ResponseEntity<Void> response = ResponseEntity.notFound().build();
			return response;
		}
	}
	
	

}
