package tw.com.ispan.controller;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import tw.com.ispan.domain.FoodMixBean;
import tw.com.ispan.domain.MemberBean;
import tw.com.ispan.domain.TrackBean;
import tw.com.ispan.service.TrackService;

@RestController
@CrossOrigin
@RequestMapping(path = "")
public class TrackController {

	@Autowired
	private TrackService trackService;

	@PostMapping("/track")
	public ResponseEntity<?> insert(HttpServletRequest request, @RequestBody TrackBean param) {

		try {
			if (param != null) {
				if (!trackService.findByTrackerNumberAndTrackedNumber(param.getTrackerNumber(),
						param.getTrackedNumber())) {
					TrackBean insert = trackService.insert(param);
					return new ResponseEntity<>(insert, HttpStatus.OK);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/track/{trackerNumber}")
	public ResponseEntity<?> findByTrackerNumber(@PathVariable Integer trackerNumber) {

		try {
			if (trackerNumber != null) {
				List<TrackBean> result = trackService.findByTrackerNumber(trackerNumber);

				return new ResponseEntity<>(result, HttpStatus.OK);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	
	@GetMapping("/track-with-name/{trackerNumber}")
	public ResponseEntity<?> followListWithName(@PathVariable Integer trackerNumber) {

		try {
			if (trackerNumber != null) {
				List<Object[]> result = trackService.followListWithName(trackerNumber);
				
				
				

				return new ResponseEntity<List<Object[]>>(result, HttpStatus.OK);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	
	@DeleteMapping("/track/delete/{trackNumber}")
	public ResponseEntity<?> delete(@PathVariable Integer trackNumber) {

		Integer delete = trackService.deleteByTrackNumber(trackNumber);
		if(delete > 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
