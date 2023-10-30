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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import tw.com.ispan.domain.MemberBean;
import tw.com.ispan.domain.RecipeBean;
import tw.com.ispan.repository.MemberRepository;
import tw.com.ispan.service.MemberService;
import tw.com.ispan.service.RecipeService;
import tw.com.ispan.util.DatetimeConverter;

@RestController
@CrossOrigin
@RequestMapping(path = "")
public class MemberController {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private RecipeService recipeService;

	@Autowired
	private MemberService memberService;

	@GetMapping(path = "/members/exists/{account}")
	public String exist(@PathVariable(name = "account") String account) {
		JSONObject responseJson = new JSONObject();
		if (memberRepository.findByAccount(account) != null) {
			responseJson.put("message", "該帳號名稱已有人使用");
			responseJson.put("success", false);
		} else {
			responseJson.put("message", "帳號名稱可使用");
			responseJson.put("success", true);
		}
		return responseJson.toString();
	}

	@PostMapping(path = "/members/login1")
	public String create(@RequestBody String json) {
		JSONObject requestJson = new JSONObject(json);
		System.out.println(requestJson);

		String account = requestJson.isNull("account") ? null : requestJson.getString("account");

		MemberBean member = memberRepository.findByAccount(account);

		JSONObject responseJson = new JSONObject();

		if (member != null) {

			String birthday = DatetimeConverter.toString(member.getBirthday(), "yyyy-MM-dd");
			responseJson.put("account", member.getAccount()).put("birthday", member.getBirthday()).put("memberPassword",
					member.getMemberPassword());

		}
		return responseJson.toString();

	}

	@PostMapping("/member/signup")
	public ResponseEntity<?> insert(HttpServletRequest request, @RequestBody String param) {
		MemberBean insert = memberService.create(param);
		if (insert != null) {
			return new ResponseEntity<>(insert, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(path = "/members/login")
	public ResponseEntity<?> login(HttpServletRequest request, @RequestBody String param) {
		try {
			JSONObject obj = new JSONObject(param);
			String accountInput = obj.isNull("account") ? null : obj.getString("account");
			String memberPasswordInput = obj.isNull("memberPassword") ? null : obj.getString("memberPassword");

			MemberBean member = memberService.findByAccount(accountInput);
			if (member != null && memberPasswordInput != null) {
				if (memberPasswordInput.equals(member.getMemberPassword())) {
					return new ResponseEntity<>(member, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/members/like/{account}")
	public ResponseEntity<?> findByAccountLike(@PathVariable(name = "account") String account) {
		List<MemberBean> accountsAlike = memberService.findByAccountContaining(account);
		if (accountsAlike != null) {
			return new ResponseEntity<>(accountsAlike, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/members/like&&not/{account}/{notAccount}")
	public ResponseEntity<?> findByAccountLike(@PathVariable(name = "account") String account,@PathVariable(name = "notAccount") String notAccount) {
		List<MemberBean> accountsAlike = memberService.findByAccountContainingAndAccountNot(account,notAccount);
		if (accountsAlike != null) {
			return new ResponseEntity<>(accountsAlike, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/member/update")
	public ResponseEntity<?> update(HttpServletRequest request, @RequestBody MemberBean memberBean) {
		if (memberBean != null) {
			MemberBean update = memberService.update(memberBean);
			return new ResponseEntity<>(update, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@PostMapping("/member/findById")
	public MemberBean findById(@RequestBody String json) {
		JSONObject obj = new JSONObject(json);
		Integer id = obj.isNull("id")?null:obj.getInt("id");
		
		return memberRepository.findById(id).get();
	}
	
	@GetMapping("/member/recipe/{uploaderId}")
	public ResponseEntity<?> findByUploaderId(@PathVariable(name = "uploaderId") Integer uploaderId) {
		List<Object[]> result = memberRepository.recipesWithPics(uploaderId);
		if (result != null && !result.isEmpty()) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/member/recipes/collect/{collectorNumber}")
	public ResponseEntity<?> collectedRecipesWithPics(@PathVariable(name = "collectorNumber") Integer collectorNumber) {
		List<Object[]> result = memberRepository.collectedRecipesWithPics(collectorNumber);
		if (result != null && !result.isEmpty()) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/member/modify")
	public ResponseEntity<?> modify(HttpServletRequest request, @RequestBody MemberBean memberBean) {
		MemberBean modify = memberService.update(memberBean);
		if (modify != null) {
			return new ResponseEntity<>(modify, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/allIngredient")
	public ResponseEntity<?> allIngredient(HttpServletRequest request) {
		List<Object[]> modify = memberService.allIngredient();
		if (modify != null) {
			return new ResponseEntity<>(modify, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	

}
