 package tw.com.ispan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
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

import tw.com.ispan.domain.IngredientBean;
import tw.com.ispan.domain.IngredientImageBean;
import tw.com.ispan.service.IngredientImageService;
import tw.com.ispan.service.IngredientService;
import tw.com.ispan.vo.IngredientsVo;

@RestController
@CrossOrigin
@RequestMapping("/recipe")
public class IngredientControllerImpl implements IngredientController {
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private IngredientImageService ingredientImageService;
	


//查詢全部
	@Override
	@GetMapping("/findIngredientAll")
	public ResponseEntity<?> findAll() {
		List<IngredientBean> ingredientList = ingredientService.findAll();
		ResponseEntity<List<IngredientBean>> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(ingredientList);
		return response;
	}

	// 查詢名字
	@Override
	@GetMapping("/findByIngredientName/{name}")
	public List<IngredientBean> findByIngredientName(@PathVariable(name = "name") String name) {
		List<IngredientBean> ingredient = ingredientService.findByIngredientName(name);
		if (ingredient != null) {
			ResponseEntity<List<IngredientBean>> response = ResponseEntity.ok().body(ingredient);
			return ingredient;
		} else {
			ResponseEntity<Void> response = ResponseEntity.notFound().build();
			return ingredient;
		}

	}

//刪除
	@Override
	@DeleteMapping("/delInteger/{id}")
	public ResponseEntity<?> delInteger(@PathVariable Integer id) {
		boolean delInteger = ingredientService.delInteger(id);
		if (delInteger) {
			ResponseEntity<Void> response = ResponseEntity.noContent().build();
			return response;
		} else {
			ResponseEntity<Void> response = ResponseEntity.notFound().build();
			return response;
		}
	}

//新增
	@Override
	@PostMapping("/addInteger")
	public IngredientBean addInteger(@RequestBody IngredientBean bean) {
		System.out.println(bean);
		return ingredientService.addInteger(bean);

	}

//修改
	@Override
	@PutMapping("/updateInteger/{id}")
	public ResponseEntity<?> updateInteger(@PathVariable Integer id, @RequestBody IngredientBean bean) {
		String ingredientName = bean.getIngredientName();
		IngredientBean result = ingredientService.updateInteger(bean);
		if (result != null) {
			ResponseEntity<IngredientBean> response = ResponseEntity.ok().body(result);
			return response;
		} else {
			ResponseEntity<Void> response = ResponseEntity.notFound().build();
			return response;
		}
	}

//查分類id
	@Override
	@GetMapping("/findByclassificationId/{id}")
	public List<IngredientBean> findByclassificationId(@PathVariable Integer id) {
		List<IngredientBean> ingredient = ingredientService.findByclassificationId(id);
		if (ingredient != null) {
			ResponseEntity<List<IngredientBean>> response = ResponseEntity.ok().body(ingredient);
			return ingredient;
		} else {
			ResponseEntity<List<IngredientBean>> response = ResponseEntity.notFound().build();
			return ingredient;
		}

	}

	@Override
	@GetMapping("/getIngredient/{id}")
	public IngredientsVo getIngredient(@PathVariable Integer id) {

		System.out.println(id);
		IngredientBean ingredient = ingredientService.findByIngredientId(id);
		// ?
//		IngredientRecipeBean  IngredientRecipe =ingredientRecipeService.findByIngredientRecipeId(id);
		IngredientImageBean ingredientImage = ingredientImageService.findByIngredientImageId(id);
		IngredientsVo vo = new IngredientsVo();
		vo.setIngredientImage(ingredientImage.getIngredientImage());
		vo.setIngredientName(ingredient.getIngredientName());
		vo.setCalorie(ingredient.getCalorie());
		vo.setTips(ingredient.getTips());
		//vo.setIngredientWeight(IngredientRecipe.getIngredientWeight());
		// System.out.println(vo);

		return vo;
	}

//查食材id
	@Override
	@GetMapping("/findByIngredientId/{id}")
	public ResponseEntity<?> findByIngredientId(@PathVariable Integer id) {
		IngredientBean ingredient = ingredientService.findByIngredientId(id);
		if (ingredient != null) {
			ResponseEntity<IngredientBean> response = ResponseEntity.ok().body(ingredient);
			return response;
		} else {
			ResponseEntity<IngredientBean> response = ResponseEntity.notFound().build();
			return response;
		}

	}


}
