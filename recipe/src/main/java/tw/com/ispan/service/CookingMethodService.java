package tw.com.ispan.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.domain.CookingMethodBean;
import tw.com.ispan.domain.MemberBean;
import tw.com.ispan.repository.CookingMethodRepository;
import tw.com.ispan.repository.MemberRepository;
import tw.com.ispan.util.DatetimeConverter;

@Service
@Transactional
public class CookingMethodService {

	@Autowired
	private CookingMethodRepository cookingMethodRepository;

	public List<CookingMethodBean> findAll() {
		return cookingMethodRepository.findAll();
	}

	public List<CookingMethodBean> findByMethodNameContaining(String methodName) {

		try {

			return cookingMethodRepository.findByMethodNameContaining(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
