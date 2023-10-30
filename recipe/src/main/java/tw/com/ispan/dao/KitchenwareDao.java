package tw.com.ispan.dao;

import java.util.List;

import org.json.JSONObject;

import tw.com.ispan.domain.KitchenwareBean;


public interface KitchenwareDao {

	
	public List<KitchenwareBean> findByName(String name);
	
//	public KitchenwareBean insert(KitchenwareBean bean);
	
	public KitchenwareBean update(KitchenwareBean bean);
	
	public boolean delete(Integer id);
	
	public KitchenwareBean findBykitchenwareId(Integer kitchenwareId);
	
	public Long count(JSONObject obj);
	
	public List<KitchenwareBean> find(JSONObject obj);
}
