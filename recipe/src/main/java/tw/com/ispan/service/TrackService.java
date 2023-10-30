package tw.com.ispan.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.com.ispan.domain.TrackBean;
import tw.com.ispan.repository.TrackRepository;

@Transactional
@Service
public class TrackService {

	@Autowired
	private TrackRepository trackRepository;

	public Boolean findByTrackerNumberAndTrackedNumber(Integer trackerNumber, Integer trackedNumber) {

		try {
			return trackRepository.existsByTrackerNumberAndTrackedNumber(trackerNumber, trackedNumber);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public TrackBean insert(TrackBean insert) {
		try {
			return trackRepository.save(insert);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public List<TrackBean> findByTrackerNumber(Integer trackerNumber) {
		try {
			return trackRepository.findByTrackerNumber(trackerNumber);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public List<Object[]> followListWithName(Integer trackerNumber) {
		try {
			List<Object[]> follows = trackRepository.followListWithName(trackerNumber);

//			JSONArray jsonArray = new JSONArray();
//
//			for (Object[] follow : follows) {
//
//				JSONObject jsonObject = new JSONObject();
//				jsonObject.put("trackNumber", (Integer) follow[0]);
//				jsonObject.put("memberNumber", (Integer) follow[1]);
//				jsonObject.put("account", (String) follow[2]);
//				jsonObject.put("memberName", (String) follow[3]);
//
//				jsonArray.put(jsonObject);
//			}
			
			return  follows;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
	
	public Integer deleteByTrackNumber(Integer trackNumber) {
		try {
			return trackRepository.deleteByTrackNumber(trackNumber);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
	

}
