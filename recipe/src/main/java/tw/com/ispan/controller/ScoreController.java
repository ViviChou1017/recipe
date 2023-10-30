package tw.com.ispan.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.domain.ScoreBean;
import tw.com.ispan.repository.ScoreRepository;
import tw.com.ispan.service.ScoreService;


@RestController
@RequestMapping("/recipe")
@CrossOrigin
public class ScoreController {

	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	
	@PostMapping("/exists")
	public boolean exists(@RequestBody String body) {
		JSONObject obj = new JSONObject(body);
		Integer raterNumber = obj.isNull("raterNumber") ? null : obj.getInt("raterNumber");
		Integer recipeNumber = obj.isNull("recipeNumber") ? null : obj.getInt("recipeNumber");
		
		return scoreService.exists(raterNumber,recipeNumber);
	}
	
	
	@PostMapping(path = "/createScore")
	public String create(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();

		JSONObject obj = new JSONObject(body);
		Integer raterNumber = obj.isNull("raterNumber") ? null : obj.getInt("raterNumber");
		Integer recipeNumber = obj.isNull("recipeNumber") ? null : obj.getInt("recipeNumber");
		//判斷資料是否存在似乎沒作用
		if(scoreService.exists(raterNumber,recipeNumber)) {
			responseJson.put("message", "資料已存在");
			responseJson.put("success", false);
		} else {
				ScoreBean Score = scoreService.create(body);
				if(Score != null) {
					responseJson.put("message", "新增成功");
					responseJson.put("success", true);
				} else {
					responseJson.put("message", "新增失敗");
					responseJson.put("success", false);
				}
			
		}
		return responseJson.toString();
	}
	
	@PutMapping("/updateScore/{ratNumber}")
    public ResponseEntity<ScoreBean> updateScore(@PathVariable Integer ratNumber, @RequestBody String json) {
		JSONObject obj = new JSONObject(json);
		Integer newScore = obj.isNull("newScore")?null:obj.getInt("newScore");
		
		ScoreBean updateScore = scoreService.updateScore(ratNumber, newScore);
        if (updateScore != null) {
            return new ResponseEntity<>(updateScore, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }

	@PostMapping("/deleteScore/ratNumber")
    public boolean deleteScore(@RequestBody String json) {
		JSONObject obj = new JSONObject(json);
		Integer ratNumber = obj.isNull("ratNumber")?null:obj.getInt("ratNumber"); 
    	scoreService.deleteScore(ratNumber);
    	return true;
    }
	
	//刪除用這個(用評分者id+食譜id)來刪(X)
	@PostMapping("/deleteScore")
	public void delete(@RequestBody String json) {
		JSONObject obj = new JSONObject(json);
		Integer raterNumber = obj.isNull("raterNumber") ? null : obj.getInt("raterNumber");
		Integer recipeNumber = obj.isNull("recipeNumber") ? null : obj.getInt("recipeNumber");
		
		scoreService.deleteByRaterNumberAndRecipeNumber(raterNumber, recipeNumber);
	}
	
	
	
	
	
	
	
	@GetMapping("/findAllScore")
	public List<ScoreBean> findAll(){
		List<ScoreBean> score = scoreService.findAll();
		return score;
	}
	
	
	@PostMapping("/findByRecipeNumber")
	public List<ScoreBean> findByRecipeNumber(@RequestBody String json){
		JSONObject obj = new JSONObject(json);
		Integer recipeNumber = obj.isNull("recipeNumber") ? null : obj.getInt("recipeNumber");
		
		return scoreRepository.findByRecipeNumber(recipeNumber);
		
	}
	
	@GetMapping("/findById/{ratNumber}")
	public ScoreBean findById(@PathVariable Integer ratNumber) {
	return scoreRepository.findById(ratNumber).get();
	}
}
