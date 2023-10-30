package tw.com.ispan.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.micrometer.common.util.StringUtils;
import tw.com.ispan.domain.IngredientImageBean;
import tw.com.ispan.service.IngredientImageService;
import tw.com.ispan.util.ImgAndBasc64;

@RestController
@RequestMapping("/recipe")
public class IngredientImageControllerImpl implements IngredientImageController {
	@Autowired
	private IngredientImageService ingredientImageService;

//查全部
	@Override
	@GetMapping("/findByIngredientImageAll")
	public ResponseEntity<?> findByIngredientImageAll() {
		List<IngredientImageBean> ingredientList = ingredientImageService.findByIngredientImageAll();
		ResponseEntity<List<IngredientImageBean>> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(ingredientList);
		return response;
	}

//刪除
	@Override
	@DeleteMapping("/delIntegers/{id}")
	public ResponseEntity<?> delInteger(@PathVariable Integer id) {
		boolean delInteger = ingredientImageService.delInteger(id);
		if (delInteger) {
			ResponseEntity<IngredientImageBean> response = ResponseEntity.noContent().build();
			return response;
		} else {
			ResponseEntity<IngredientImageBean> response = ResponseEntity.notFound().build();
			return response;
		}
	}

//新增
	@Override
	@PostMapping("/addIntegersimage")
	// public IngredientImageBean addInteger(@RequestBody IngredientImageBean bean)
	// {
	public IngredientImageBean addInteger(@RequestParam("file") MultipartFile file, @RequestParam String ingredientId) {
		System.out.println(file);
		IngredientImageBean bean = new IngredientImageBean();
		try {

			System.out.println("file.getBytes() = " + file.getInputStream());
			String image = ImgAndBasc64.enCodeToBase64(file.getInputStream());
			// Base64.encodeBase64String(file.getBytes());
			System.out.println(image);
			bean.setIngredientImage(image);
			if (StringUtils.isNotBlank(ingredientId)) {
				bean.setIngredientId(Integer.parseInt(ingredientId));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 建立檔案輸入串流

		IngredientImageBean addInteger = ingredientImageService.addInteger(bean);
//		byte[] deCodeByte = ImgAndBasc64.deCodeByte(addInteger.getIngredientImage());
//		addInteger.setIngredientImage(deCodeByte.toString());

		return addInteger;
	}

	@Override
	public IngredientImageBean addInteger(@RequestBody IngredientImageBean bean) {

		if (StringUtils.isNotBlank(bean.getIngredientImage())) {
			String image = ImgAndBasc64.enCodeToBase64(bean.getIngredientImage());
			// Base64.encodeBase64String(file.getBytes());
			bean.setIngredientImage(image);
			return ingredientImageService.addInteger(bean);
		} else {
			return null;
		}
	}

//修改
	@Override
	@PutMapping("/updateIntegers/{id}")
	public ResponseEntity<?> updateInteger(@PathVariable Integer id, @RequestBody IngredientImageBean bean) {
		String IngredientImage = bean.getIngredientImage();
		IngredientImageBean result = ingredientImageService.updateInteger(bean);
		if (result != null) {
			ResponseEntity<IngredientImageBean> response = ResponseEntity.ok().body(result);
			return response;
		} else {
			ResponseEntity<IngredientImageBean> response = ResponseEntity.notFound().build();
			return response;
		}
	}

//查食材id
	@Override
	@GetMapping("/findByIngredientImageId/{id}")
	public ResponseEntity<?> findByIngredientImageId(@PathVariable Integer id) {
		IngredientImageBean ingredient = ingredientImageService.findByIngredientImageId(id);
		if (ingredient != null) {
			ResponseEntity<IngredientImageBean> response = ResponseEntity.ok().body(ingredient);
			return response;
		} else {
			ResponseEntity<IngredientImageBean> response = ResponseEntity.notFound().build();
			return response;
		}
	}

	@Override
	@GetMapping("/findByImageId/{id}")
	public ResponseEntity<?> findByImageId(@PathVariable Integer id) {
		IngredientImageBean ingredient = ingredientImageService.findByImageId(id);
		if (ingredient != null) {
			ResponseEntity<IngredientImageBean> response = ResponseEntity.ok().body(ingredient);
			return response;
		} else {
			ResponseEntity<IngredientImageBean> response = ResponseEntity.notFound().build();
			return response;
		}

	}
}
