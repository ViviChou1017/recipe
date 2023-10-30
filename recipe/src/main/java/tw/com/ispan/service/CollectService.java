package tw.com.ispan.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.domain.CollectBean;
import tw.com.ispan.repository.CollectRepository;

@Service
@Transactional
public class CollectService {

	@Autowired
	private CollectRepository collectRepository;
	
	public CollectBean createCollect(CollectBean collect) {
		try {
			Date currentTime = new Date(); //取當下時間
	        collect.setCollectDate(currentTime);
			return collectRepository.save(collect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteCollect(Integer id) {
		collectRepository.deleteById(id);
	}
	
	
	
//	public CollectBean create(String json) {
//		try {
//			JSONObject obj = new JSONObject(json);
//			Integer collectorNumber = obj.isNull("collectorNumber") ? null : obj.getInt("collectorNumber");
//			Integer recipeNumber = obj.isNull("recipeNumber") ? null : obj.getInt("recipeNumber");
//			String collectDate = obj.isNull("collectDate") ? null : obj.getString("collectDate");
//			
//			
//			if (collectorNumber != null && recipeNumber != null && recipeNumber != null) {
//	            boolean hasRated = scoreRepository.existsByRatNumberAndRaterNumberAndRecipeNumber(ratNumber, raterNumber, recipeNumber);
//	            if (hasRated) {
//	                return null;
//	            }
//	        }
//			
//			LocalDateTime currentDateTime = LocalDateTime.now();
//			Date currentDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
//			
//			ScoreBean insert = new ScoreBean();
//			insert.setRatNumber(ratNumber);
//			insert.setRaterNumber(raterNumber);
//			insert.setRecipeNumber(recipeNumber);
//			insert.setRatDate(currentDate);
//			insert.setScore(score);
//			return scoreRepository.insertScore(insert);
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	//刪除
//	public void deleteCollect(Integer ratNumber) {
//	 scoreRepository.deleteScore(ratNumber);
//	}

}
