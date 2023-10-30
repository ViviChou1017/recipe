package tw.com.ispan.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.domain.KitchenwareBean;

import tw.com.ispan.service.kitchenwareService;
import tw.com.ispan.util.DatetimeConverter;

@RestController
@RequestMapping(path = {"/pages/ajax"})
@CrossOrigin
public class KitchenwareController {

	@Autowired
	private kitchenwareService kitchenwareService;
	

	
	//新增OK
	@PostMapping(path = "/kitchenware/insert")
	public String create(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		JSONObject obj = new JSONObject(json);
		Integer kitchenwareId = obj.isNull("kitchenwareId") ? null : obj.getInt("kitchenwareId");
		
		if(kitchenwareService.exist(kitchenwareId)) {
			responseJson.put("message", "資料已存在");
			responseJson.put("success", false);
		} else {
			KitchenwareBean product = null;
			try {
				product = kitchenwareService.create(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(product==null) {
				responseJson.put("message", "新增失敗");
				responseJson.put("success", false);
			} else {
				responseJson.put("message", "新增成功");
				responseJson.put("success", true);
			}
		}
		return responseJson.toString();
	}
	
	
	
	//修改OK
	@PostMapping(path= {"/kitchenware/update"})
	public String update(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		JSONObject obj = new JSONObject(json);
		Integer kitchenwareId = obj.isNull("kitchenwareId")?null:obj.getInt("kitchenwareId");
		
		if(!kitchenwareService.exist(kitchenwareId)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			KitchenwareBean product = null;
			try {
				product = kitchenwareService.update(json);
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
	
	
	//刪除OK
	@DeleteMapping(path = "/kitchenware/delete")
	public String delete(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		JSONObject obj = new JSONObject(json);
		Integer kitchenwareId = obj.isNull("kitchenwareId")?null:obj.getInt("kitchenwareId");
		
		if(!kitchenwareService.exist(kitchenwareId)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			if(kitchenwareService.remove(kitchenwareId)) {
				responseJson.put("message", "刪除成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "刪除失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}
		
		
		
		@GetMapping(path = "/kitchenware/findAll")
		public List<KitchenwareBean> findAll() {
			return kitchenwareService.findAll();
		}
		
		
		
		
		
		//find+count(RequestBody什麼都不給就等於查全部，但還是要給一個空的{})
		@PostMapping(path={"/kitchenware/find"})
		public String find(@RequestBody String body) {
			JSONObject responseJson = new JSONObject();
			long count = kitchenwareService.count(body);
			responseJson.put("count", count);
			
			JSONArray array = new JSONArray();
			List<KitchenwareBean> result = kitchenwareService.find(body);
			if(result!=null && !result.isEmpty()) {
				for(KitchenwareBean view:result) {
					String date = DatetimeConverter.toString(
							view.getKitchenwareCreateDate(), "yyyy-MM-dd");
					JSONObject item = new JSONObject()
							.put("kitchenwareId", view.getKitchenwareId())
							.put("kitchenwareName", view.getKitchenwareName())
							.put("kitchenwareCreateDate", date)
							.put("kitchenwareContent",view.getKitchenwareContent())
							.put("kitchenwarePrice", view.getKitchenwarePrice())
							.put("kitchenwareBrand", view.getKitchenwareBrand())
							.put("kitchenwareMaterial", view.getKitchenwareMaterial())
							.put("kitchenwareOrigin", view.getKitchenwareOrigin());
							
					
					
					array = array.put(item);
					
				}
			}
			responseJson.put("list",array);
			return responseJson.toString();
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping(path = {"/kitchenware/findById"})
	public String findById(@RequestBody String json) {
		try {
		JSONObject responseJson = new JSONObject();
		JSONObject obj = new JSONObject(json);
		Integer id = obj.isNull("kitchenwareId")?null:obj.getInt("kitchenwareId");
		if(id != null) {
			KitchenwareBean result = kitchenwareService.findById(id);
			responseJson.put("kitchenware_id", result.getKitchenwareId())
						.put("kitchenware_name ", result.getKitchenwareName())
						.put("kitchenware_create_date ", result.getKitchenwareCreateDate())
						.put("kitchenware_content ", result.getKitchenwareContent())
						.put("kitchenware_price ", result.getKitchenwarePrice())
						.put("kitchenware_brand ", result.getKitchenwareBrand())
						.put("kitchenware_material ", result.getKitchenwareMaterial())
						.put("kitchenware_origin ", result.getKitchenwareOrigin());
			
			return responseJson.toString();
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@GetMapping(path = {"/kitchenware/finds/{name}"})
	public ResponseEntity<?> findByName(@PathVariable(name = "name") String name) {
		List<KitchenwareBean> result = kitchenwareService.findByName(name);
		if(result != null) {
			ResponseEntity<List<KitchenwareBean>> response = ResponseEntity.ok().body(result);
			return response;
		}else {
			ResponseEntity<Void> response = ResponseEntity.notFound().build();
			return response;
		}
	}
	
	
}
