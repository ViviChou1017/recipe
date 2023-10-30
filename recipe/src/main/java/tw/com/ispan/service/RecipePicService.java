package tw.com.ispan.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.domain.RecipePicBean;
import tw.com.ispan.repository.RecipePicRepository;
import tw.com.ispan.util.ImgAndBasc64;

@Service
@Transactional
public class RecipePicService {

	@Autowired
	private RecipePicRepository recipePicRepository;
	
	public RecipePicBean insertRecipePic(JSONObject obj) {
		//前端傳來json格式
//		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		Integer recipeId = obj.isNull("recipeId") ? null : obj.getInt("recipeId");
		String picBase64 = obj.isNull("picBase64") ? null : obj.getString("picBase64");
		
		//json轉basc64
		String pic = ImgAndBasc64.enCodeToBase64(picBase64);
		//img放轉好的basc64
		RecipePicBean img = new RecipePicBean();
//		img.setId(id);
		img.setRecipeId(recipeId);
		img.setPicBase64(pic);
		//存進資料庫
		return recipePicRepository.save(img);
	}
	
	//更新圖片
	public RecipePicBean updateRecipePic(Integer id,String newPicBase64) {
		String pic = ImgAndBasc64.enCodeToBase64(newPicBase64);
		//檢查是否為空值
		Optional<RecipePicBean> optionalRecipePic = recipePicRepository.findById(id);
		if(optionalRecipePic.isPresent()) {
			RecipePicBean existingRecipePic = optionalRecipePic.get();
			existingRecipePic.setPicBase64(pic);
			return recipePicRepository.save(existingRecipePic);
		}
		return null;
	}
	public List<RecipePicBean> findAll(){
		try {
			return recipePicRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//用食譜id找圖片
	public List<RecipePicBean>  findByRecipeId(Integer recipeId){
		return recipePicRepository.findByRecipeId(recipeId);
	}
	
}
