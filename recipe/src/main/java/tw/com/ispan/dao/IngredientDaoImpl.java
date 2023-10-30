package tw.com.ispan.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.hibernate.Session;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.PersistenceContext;
import tw.com.ispan.domain.ClassificationBean;
import tw.com.ispan.domain.IngredientBean;
import tw.com.ispan.domain.IngredientImageBean;
import tw.com.ispan.repository.IngredientRepository;

@Repository
public class IngredientDaoImpl implements IngredientRepository {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	// 查詢名字
	@Override
	public List<IngredientBean> findByIngredientName(String name) {

		try {
			return this.getSession()
					.createQuery("from IngredientBean where ingredientName like '%" + name + "%'", IngredientBean.class)
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
			IngredientBean temp = this.getSession().get(IngredientBean.class, id);
			if (temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}

	// 新增
	@Override
	public IngredientBean addInteger(IngredientBean bean) {
		try {
			if (bean != null && StringUtils.isNotBlank(bean.getIngredientName())) {
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
	public IngredientBean updateInteger(IngredientBean bean) {
		if (bean != null) {
			IngredientBean temp = this.getSession().get(IngredientBean.class, bean.getIngredientId());
			if (temp != null) {
				temp.setIngredientName(bean.getIngredientName());
				temp.setVegan(bean.getVegan());
				temp.setIngredientUrl(bean.getIngredientUrl());
				temp.setTips(bean.getTips());
				temp.setProductId(bean.getProductId());
				temp.setPrice(bean.getPrice());
				temp.setCalorie(bean.getCalorie());
				temp.setClassificationId(bean.getClassificationId());
			}
		}
		return bean;
	}

//查全部
	@Override
	public List<IngredientBean> findAll() {
		List<IngredientBean> Ingredient = this.getSession().createQuery("from IngredientBean", IngredientBean.class)
				.list();
		return Ingredient;
	}

//查分類id
	@Override
	public List<IngredientBean> findByclassificationId(Integer id) {
		try {
			return this.getSession().createQuery("from IngredientBean where classificationId=:id", IngredientBean.class)
					.setParameter("id", id).list();

			// .createQuery("from IngredientBean where classificationId = '"+id+"'",
			// IngredientBean.class)
			// .list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// 查食材id

	@Override
	public IngredientBean findByIngredientId(Integer id) {
		try {
			return this.getSession()
					.createQuery("from IngredientBean where ingredientId=:id", IngredientBean.class)
					.setParameter("id", id).uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public List<IngredientBean> findByIngredientIds(List<Integer> ids) {
		List<IngredientBean> ilists= new ArrayList<IngredientBean>();
		for(Integer id : ids) {
			IngredientBean ingredientBean = this.getSession().get(IngredientBean.class, id);
			ilists.add(ingredientBean);
		}
		return ilists;
	}
	
	



	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public <S extends IngredientBean> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends IngredientBean> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<IngredientBean> entities) {
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
	public IngredientBean getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IngredientBean getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IngredientBean getReferenceById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends IngredientBean> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends IngredientBean> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends IngredientBean> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IngredientBean> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends IngredientBean> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<IngredientBean> findById(Integer id) {
		// TODO Auto-generated method stub
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
	public void delete(IngredientBean entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends IngredientBean> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<IngredientBean> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<IngredientBean> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends IngredientBean> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends IngredientBean> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends IngredientBean> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends IngredientBean> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends IngredientBean, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
