package tw.com.ispan.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.domain.ScoreBean;
import tw.com.ispan.repository.ScoreRepository;


@Service
@Transactional
public class ScoreService {

	@Autowired
	private ScoreRepository scoreRepository;
	
	//檢查評分者編號是否重複，如重複就不能再評分
	public boolean exists(Integer raterNumber,Integer recipeNumber) { 
		
		return  scoreRepository.findByRaterNumberAndRecipeNumber(raterNumber, recipeNumber) != null;
		
		}
		
	
	
	//有擋了但Controller不會回傳資料已存在
	public ScoreBean create(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer ratNumber = obj.isNull("ratNumber") ? null : obj.getInt("ratNumber");
			Integer raterNumber = obj.isNull("raterNumber") ? null : obj.getInt("raterNumber");
			Integer recipeNumber = obj.isNull("recipeNumber") ? null : obj.getInt("recipeNumber");
			String ratDate = obj.isNull("ratDate") ? null : obj.getString("ratDate");
			Integer score = obj.isNull("score") ? null : obj.getInt("score");
			
//			if (raterNumber != null && recipeNumber != null) {
//	            boolean hasRated = scoreRepository.existsByRaterNumberAndRecipeNumber(raterNumber, recipeNumber);
//	            if (hasRated) {
//	                return null;
//	            }
//	        }
			
			LocalDateTime currentDateTime = LocalDateTime.now();
			Date currentDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
			
			ScoreBean insert = new ScoreBean();
			insert.setRatNumber(ratNumber);
			insert.setRaterNumber(raterNumber);
			insert.setRecipeNumber(recipeNumber);
			insert.setRatDate(currentDate);
			insert.setScore(score);
			return scoreRepository.insertScore(insert);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//刪除byratNumber
	public void deleteScore(Integer ratNumber) {
	 scoreRepository.deleteScore(ratNumber);
	}
	
	
	//刪除ByRaterNumberAndRecipeNumber
	public void deleteByRaterNumberAndRecipeNumber(Integer raterNumber,Integer recipeNumber) {
		ScoreBean bean = scoreRepository.findByRaterNumberAndRecipeNumber(raterNumber, recipeNumber);
		 scoreRepository.delete(bean);;
	}
	
	
	//更新
	public ScoreBean updateScore(Integer ratNumber,Integer newScore) {
		ScoreBean existingScore = scoreRepository.findByRatNumber(ratNumber);
		if(existingScore!=null) {
			existingScore.setScore(newScore);
			existingScore.setRatDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
			return scoreRepository.save(existingScore);
		}
		return null;
	}
	
	//查全部
	public List<ScoreBean> findAll(){
		try {
			return scoreRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

