package tw.com.ispan.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.com.ispan.domain.KitchenwareBean;

import tw.com.ispan.repository.KitchenwareRepository;
import tw.com.ispan.util.DatetimeConverter;

@Service
@Transactional(rollbackOn = Exception.class)
public class kitchenwareService {

	@Autowired
	private KitchenwareRepository kitchenwareRepository;

	
	//新增
	public KitchenwareBean create(String json) {
		try{
			JSONObject obj = new JSONObject(json);
			
//			Integer kitchenwareId = obj.isNull("kitchenwareId")? null : obj.getInt("kitchenwareId");
			String kitchenwareName = obj.isNull("kitchenwareName")? null : obj.getString("kitchenwareName");
			String kitchenwareCreateDate = obj.isNull("kitchenwareCreateDate")? null : obj.getString("kitchenwareCreateDate");
			String kitchenwareContent = obj.isNull("kitchenwareContent")? null : obj.getString("kitchenwareContent");
			Integer kitchenwarePrice = obj.isNull("kitchenwarePrice")? null : obj.getInt("kitchenwarePrice");
			String kitchenwareBrand = obj.isNull("kitchenwareBrand")? null : obj.getString("kitchenwareBrand");
			String kitchenwareMaterial = obj.isNull("kitchenwareMaterial")? null : obj.getString("kitchenwareMaterial");
			String kitchenwareOrigin = obj.isNull("kitchenwareOrigin")? null : obj.getString("kitchenwareOrigin");
			
			KitchenwareBean bean = new KitchenwareBean();
//			bean.setKitchenwareId(kitchenwareId);
			bean.setKitchenwareName(kitchenwareName);
			bean.setKitchenwareCreateDate(DatetimeConverter.parse(kitchenwareCreateDate, "yyyy-MM-dd"));
			bean.setKitchenwareContent(kitchenwareContent);
			bean.setKitchenwarePrice(kitchenwarePrice);
			bean.setKitchenwareBrand(kitchenwareBrand);
			bean.setKitchenwareMaterial(kitchenwareMaterial);
			bean.setKitchenwareOrigin(kitchenwareOrigin);
			
			return kitchenwareRepository.save(bean);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return null;
	}
	
	
	
	
	//修改
	public KitchenwareBean update(String json) {
		try {
		JSONObject obj = new JSONObject(json);
		
		Integer kitchenwareId = obj.isNull("kitchenwareId")? null : obj.getInt("kitchenwareId");
		String kitchenwareName = obj.isNull("kitchenwareName")? null : obj.getString("kitchenwareName");
		String kitchenwareCreateDate = obj.isNull("kitchenwareCreateDate")? null : obj.getString("kitchenwareCreateDate");
		String kitchenwareContent = obj.isNull("kitchenwareContent")? null : obj.getString("kitchenwareContent");
		Integer kitchenwarePrice = obj.isNull("kitchenwarePrice")? null : obj.getInt("kitchenwarePrice");
		String kitchenwareBrand = obj.isNull("kitchenwareBrand")? null : obj.getString("kitchenwareBrand");
		String kitchenwareMaterial = obj.isNull("kitchenwareMaterial")? null : obj.getString("kitchenwareMaterial");
		String kitchenwareOrigin = obj.isNull("kitchenwareOrigin")? null : obj.getString("kitchenwareOrigin");
		
		
		KitchenwareBean bean = kitchenwareRepository.findBykitchenwareId(kitchenwareId);
		bean.setKitchenwareName(kitchenwareName);
		bean.setKitchenwareCreateDate(DatetimeConverter.parse(kitchenwareMaterial, "yyyy-MM-dd"));
		bean.setKitchenwareContent(kitchenwareContent);
		bean.setKitchenwarePrice(kitchenwarePrice);
		bean.setKitchenwareBrand(kitchenwareBrand);
		bean.setKitchenwareMaterial(kitchenwareMaterial);
		bean.setKitchenwareOrigin(kitchenwareOrigin);
		
		KitchenwareBean result = kitchenwareRepository.update(bean);
		return result;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return null;
	}
	
	
	

	
	//刪除
	public boolean remove(Integer kitchenwareId) {
		boolean result = kitchenwareRepository.delete(kitchenwareId);
		return result;
	}
	
	
	
	//查詢資料總比數
		public long count(String json) {
			try {
				JSONObject obj = new JSONObject(json);
				return kitchenwareRepository.count(obj);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				
			}
			return 0;
		}
	
	
		
		//透過條件查詢結果(沒設條件就是查全部)
		public List<KitchenwareBean> find(String json){
			try {
			JSONObject obj = new JSONObject(json);
			return  kitchenwareRepository.find(obj);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				
			}
			return null;
		}
	
	

	
	
	public boolean exist(Integer kitchenwareId) {
		return kitchenwareRepository.findBykitchenwareId(kitchenwareId) != null;
	}
	
	
	
	//查全部
	public List<KitchenwareBean> findAll(){
		return kitchenwareRepository.findAll();
	}
	
	
	// ID查詢
	public KitchenwareBean findById(Integer id) {
		if (id != null) {
			Optional<KitchenwareBean> optional = kitchenwareRepository.findById(id);
			if (optional != null && optional.isPresent()) {
				return optional.get();
			}
		}
		return null;
	}

	
	
	public List<KitchenwareBean> findByName(String name) {
		try {

			List<KitchenwareBean> list = kitchenwareRepository.findByName(name);
			if (list != null && !list.isEmpty()) {
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
