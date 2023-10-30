package tw.com.ispan.service;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.domain.RecipePicBean;

//@SpringBootTest
public class RecipePicServiceTest {

	@Autowired
	private RecipePicService recipePicService;

//	@Test
	public void testInsertRecipePic() {
		JSONObject json = new JSONObject();
		json.put("id", 5).put("recipeId", 2).put("picBase64", "C:\\Users\\User\\Desktop\\食譜圖片\\非64菜圃卵.jpg");
		
		RecipePicBean pic = recipePicService.insertRecipePic(json);
		System.out.println(pic.toString());
	}
}
