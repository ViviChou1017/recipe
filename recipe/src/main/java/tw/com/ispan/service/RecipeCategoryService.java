package tw.com.ispan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.domain.RecipeCategoryBean;
import tw.com.ispan.repository.RecipeCategoryRepository;

@Service
@Transactional
public class RecipeCategoryService {

	@Autowired
	private RecipeCategoryRepository recipeCategoryRepository;
	
	public RecipeCategoryBean findByNumber(Integer id) {
		try {
			return recipeCategoryRepository.findByNumber(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//找全部
	public List<RecipeCategoryBean> findAll(){
		List<RecipeCategoryBean> result = recipeCategoryRepository.findAll();
		return result;
	}
}
