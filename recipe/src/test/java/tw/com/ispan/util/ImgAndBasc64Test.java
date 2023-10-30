package tw.com.ispan.util;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class ImgAndBasc64Test {

	@Autowired
	private ImgAndBasc64 imgAndBasc64;
	
//	@Test
	public void testChangeToBase64() {
		//File file = new File("C:\\Users\\芋圓\\Desktop\\123.jpg");
		
		
		
		
		String ans = imgAndBasc64.enCodeToBase64("C:\\Users\\芋圓\\Desktop\\123.jpg");
		System.out.println(ans);
	}
}
