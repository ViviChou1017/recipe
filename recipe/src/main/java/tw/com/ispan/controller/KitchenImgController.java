package tw.com.ispan.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.domain.KitchenImgBean;
import tw.com.ispan.domain.KitchenwareBean;
import tw.com.ispan.service.KitchenImgService;

@RestController
@RequestMapping(path = {"/pages/ajax"})
@CrossOrigin
public class KitchenImgController {

	@Autowired
	private KitchenImgService kitchenImgService;
	
	
	//查全部OK
	@GetMapping(path = "/kitchenimg/findall")
	public List<KitchenImgBean> findAll(){
		return kitchenImgService.findAll();
	}
	
	
	
	//修改OK
	@PostMapping(path = "/kitchenimg/update")
	public String update(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		JSONObject obj = new JSONObject(json);
		Integer kitchenwareImgId = obj.isNull("kitchenwareImgId")? null : obj.getInt("kitchenwareImgId");
		if(!kitchenImgService.exist(kitchenwareImgId)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			KitchenImgBean product = null;
			try {
				product = kitchenImgService.update(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(product==null) {
				responseJson.put("message", "修改失敗");
				responseJson.put("success", false);
			} else {
				responseJson.put("message", "修改成功");
				responseJson.put("success", true);
			}
		
	}
		return responseJson.toString();
	}
	
	
	
	@PostMapping(path = "/kitchenimg/findByKitchwareId")
	public List<KitchenImgBean> findByKitchenwareId(@RequestBody String json){
		JSONObject obj = new JSONObject(json);
		
		Integer kitchenwareId = obj.isNull("kitchenwareId")? null : obj.getInt("kitchenwareId");
		
		return kitchenImgService.findByKitchenwareId(kitchenwareId);
		
	}
	
	
}
