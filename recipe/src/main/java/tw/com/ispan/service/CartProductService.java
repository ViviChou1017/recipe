package tw.com.ispan.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.PersistenceContext;
import tw.com.ispan.dao.CartProductDao;
import tw.com.ispan.domain.CartProductBean;
import tw.com.ispan.repository.CartProductRepository;

@Service
@Transactional
public class CartProductService {

	@Autowired
	private CartProductRepository cartProductRepository;

	public List<CartProductBean> findAll() {
		return cartProductRepository.findAll();
	}
	
//	public boolean removeAll() {
//		cartProductRepository.deleteAll();
//		return true;
//	}

//	public List<CartProductBean> findAllByMemberNumber(Integer number) {
//		try {
//			return cartProductRepository.findAllByMemberNumber(number);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//
//	}

	public CartProductBean modify(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer cartProductId = obj.isNull("cartProductId") ? null : obj.getInt("cartProductId");
//			Integer cartId = obj.isNull("cartId") ? null : obj.getInt("cartId");
//			Integer productId = obj.isNull("productId") ? null : obj.getInt("productId");
			Integer quantity = obj.isNull("quantity") ? null : obj.getInt("quantity");

			Optional<CartProductBean> optional = cartProductRepository.findById(cartProductId);
			if (optional != null && optional.isPresent()) {
				CartProductBean update = optional.get();
//				update.setCartId(cartId);
//				update.setProductId(productId);
				update.setQuantity(quantity);

				return cartProductRepository.save(update);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

//	public CartProductBean create(String json) {
//		try {
//			JSONObject obj = new JSONObject(json);
//			Integer id = obj.isNull("id") ? null : obj.getInt("id");
//			Integer cartId = obj.isNull("cartId") ? null : obj.getInt("cartId");
//			Integer productId = obj.isNull("productId") ? null : obj.getInt("productId");
//			Integer quantity = obj.isNull("quantity") ? null : obj.getInt("quantity");
//
//			CartProductBean insert = new CartProductBean();
//			insert.setCartId(cartId);
//			insert.setProductId(productId);
//			insert.setQuantity(quantity);
//
//			if (!cartProductRepository.existsById(id)) {
//				return cartProductRepository.save(insert);
//			}
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public CartProductBean create(CartProductBean json) {
		try {
			JSONObject obj = new JSONObject(json);
//			Integer id = obj.isNull("id") ? null : obj.getInt("id");
//			Integer cartId = obj.isNull("cartId") ? null : obj.getInt("cartId");
			Integer productId = obj.isNull("productId") ? null : obj.getInt("productId");
//			Integer quantity = obj.isNull("quantity") ? null : obj.getInt("quantity");
//
//			CartProductBean insert = new CartProductBean();
//			insert.setCartId(cartId);
//			insert.setProductId(productId);
//			insert.setQuantity(quantity);

			if (!cartProductRepository.existsByProductId(productId) && productId!= null) {
				return cartProductRepository.save(json);
			}else {
//				throw new Exception("productId already exists");
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean remove(Integer cartProductId) {
		try {
			if (cartProductRepository.existsById(cartProductId)) {
				cartProductRepository.deleteById(cartProductId);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeAll(Integer cartProductId) {
		try {
			if (cartProductRepository.existsById(cartProductId)) {
				cartProductRepository.deleteById(cartProductId);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
