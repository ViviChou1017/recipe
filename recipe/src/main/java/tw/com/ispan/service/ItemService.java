package tw.com.ispan.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.com.ispan.domain.CartBean;
import tw.com.ispan.domain.CartProductBean;
import tw.com.ispan.domain.ItemBean;
import tw.com.ispan.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	public ItemRepository itemRepository;
	
	public List<ItemBean> findAll() {
		return itemRepository.findAll();
	}
	
	public ItemBean create(ItemBean json) {
		try {
			JSONObject obj = new JSONObject(json);
//			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			Integer kitchenwareId = obj.isNull("kitchenwareId") ? null : obj.getInt("kitchenwareId");
			Integer courseId = obj.isNull("courseId") ? null : obj.getInt("courseId");
//
//			CartBean insert = new CartBean();
//			insert.setUserAccountId(userAccountId);

			
//			if (userKitchenwareId == null && userCourseId == null) {
//				throw new Exception("KitchenwareId & CourseId are not exists ");
//			}else {
//				
//				return itemRepository.save(json);
//			}
			
			if(!itemRepository.existsByKitchenwareId(kitchenwareId) && kitchenwareId != null) {
				System.out.println("itemNOTNUll1");
				return itemRepository.save(json);
				
			}else {
				if(!itemRepository.existsByCourseId(courseId) && courseId != null) {
					System.out.println("itemNOTNUll2");
					return itemRepository.save(json);
				}
//				throw new Exception("KitchenwareId or CourseId already exist. ");
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("itemNUll");
		return null;
	}
	
	public boolean remove(Integer kitchenwareId, Integer courseId) {
		try {
			if (itemRepository.existsByKitchenwareId(kitchenwareId)) {
				itemRepository.deleteByKitchenwareId(kitchenwareId);
				return true;
			}else {
				itemRepository.deleteByCourseId(courseId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
