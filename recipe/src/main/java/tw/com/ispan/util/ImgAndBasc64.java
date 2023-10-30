package tw.com.ispan.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class ImgAndBasc64 {

	public static String enCodeToBase64(InputStream inputfilePath) {
		try {
			if (inputfilePath != null) {
				// 將檔案轉為 byte array
				String encodedString = Base64.getEncoder().encodeToString(inputfilePath.readAllBytes());
				// 再將Byte array 轉成 base64格式，再轉為字串
				return encodedString;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	// 只要給檔案，如果要在網路上直接顯示base64圖檔就是在<img
	// src=">data:image/png;base64,+{base64encoding}">即可
	public static String enCodeToBase64(String inputfilePath) {
		byte[] fileContent;
		try {
			if (inputfilePath != null) {
				// 將檔案轉為 byte array
				fileContent = FileUtils.readFileToByteArray(new File(inputfilePath));
				// 再將Byte array 轉成 base64格式，再轉為字串
				String encodedString = Base64.getEncoder().encodeToString(fileContent);
				return encodedString;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 要轉為byte[]的base64編碼
	public static byte[] deCodeByte(String encodedString) {

		// 將Base64字串解碼後轉為byte array
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		return decodedBytes;
		// 如果有需要把轉換好的buty[]轉入某個檔案中，再多寫下面一行即可。
		// 將byte array 寫入檔案中
		// FileUtils.writeByteArrayToFile(new File(outputFilePath), decodedBytes);
	}
}