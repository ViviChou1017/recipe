package tw.com.ispan.impl;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.dao.MemberDaoImpl;
import tw.com.ispan.domain.MemberBean;
import tw.com.ispan.domain.OrderBean;
import tw.com.ispan.repository.MemberRepository;
import tw.com.ispan.repository.OrderRepository;
import tw.com.ispan.repository.TrackRepository;
import tw.com.ispan.service.TrackService;

//@SpringBootTest
public class MemberDaoImplTests {
	
	@Autowired
	private MemberDaoImpl memberDaoImpl;
	
	@Autowired
	private MemberDaoImpl memberDao;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private TrackService trackService;
	
	@Autowired
	private OrderRepository  orderRepository;
	
	@Autowired
	private TrackRepository  trackRepository;
	
	
//	@Test
	public void testFind() {
		List<MemberBean> findByAccountLike = memberRepository.findByAccountContaining("");
		System.out.println(findByAccountLike);
	}
	
//	@Test
	public void testFind1() {
		List<MemberBean> findByAccountLike = memberRepository.findByAccountContainingAndAccountNot("1234", "123");
		System.out.println(findByAccountLike);
	}
	
//	@Test
	public void testFind2() {
		List<Object[]> findByTrackerNumberAndTrackedNumber = trackRepository.followListWithName(1002);
		for (Object[] row : findByTrackerNumberAndTrackedNumber) {
		    Integer trackNumber = (Integer) row[0];
		    Integer memberNumber = (Integer) row[1];
		    String account = (String) row[2];
		    String memberName = (String) row[3];
		    System.out.println(account+trackNumber+memberNumber+memberName);
		    // 这里可以处理结果数据
		}
		
	}
	
//	@Test
	public void testFind3() {
		List<Object[]> findByTrackerNumberAndTrackedNumber = memberRepository.recipesWithPics(1);
		for (Object[] row : findByTrackerNumberAndTrackedNumber) {
		    Integer trackNumber = (Integer) row[0];
		    System.out.println(trackNumber);
		    // 这里可以处理结果数据
		}
		
	}
	
//	@Test
//	public void testFind3() {
//		List<OrderBean> findByTrackerNumberAndTrackedNumber = orderRepository.findByMemberId(1);
//		System.out.println(findByTrackerNumberAndTrackedNumber.get(0));
//	}
	
//	@Test
//	public void testFind3() {
//		Optional<OrderBean> findByTrackerNumberAndTrackedNumber = orderRepository.findById(1);
//		findByTrackerNumberAndTrackedNumber.get();
//		System.out.println(findByTrackerNumberAndTrackedNumber.get());
//	}
	
}
