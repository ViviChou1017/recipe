package tw.com.ispan.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.domain.MemberBean;
import tw.com.ispan.repository.MemberRepository;
import tw.com.ispan.util.DatetimeConverter;

@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	public List<MemberBean> findAll() {
		return memberRepository.findAll();
	}

	public MemberBean create(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			System.out.println(obj.toString());
			Integer memberNumber = obj.isNull("memberNumber") ? null : obj.getInt("memberNumber");
			String account = obj.isNull("account") ? null : obj.getString("account");
			String memberPassword = obj.isNull("memberPassword") ? null : obj.getString("memberPassword");
			String avatar = obj.isNull("avatar") ? null : obj.getString("avatar");
			String memberName = obj.isNull("memberName") ? null : obj.getString("memberName");
			String birthday = obj.isNull("birthday") ? null : obj.getString("birthday");
			System.out.println("birthday:" + birthday);
			Integer memberLevel = obj.isNull("memberLevel") ? null : obj.getInt("memberLevel");
			Integer viewCount = obj.isNull("viewCount") ? null : obj.getInt("viewCount");
			String phone = obj.isNull("phone") ? null : obj.getString("phone");
			String introduction = obj.isNull("introduction") ? null : obj.getString("introduction");
			String gender = obj.isNull("gender") ? null : obj.getString("gender");
			String email = obj.isNull("email") ? null : obj.getString("email");
			String address = obj.isNull("address") ? null : obj.getString("address");
			String cellphone = obj.isNull("cellphone") ? null : obj.getString("cellphone");

			MemberBean insert = new MemberBean();
			insert.setMemberNumber(memberNumber);
			insert.setAccount(account);
			insert.setMemberPassword(memberPassword);
			insert.setAvatar(avatar);
			insert.setMemberName(memberName);
			insert.setBirthday(DatetimeConverter.parse(birthday, "yyyy-MM-dd"));
			insert.setMemberLevel(memberLevel);
			insert.setViewCount(viewCount);
			insert.setPhone(phone);
			insert.setIntroduction(introduction);
			insert.setGender(gender);
			insert.setEmail(email);
			insert.setAddress(address);
			insert.setCellphone(cellphone);

			if (insert.getMemberNumber() == null) {
				return memberRepository.save(insert);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MemberBean modify(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			Integer memberNumber = obj.isNull("memberNumber") ? null : obj.getInt("memberNumber");
			String account = obj.isNull("account") ? null : obj.getString("account");
			String memberPassword = obj.isNull("memberPassword") ? null : obj.getString("memberPassword");
			String avatar = obj.isNull("avatar") ? null : obj.getString("avatar");
			String memberName = obj.isNull("memberName") ? null : obj.getString("memberName");
			String birthday = obj.isNull("birthday") ? null : obj.getString("birthday");
			Integer memberLevel = obj.isNull("memberLevel") ? null : obj.getInt("memberLevel");
			Integer viewCount = obj.isNull("viewCount") ? null : obj.getInt("viewCount");
			String phone = obj.isNull("phone") ? null : obj.getString("phone");
			String introduction = obj.isNull("introduction") ? null : obj.getString("introduction");
			String gender = obj.isNull("gender") ? null : obj.getString("gender");
			String email = obj.isNull("email") ? null : obj.getString("email");
			String address = obj.isNull("address") ? null : obj.getString("address");
			String cellphone = obj.isNull("cellphone") ? null : obj.getString("cellphone");

			Optional<MemberBean> optional = memberRepository.findById(id);
			if (optional != null && optional.isPresent()) {
				MemberBean update = optional.get();
				update.setMemberNumber(memberNumber);
				update.setAccount(account);
				update.setMemberPassword(memberPassword);
				update.setAvatar(avatar);
				update.setMemberName(memberName);
				update.setBirthday(DatetimeConverter.parse(birthday, "yyyy-MM-dd"));
				update.setMemberLevel(memberLevel);
				update.setViewCount(viewCount);
				update.setPhone(phone);
				update.setIntroduction(introduction);
				update.setGender(gender);
				update.setEmail(email);
				update.setAddress(address);
				update.setCellphone(cellphone);

				return memberRepository.save(update);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MemberBean findByAccount(String account) {

		try {
			return memberRepository.findByAccount(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<MemberBean> findByAccountContaining(String account) {

		try {

			return memberRepository.findByAccountContaining(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<MemberBean> findByAccountContainingAndAccountNot(String account, String notAccount) {

		try {

			return memberRepository.findByAccountContainingAndAccountNot(account, notAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MemberBean update(MemberBean memberBean) {
		try {
			if (memberBean != null) {
				if (memberRepository.existsById(memberBean.getMemberNumber())) {
					return memberRepository.save(memberBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
	
	public List<Object[]> recipesWithPics(Integer uploaderId) {

		try {

			return memberRepository.recipesWithPics(uploaderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Object[]> allIngredient() {

		try {

			return memberRepository.allIngredient();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	

}
