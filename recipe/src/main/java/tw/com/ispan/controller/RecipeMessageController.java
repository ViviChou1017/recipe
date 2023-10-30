package tw.com.ispan.controller;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import tw.com.ispan.domain.RecipeMessageBean;
import tw.com.ispan.repository.RecipeMessageRepository;
import tw.com.ispan.service.RecipeMessageService;

@RestController
@RequestMapping("/recipeMessage")
@CrossOrigin
public class RecipeMessageController {
	
	@Autowired
	private RecipeMessageService recipeMessageService;
	
	@Autowired
	private RecipeMessageRepository recipeMessageRepository;
	
//	@PostMapping("/createMessage")	//																	自動填充資訊物件、存取詳細資訊
//	public ResponseEntity<RecipeMessageBean> createMessage(@RequestParam("content") String content, @AuthenticationPrincipal UserDetails currentUser) {
//		if (currentUser != null) {
//            // 已登入的用戶，可以執行留言操作
//            // 創建留言的邏輯
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            // 未登入的用戶，無法執行留言操作
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//	}
//	
//	@GetMapping("/checkLogin")
//    public ResponseEntity<String> checkLogin(HttpServletRequest request) {
//        // 從Cookie中獲取用戶信息               驗證
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            // 用戶已登入，可以執行留言操作
//            return new ResponseEntity<>("User is logged in", HttpStatus.OK);
//        } else {
//            // 用戶未登入，無法執行留言操作
//            return new ResponseEntity<>("User is not logged in", HttpStatus.UNAUTHORIZED);
//        }
//    }
	
	@PostMapping("/createMessage")
	public RecipeMessageBean createMessage(@RequestBody RecipeMessageBean message) {
		if(message!=null) {
			
			Integer recipeId = message.getRecipeId();
			Integer id = message.getId();
			
			if(recipeMessageRepository.findByRecipeIdAndId(recipeId, id)!=null) {
				return null;
				}else {
					RecipeMessageBean result = recipeMessageService.createMessage(message);
				}
			}
		return message;
			
	}
	
	
	
	
	
	@PutMapping("/updateMessage/{messageId}")
    public ResponseEntity<RecipeMessageBean> updateMessage(@PathVariable Integer messageId, @RequestBody String json) {
		JSONObject obj = new JSONObject(json);
		String newContent = obj.isNull("newContent")?null:obj.getString("newContent");
		RecipeMessageBean updatedMessage = recipeMessageService.updateMessage(messageId, newContent);
        if (updatedMessage != null) {
            return new ResponseEntity<>(updatedMessage, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }
	

    @PostMapping("/deleteMessage")
    public void deleteMessage(@RequestBody String json) {
    	JSONObject obj = new JSONObject(json);
    	Integer messageId = obj.isNull("messageId")?null:obj.getInt("messageId");
    	recipeMessageService.deleteMessage(messageId);
    }
	
    @GetMapping("/findAllMessage")
	public List<RecipeMessageBean> findAll() {
		List<RecipeMessageBean> Message = recipeMessageService.findAll();
		return Message;
	}
    
    @PostMapping("/findMessageByRecipeId")
    public List<RecipeMessageBean> findByRecipeId(@RequestBody String json){
    	JSONObject obj = new JSONObject(json);
    	Integer recipeId = obj.isNull("recipeId")? null:obj.getInt("recipeId");
    	
    	return recipeMessageRepository.findByRecipeId(recipeId);
    }
    
    @GetMapping("/findMessageByMessageId/{MessageId}")
    public RecipeMessageBean findByMessageId(@PathVariable Integer MessageId) {
    return 	recipeMessageRepository.findById(MessageId).get();
    }
    
    
}
