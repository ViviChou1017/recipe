package tw.com.ispan.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.domain.RecipeMessageBean;
import tw.com.ispan.repository.MemberRepository;
import tw.com.ispan.repository.RecipeMessageRepository;

@Service
@Transactional
public class RecipeMessageService {

	@Autowired
	private RecipeMessageRepository recipeMessageRepository;
	
//	@Autowired
//	private MemberRepository memberRepository;

//	驗證登入狀態新增留言未測試
//	public RecipeMessageBean createMessage(RecipeMessageBean message,Integer memberNumber) {
//		User user = memberRepository.findById(memberNumber).orElse(null);
//		if (user != null) {
//            message.setUser(user);
//            message.setMessageDatetime(LocalDateTime.now());
//            return recipeMessageRepository.save(message);
//		}
//		return null;
//	}
	
	//不驗證登入狀態新增留言
	public RecipeMessageBean createMessage(RecipeMessageBean message) {
		try {
			message.setMessageDatetime(LocalDateTime.now());
			return recipeMessageRepository.save(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//使用留言者id找留言內容更新
	public RecipeMessageBean updateMessage(Integer messageId, String newContent) {
		Optional<RecipeMessageBean> optionalMessage = recipeMessageRepository.findById(messageId);
		if (optionalMessage.isPresent()) {
			RecipeMessageBean existingMessage = optionalMessage.get();
			existingMessage.setContent(newContent);
			existingMessage.setMessageDatetime(LocalDateTime.now());
			return recipeMessageRepository.save(existingMessage);

		}
		return null;
	}
	
	//刪除
	public void deleteMessage(Integer recipeMessageId) {
		recipeMessageRepository.deleteById(recipeMessageId);
	}

	//查全部
	public List<RecipeMessageBean> findAll() {
		try {
			return recipeMessageRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
