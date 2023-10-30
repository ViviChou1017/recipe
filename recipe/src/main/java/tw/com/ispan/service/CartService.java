package tw.com.ispan.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.com.ispan.domain.CartBean;
import tw.com.ispan.domain.CartProductBean;
import tw.com.ispan.repository.CartRepository;

@Service
@Transactional
public class CartService {
	
	@Autowired
	public CartRepository cartRepository;
	
	
	public CartBean create(CartBean json) {
		try {
			JSONObject obj = new JSONObject(json);
////			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			Integer userAccountId = obj.isNull("userAccountId") ? null : obj.getInt("userAccountId");
//
//			CartBean insert = new CartBean();
//			insert.setUserAccountId(userAccountId);

			if (!cartRepository.existsByUserAccountId(userAccountId)) {
				//設置當前時間
				Date currentDate = new Date();
	            Timestamp timestamp = new Timestamp(currentDate.getTime());
	            json.setAddedDate(timestamp);
				return cartRepository.save(json);
			}else {
//				throw new Exception("UserAccountId already exists");
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<CartBean> findAll() {
		return cartRepository.findAll();
	}
	

}
