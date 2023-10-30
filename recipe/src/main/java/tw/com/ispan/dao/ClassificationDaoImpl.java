package tw.com.ispan.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.hibernate.Session;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.PersistenceContext;
import tw.com.ispan.domain.ClassificationBean;
import tw.com.ispan.domain.IngredientBean;
import tw.com.ispan.repository.ClassificationRepository;

@Repository
public class ClassificationDaoImpl  implements ClassificationRepository{
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}
	//查名字
	@Override
	public List<ClassificationBean> findByClassificationName(String name) {
		try {
			return this.getSession()
					.createQuery("from ClassificationBean where classificationName like '%"+ name +"%'", ClassificationBean.class)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//刪除
	@Override
	public boolean delInteger(Integer id) {
		if (id != null) {
			ClassificationBean temp = this.getSession().get(ClassificationBean.class, id);
			if (temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}
//新增
	@Override
	public ClassificationBean addClassification(ClassificationBean bean) {
		try {
			if (bean != null && StringUtils.isNotBlank(bean.getClassificationName()) ) {
				this.getSession().persist(bean);
				return bean;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bean;
	}
//修改
	@Override
	public ClassificationBean updateClassification(ClassificationBean bean) {
		if (bean != null) {
			ClassificationBean temp = this.getSession().get(ClassificationBean.class, bean.getClassificationId());
			if (temp != null) {
				temp.setClassificationName(bean.getClassificationName());
				
			}
		}
		return bean;
	}

	
	@Override
	public List<ClassificationBean> findAll() {
		List<ClassificationBean> Classification = this.getSession().createQuery("from ClassificationBean", ClassificationBean.class)
				.list();
		return Classification;
	}
	
	//
	public ClassificationBean findClassificationById(Integer id) {
		ClassificationBean classificationBean = this.getSession().get(ClassificationBean.class, id);
		return classificationBean;
	}
	
	
	
	
	
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends ClassificationBean> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ClassificationBean> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<ClassificationBean> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClassificationBean getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassificationBean getById(Integer id) {
		return null;
	}

	@Override
	public ClassificationBean getReferenceById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ClassificationBean> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ClassificationBean> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ClassificationBean> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClassificationBean> findAllById(Iterable<Integer> ids) {
		List<ClassificationBean> clists= new ArrayList<ClassificationBean>();
		for(Integer id : ids) {
			ClassificationBean classificationBean = this.getSession().get(ClassificationBean.class, id);
			clists.add(classificationBean);
		}
		return clists;
	}

	@Override
	public <S extends ClassificationBean> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ClassificationBean> findById(Integer id) {
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ClassificationBean entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends ClassificationBean> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ClassificationBean> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ClassificationBean> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ClassificationBean> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ClassificationBean> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ClassificationBean> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends ClassificationBean> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends ClassificationBean, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
}
