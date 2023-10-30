package tw.com.ispan.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.domain.ClassificationBean;
import tw.com.ispan.domain.FoodMixBean;
import tw.com.ispan.domain.IngredientBean;
import tw.com.ispan.domain.IngredientImageBean;
import tw.com.ispan.domain.RecipeBean;
import tw.com.ispan.service.ClassificationService;
import tw.com.ispan.service.FoodMixService;
import tw.com.ispan.service.IngredientImageService;
import tw.com.ispan.service.IngredientService;
import tw.com.ispan.service.RecipeService;
import tw.com.ispan.vo.FoodMixVo;
import tw.com.ispan.vo.IngredientsVo;

@RestController
@CrossOrigin()
@RequestMapping("/recipe")
public class SearchController {

	@Autowired
	private FoodMixService foodMixService;
	
	@Autowired
	private ClassificationService classificationService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private IngredientImageService ingredientImageService;
	
	
	@Autowired
	private RecipeService recipeService;

		
	@GetMapping("/getRecipe/{id}")
	public List<RecipeBean>getRecipe(@PathVariable(name = "id") Integer iId){
		List<FoodMixBean>foodbeans=foodMixService.findFoodByIngredientId(iId);
		
		  List<RecipeBean> recipeBeans = new ArrayList<>();
		 
          for (FoodMixBean foodbean : foodbeans) {		          
        	  RecipeBean rb = recipeService.findByRecipeId(foodbean.getFoodMixId().getRecipeId());
        	  if(null != rb) {
        		  recipeBeans.add(rb);
        	  }
          }
		  return recipeBeans;
	}
	
	
	
	
	
	
	
	//取得分類
	@GetMapping("/getClassificaton/{id}")
	public List<ClassificationBean> getClassificaton(@PathVariable(name = "id") Integer rId){
		List<FoodMixBean> foodbeans = foodMixService.findFoodByRecipeId(rId);
		//去重覆的 cId
		Set cidSet = new HashSet();
		for(FoodMixBean bean : foodbeans) {
			cidSet.add(bean.getFoodMixId().getClassificationId());
		}
		
		//取得分類
		List<Integer> cArray = new ArrayList<Integer>(cidSet);
		List<ClassificationBean> cList = classificationService.findClassificationByIds(cArray);
		return cList;
	}
	
	//取得食材
	@PostMapping("/getIngredient")
	public List<IngredientBean> getIngredient(@RequestBody FoodMixVo vo){
		Integer rId= vo.getRecipeId();
		Integer cId = vo.getClassificationId();
		List<FoodMixBean> foodbeans = foodMixService.findFoodByRecipeIdAndClassificationById(rId, cId);
		
		List<Integer> iArray = new ArrayList<Integer>();
		for(FoodMixBean bean : foodbeans) {
			iArray.add(bean.getFoodMixId().getIngredientId());
		}
		List<IngredientBean> beans = ingredientService.findByIngredientId(iArray);
		return beans;
	}
	
	//取得要顯示的食材
	@GetMapping("/getDisplayIngredient/{id}")
	public IngredientsVo getDisplayIngredient(@PathVariable(name = "id") Integer iId) {
		
		IngredientsVo ingredientsVo = new IngredientsVo();
		
		Integer weight = 1;
		
		
//		Integer rId = vo.getRecipeId();
//		Integer cId = vo.getClassificationId();
//		Integer iId = vo.getIngredientId();
//		
//		List<FoodMixBean> foodMixBeans = foodMixService.findFoodByRecipeIdAndClassificationAndIngredientById(rId, cId, iId);
//		
//		//取份量
//		if(null != foodMixBeans && foodMixBeans.size() == 1) {
//			FoodMixBean foodMixBean = foodMixBeans.get(0);
//			weight = foodMixBean.getIngredientWeight();
//		}
		
		//取食材
		IngredientBean ingredientBean = ingredientService.findByIngredientId(iId);
		
		//取圖片
		IngredientImageBean ingredientImageBean = ingredientImageService.findByIngredientImageId(iId);
		
		//計算熱量
		Integer calorie = (weight * ingredientBean.getCalorie());
		
		ingredientsVo.setCalorie(calorie);
		ingredientsVo.setIngredientImage(ingredientImageBean.getIngredientImage());
		ingredientsVo.setIngredientName(ingredientBean.getIngredientName());
		ingredientsVo.setTips(ingredientBean.getTips());
	
		return ingredientsVo;
	}
	
}
