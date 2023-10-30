package tw.com.ispan.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.domain.CollectBean;
import tw.com.ispan.repository.CollectRepository;
import tw.com.ispan.service.CollectService;

@RestController
@RequestMapping("/collect")
@CrossOrigin
public class CollectController {

	@Autowired
	private CollectService collectService;
	
	@Autowired
	private CollectRepository collectRepository;
	
	
	//找收藏
	@PostMapping("/findByCollectorNumberAndRecipeNumber")
	public CollectBean findByCollectorNumberAndRecipeNumber(@RequestBody String json) {
		try {
		JSONObject obj = new JSONObject(json);
		Integer collectorNumber = obj.isNull("collectorNumber")?null:obj.getInt("collectorNumber");
		Integer recipeNumber = obj.isNull("recipeNumber")?null:obj.getInt("recipeNumber");
		return collectRepository.findByCollectorNumberAndRecipeNumber(collectorNumber, recipeNumber);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {	
		}
		return null;
	}
	
	
	
	
	
	
	@PostMapping("/createCollect")
	public CollectBean createCollect(@RequestBody CollectBean collect) {
		return collectService.createCollect(collect);
	}
	
	@PostMapping("/deleteCollect")
    public void deleteCollect(@RequestBody String json) {
		try {
		JSONObject obj = new JSONObject(json);
		Integer collectNumber = obj.isNull("collectNumber")?null:obj.getInt("collectNumber");
		 collectService.deleteCollect(collectNumber);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		}
		
    }
}
