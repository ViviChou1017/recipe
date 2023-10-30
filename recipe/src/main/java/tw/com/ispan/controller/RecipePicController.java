package tw.com.ispan.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.micrometer.common.util.StringUtils;
import tw.com.ispan.domain.RecipePicBean;
import tw.com.ispan.repository.RecipePicRepository;
import tw.com.ispan.service.RecipePicService;

@RestController
@RequestMapping("/recipePic")
@CrossOrigin
public class RecipePicController {

	@Autowired
	private RecipePicService recipePicService;

	@Autowired
	private RecipePicRepository recipePicRepository;

	// 舊的
//	@PostMapping("/insertRecipePic")
//	public RecipePicBean insertRecipePic(@RequestBody String json) {
//		JSONObject pic = new JSONObject(json);
//		return recipePicService.insertRecipePic(pic);
//	}

	//新的
	@PostMapping("/insertRecipePic")
	public RecipePicBean insertRecipePic(@RequestParam("recipeId") String recipeId,
			@RequestParam("file") MultipartFile file) {
		try {
			// 處理文件，例如保存到磁盤或轉換為 Base64
			byte[] picBytes;
			picBytes = file.getBytes();
			String picBase64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(picBytes);

			RecipePicBean img = new RecipePicBean();
			
			if (StringUtils.isNotBlank(recipeId)) {
				img.setRecipeId(Integer.parseInt(recipeId));
			}
			
//			img.setRecipeId(recipeId);
			img.setPicBase64(picBase64);

			return recipePicRepository.save(img);
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@PutMapping("/updateRecipePic")
	public ResponseEntity<RecipePicBean> updateRecipePic(@RequestBody String json) {
		JSONObject obj = new JSONObject(json);
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		String newPicBase64 = obj.isNull("picBase64") ? null : obj.getString("picBase64");

		RecipePicBean recipePic = recipePicService.updateRecipePic(id, newPicBase64);
		if (recipePic != null) {
			return new ResponseEntity<>(recipePic, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/findAllRecipePic")
	public List<RecipePicBean> findAll() {
		List<RecipePicBean> recipePic = recipePicService.findAll();
		return recipePic;
	}

	@PostMapping("/findByRecipePic")
	public List<RecipePicBean> findByRecipeId(@RequestBody String json) {
		JSONObject obj = new JSONObject(json);
		Integer recipeId = obj.isNull("recipeId") ? null : obj.getInt("recipeId");
		List<RecipePicBean> result = recipePicService.findByRecipeId(recipeId);
		return result;
	}
}
