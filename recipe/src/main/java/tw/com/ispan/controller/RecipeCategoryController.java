package tw.com.ispan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.domain.RecipeCategoryBean;
import tw.com.ispan.service.RecipeCategoryService;


@RestController
@RequestMapping("/recipeCategory")
@CrossOrigin
public class RecipeCategoryController {

	@Autowired
	private RecipeCategoryService recipeCategoryService;
	
	
	@GetMapping("/findById/{id}")
	public RecipeCategoryBean findByNumber(@PathVariable Integer id) {
		RecipeCategoryBean Category = recipeCategoryService.findByNumber(id);
		return Category;
	}
	

	//找全部
	@GetMapping("/findAll")
	public List<RecipeCategoryBean> findAll(){
		return recipeCategoryService.findAll();
	}
}
