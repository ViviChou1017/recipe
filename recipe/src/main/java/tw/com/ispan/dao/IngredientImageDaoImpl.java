package tw.com.ispan.dao;

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
import org.springframework.transaction.annotation.Transactional;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.PersistenceContext;
import tw.com.ispan.domain.IngredientBean;
import tw.com.ispan.domain.IngredientImageBean;
import tw.com.ispan.repository.IngredientImageRepository;

@Repository
public class IngredientImageDaoImpl implements IngredientImageRepository {
	@PersistenceContext

	private Session session;

	public Session getSession() {
		return session;
	}

//刪除
	@Override
	public boolean delInteger(Integer id) {
		if (id != null) {
			IngredientImageBean temp = this.getSession().get(IngredientImageBean.class, id);
			if (temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}

//新增
	@Override
	public IngredientImageBean addInteger(IngredientImageBean bean) {
		try {
			if (bean != null && StringUtils.isNotBlank(bean.getIngredientImage())) {
				this.getSession().persist(bean);
				// this.getSession().save(bean);
				return bean;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bean;
	}

//修改
	@Override
	public IngredientImageBean updateInteger(IngredientImageBean bean) {
		if (bean != null) {
			IngredientImageBean temp = this.getSession().get(IngredientImageBean.class, bean.getIngredientImageId());
			if (temp != null) {
				temp.setIngredientId(bean.getIngredientId());
				temp.setIngredientImage(bean.getIngredientImage());
			}
		}
		return bean;
	}

//查全部
	@Override
	public List<IngredientImageBean> findByIngredientImageAll() {
		List<IngredientImageBean> IngredientImage = this.getSession()
				.createQuery("from IngredientImageBean", IngredientImageBean.class).list();
		return IngredientImage;
	}

	// 查食材id
	@Override
	public IngredientImageBean findByIngredientImageId(Integer id) {
		try {
			return this.getSession()
					// .createQuery("from IngredientImageBean where ingredientId='"+id+"'",
					// IngredientImageBean.class)
					// .uniqueResult();

					.createQuery("from IngredientImageBean where ingredientId=:id", IngredientImageBean.class)
					.setParameter("id", id).uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// 查圖片id
	@Override
	public IngredientImageBean findByImageId(Integer id) {

		try {
			return this.getSession()
					.createQuery("from IngredientImageBean where ingredientImageId=:id", IngredientImageBean.class)
					.setParameter("id", id).uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public <S extends IngredientImageBean> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	};

	@Override
	public <S extends IngredientImageBean> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<IngredientImageBean> entities) {
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
	public IngredientImageBean getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IngredientImageBean getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IngredientImageBean getReferenceById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends IngredientImageBean> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends IngredientImageBean> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends IngredientImageBean> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IngredientImageBean> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends IngredientImageBean> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<IngredientImageBean> findById(Integer id) {
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
	public void delete(IngredientImageBean entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends IngredientImageBean> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<IngredientImageBean> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<IngredientImageBean> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends IngredientImageBean> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends IngredientImageBean> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends IngredientImageBean> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends IngredientImageBean> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends IngredientImageBean, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IngredientImageBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
