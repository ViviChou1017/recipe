package tw.com.ispan.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
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
import tw.com.ispan.domain.CookingMethodBean;
import tw.com.ispan.domain.MemberBean;
import tw.com.ispan.domain.RecipeBean;
import tw.com.ispan.repository.MemberRepository;
import tw.com.ispan.service.CookingMethodService;
import tw.com.ispan.service.MemberService;
import tw.com.ispan.service.RecipeService;
import tw.com.ispan.util.DatetimeConverter;

@RestController
@CrossOrigin
@RequestMapping(path = "")
public class CookingMethodController {

	@Autowired
	private CookingMethodService  cookingMethodService;
	
	



	@GetMapping("/cookingMethods")
	public ResponseEntity<?> findAll() {
		List<CookingMethodBean> result = cookingMethodService.findAll();
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/cookingMethods/{methodName}")
	public ResponseEntity<?> findByAccountLike(@PathVariable(name = "methodName") String methodName) {
		List<CookingMethodBean> result = cookingMethodService.findByMethodNameContaining(methodName);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	

	
	

}
