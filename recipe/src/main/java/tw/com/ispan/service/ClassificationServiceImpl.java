package tw.com.ispan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.micrometer.common.util.StringUtils;
import tw.com.ispan.dao.ClassificationDao;
import tw.com.ispan.domain.ClassificationBean;
import tw.com.ispan.repository.ClassificationRepository;

@Service
@Transactional
public class ClassificationServiceImpl implements ClassificationService {
	
	@Autowired
	private ClassificationRepository classificationRepository;
	
	 
	private String name;

	// 查詢全部
	@Override
	public List<ClassificationBean> findAll() {
		try {
			return classificationRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//查名字
	@Override
	public List<ClassificationBean> findByClassificationName(String name) {
		try {
			return classificationRepository.findByClassificationName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	//刪除
	@Override
	public boolean delInteger(Integer id) {
		try {
			return classificationRepository.delInteger(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
//新增
	@Override
	public ClassificationBean addClassification(ClassificationBean bean) {
		ClassificationBean result = null;
		if (bean != null && !StringUtils.isBlank(bean.getClassificationName())) {
			
			result = classificationRepository.addClassification(bean);
		}
		return result;
	}
//修改
	@Override
	public ClassificationBean updateClassification(ClassificationBean bean) {
		ClassificationBean result = null;
		try {
			if (bean != null) {
				result=classificationRepository.updateClassification(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ClassificationBean> findClassificationByIds(List<Integer> id) {
		List<ClassificationBean> beans = classificationRepository.findAllById(id);
		return beans;
	}

	@Override
	public ClassificationBean findClassificationById(Integer id) {
		ClassificationBean bean = classificationRepository.findClassificationById(id);
		if(null != bean) {
			return bean;
		}
		return null;
	}

}
