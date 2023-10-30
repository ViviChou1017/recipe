package tw.com.ispan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.domain.ClassificationBean;
import tw.com.ispan.domain.IngredientBean;
import tw.com.ispan.service.ClassificationService;
import tw.com.ispan.service.IngredientService;
@RestController
@CrossOrigin
@RequestMapping("/recipe")
public class ClassificationControllerImpl implements  ClassificationController{
	@Autowired
	private ClassificationService ClassificationService;
	
	@Override
	@GetMapping("/findClassificationAll")
	public ResponseEntity<?> findAll() {
		List<ClassificationBean> ClassificationList = ClassificationService.findAll();
		ResponseEntity<List<ClassificationBean>> response = ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(ClassificationList);
		return response;
	}
	//查詢名字
	@Override
	@GetMapping("/findByClassificationName/{name}")
	public List<ClassificationBean> findByClassificationName(@PathVariable(name = "name") String name) {
		List<ClassificationBean> Classification = ClassificationService.findByClassificationName(name);
		if(Classification!=null) {
			ResponseEntity<List<ClassificationBean>> response = ResponseEntity
					.ok()
					.body(Classification);
			return Classification;
		} else {
			ResponseEntity<List<ClassificationBean>> response = ResponseEntity.notFound().build();
			return Classification;
		}
	}
//刪除
	@Override
	@DeleteMapping("/delClassification/{id}")
	public ResponseEntity<?> delInteger(@PathVariable Integer id) {
		boolean  delInteger = ClassificationService.delInteger(id);
		if(delInteger) {
			ResponseEntity<ClassificationBean> response = ResponseEntity.noContent().build();
			return response;
		} else {
			ResponseEntity<ClassificationBean> response = ResponseEntity.notFound().build();
			return response;
		}
	}
//新增
	@Override
	@PostMapping("/addClassification")
	public ClassificationBean addClassification(@RequestBody ClassificationBean bean) {
		return  ClassificationService.addClassification(bean);
		
	}
//修改
	@Override
	@PutMapping("/updateClassification/{id}")
	public ResponseEntity<?> updateClassification(@PathVariable Integer id,@RequestBody ClassificationBean bean) {
		String classificationName=bean.getClassificationName();
		ClassificationBean result= ClassificationService.updateClassification(bean);
		if(result!=null) {
			ResponseEntity<ClassificationBean> response = ResponseEntity
					.ok()
					.body(result);
			return response;
		} else {
			ResponseEntity<ClassificationBean> response = ResponseEntity.notFound().build();
			return response;
		}
	}
	@Override
	public ResponseEntity<?> selectByClassificationId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResponseEntity<?> selectByIngredientId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
