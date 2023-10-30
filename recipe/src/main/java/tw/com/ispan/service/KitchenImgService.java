package tw.com.ispan.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.com.ispan.domain.KitchenImgBean;
import tw.com.ispan.repository.KitchenImgRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class KitchenImgService {

	@Autowired
	private KitchenImgRepository kitchenImgRepository;
	
	//查全部
	public List<KitchenImgBean> findAll(){
		return kitchenImgRepository.findAll();
	}
	
	
	//更新
	public KitchenImgBean update(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer kitchenwareImgId = obj.isNull("kitchenwareImgId")? null : obj.getInt("kitchenwareImgId");
			Integer kitchenwareId = obj.isNull("kitchenwareId")? null : obj.getInt("kitchenwareId");
			String kitchenwareImg = obj.isNull("kitchenwareImg")? null : obj.getString("kitchenwareImg");
			
			KitchenImgBean bean = new KitchenImgBean();
			bean.setKitchenwareImgId(kitchenwareImgId);
			bean.setKitchenwareId(kitchenwareId);
			bean.setKitchenwareImg(kitchenwareImg);
			
			KitchenImgBean result = kitchenImgRepository.update(bean);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return null;
	}
	
	
	//存在與否
	public boolean exist(Integer kitchenwareImgId) {
		return kitchenImgRepository.findById(kitchenwareImgId)!=null;
	}
	
	
	//用廚具id找尋
	public List<KitchenImgBean> findByKitchenwareId(Integer kitchenwareId) {
		if(kitchenwareId!=null) {
		return kitchenImgRepository.findBykitchenwareId(kitchenwareId);
		}
	return null;	
	}
	
	
}
