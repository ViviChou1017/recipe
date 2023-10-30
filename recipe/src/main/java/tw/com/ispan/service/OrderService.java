package tw.com.ispan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.com.ispan.domain.OrderBean;
import tw.com.ispan.repository.OrderRepository;


@Service
@Transactional
public class OrderService  {
	
	@Autowired
	public OrderRepository orderRepository;
	
	
	
	public List<OrderBean> findAllByMemberId(Integer memberId) {
		try {
			return orderRepository.findAllByMemberId(memberId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public OrderBean create(OrderBean json) {
		try {
		
//			String note = obj.isNull("note") ? null : obj.getString("note");
//			Integer memberId = obj.isNull("memberId") ? null : obj.getInt("memberId");
//			Date orderDate = obj.isNull("orderDate") ? null : obj.getDate("orderDate");
			
//			OrderBean insert = new OrderBean();
//			insert.setCartId(cartId);
//			insert.setId(id);
//			insert.setNote(note);
//			insert.setOrderDate(orderDate);
//			insert.setMemberId(memberId);

			return orderRepository.save(json);
//			if (orderRepository.findByMemberId(json.getMemberId()).isEmpty()) {
//			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<OrderBean> createMultiple(List<OrderBean> orderList) {
        try {
            List<OrderBean> insertedOrders = new ArrayList<>();

            for (OrderBean order : orderList) {
                if (orderRepository.findByMemberId(order.getMemberId()).isEmpty()) {
                    OrderBean insertedOrder = orderRepository.save(order);
                    insertedOrders.add(insertedOrder);
                }
            }

            return insertedOrders;
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }


}
