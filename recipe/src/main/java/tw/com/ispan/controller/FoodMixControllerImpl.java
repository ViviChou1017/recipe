package tw.com.ispan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.domain.FoodMixBean;
import tw.com.ispan.service.FoodMixService;
import tw.com.ispan.vo.FoodMixVo;
@RestController
@CrossOrigin()
@RequestMapping("/recipe")
public class FoodMixControllerImpl implements FoodMixController {

	@Autowired
	private FoodMixService foodMixService;

	//新增
	@Override
	@PostMapping("/addFoodMix")
	public boolean addFoodMix(@RequestBody FoodMixBean bean) {
	boolean result = foodMixService.addFoodMix(bean);
	return result;
	}
	//新增 多筆
	@Override
	@PostMapping("/addFoodMixs")
	public boolean addFoodMix(@RequestBody List<FoodMixBean> beans) {
	boolean result = foodMixService.addFoodMix(beans);
	return result;
	}
	
	//查詢食譜id
	@Override
	@GetMapping("/findFoodByRecipeId/{id}")
	public List<FoodMixBean> findFoodByRecipeId(@PathVariable Integer id) {
		List<FoodMixBean> recipeid = foodMixService.findFoodByRecipeId(id);
		if (recipeid != null) {
			ResponseEntity<List<FoodMixBean>> response = ResponseEntity.ok().body(recipeid);
			return recipeid;
		} else {
			ResponseEntity<List<FoodMixBean>> response = ResponseEntity.notFound().build();
			return recipeid;
		}

	}
	//查食譜and分類id
	@Override
	@PostMapping("/findFoodByRecipeIdAndClassificationById")
	public List<FoodMixBean> findFoodByRecipeIdAndClassificationById(@RequestBody FoodMixVo vo) {
		if(null == vo.getRecipeId() || null == vo.getClassificationId()) {
			return null;
		}
		List<FoodMixBean> recipeid = foodMixService.findFoodByRecipeIdAndClassificationById(vo.getRecipeId(), vo.getClassificationId());
		if (recipeid != null) {
			ResponseEntity<List<FoodMixBean>> response = ResponseEntity.ok().body(recipeid);
			return recipeid;
		} else {
			ResponseEntity<List<FoodMixBean>> response = ResponseEntity.notFound().build();
			return recipeid;
		}
	}
	// 查食譜and分類and食材id
	@Override
	@PostMapping("/findFoodByRecipeIdAndClassificationAndIngredientById")
	public List<FoodMixBean> findFoodByRecipeIdAndClassificationAndIngredientById(@RequestBody FoodMixVo vo) {
		if(null == vo.getRecipeId() || null == vo.getClassificationId()||null == vo.getIngredientId()) {
			return null;
		}
		List<FoodMixBean> recipeid = foodMixService.findFoodByRecipeIdAndClassificationAndIngredientById(vo.getRecipeId(), vo.getClassificationId(),vo.getIngredientId());
		if (recipeid != null) {
			ResponseEntity<List<FoodMixBean>> response = ResponseEntity.ok().body(recipeid);
			return recipeid;
		} else {
			ResponseEntity<List<FoodMixBean>> response = ResponseEntity.notFound().build();
			return recipeid;
		}
	}
	// 查料理法id
	@Override
	@GetMapping("/findFoodByCookMethodById/{id}")
	public List<FoodMixBean> findFoodByCookMethodById(@PathVariable Integer id) {
		List<FoodMixBean> recipeid = foodMixService.findFoodByCookMethodById(id);
		if (recipeid != null) {
			ResponseEntity<List<FoodMixBean>> response = ResponseEntity.ok().body(recipeid);
			return recipeid;
		} else {
			ResponseEntity<List<FoodMixBean>> response = ResponseEntity.notFound().build();
			return recipeid;
		}

	}
	// 查食譜and分類and食材idand料理法id
	@Override
	@PostMapping("/findFoodByRecipeIdAndClassificationAndIngredientAndFoodByCookMethodById")
	public  FoodMixBean findFoodByRecipeIdAndClassificationAndIngredientAndFoodByCookMethodById(@RequestBody FoodMixVo vo) {
		if(null == vo.getRecipeId() || null == vo.getClassificationId()||null == vo.getIngredientId()||null==vo.getCookeryId()) {
			return null;
		}
		FoodMixBean recipeid = foodMixService.findFoodByRecipeIdAndClassificationAndIngredientAndFoodByCookMethodById(vo.getRecipeId(),vo.getClassificationId(),vo.getIngredientId(),vo.getCookeryId());
		if (recipeid != null) {
			ResponseEntity<FoodMixBean> response = ResponseEntity.ok().body(recipeid);
			return recipeid;
		} else {
			ResponseEntity<FoodMixBean> response = ResponseEntity.notFound().build();
			return recipeid;
		}

	}
	// 更新
	@Override
	@PutMapping("/updateFoodMix")
	public boolean upDate(@RequestBody FoodMixVo vo) {
		// TODO Auto-generated method stub
		boolean upDate = foodMixService.upDate(vo);
		//recipeId ingredientId 
		return upDate;
	}
	//查詢食材id
	@Override
	@GetMapping("/findFoodByIngredientById/{id}")
	public List<FoodMixBean> findFoodByIngredientById(@PathVariable Integer id) {
		List<FoodMixBean> recipeid = foodMixService.findFoodByIngredientId(id);
		if (recipeid != null) {
			ResponseEntity<List<FoodMixBean>> response = ResponseEntity.ok().body(recipeid);
			return recipeid;
		} else {
			ResponseEntity<List<FoodMixBean>> response = ResponseEntity.notFound().build();
			return recipeid;
		}

	}

	

}
