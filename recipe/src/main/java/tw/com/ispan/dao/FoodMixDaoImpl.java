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
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.ispan.domain.FoodMixBean;
import tw.com.ispan.domain.FoodMixIdBean;
import tw.com.ispan.repository.FoodMixRepository;

@Repository
public class FoodMixDaoImpl implements FoodMixRepository {
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	// 新增
	@Override
	public boolean addFoodMix(FoodMixBean bean) {
		try {
			if (bean != null && bean.getFoodMixId() != null) {
				FoodMixBean temp = this.getSession().get(FoodMixBean.class, bean.getFoodMixId());
				if (null == temp) {
					this.getSession().persist(bean);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 新增 多筆
	@Override
	public boolean addFoodMix(List<FoodMixBean> beans) {
		try {

			if (null == beans || beans.size() == 0) {
				return false;
			}

			for (FoodMixBean bean : beans) {
				FoodMixBean temp = this.getSession().get(FoodMixBean.class, bean.getFoodMixId());
				if (null == temp) {
					this.getSession().persist(bean);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//查食譜id
	@Override
	public List<FoodMixBean> findFoodByRecipeId(Integer id) {
		try {
			return this.getSession().createQuery("from FoodMixBean where foodMixId.recipeId=:recid", FoodMixBean.class)
					.setParameter("recid", id).list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//查食譜and分類id
	@Override
	public List<FoodMixBean> findFoodByRecipeIdAndClassificationById(Integer rId, Integer cId) {
		try {
			return this.getSession()
					.createQuery(
							"from FoodMixBean where foodMixId.recipeId=:recid and foodMixId.classificationId=:classid",
							FoodMixBean.class)
					.setParameter("recid", rId).setParameter("classid", cId).list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 查食譜and分類and食材id
	@Override
	public List<FoodMixBean> findFoodByRecipeIdAndClassificationAndIngredientById(Integer rId, Integer cId,
			Integer iId) {
		try {
			return this.getSession()
					.createQuery(
							"from FoodMixBean where foodMixId.recipeId=:recid and foodMixId.classificationId=:classid and foodMixId.ingredientId=:ingid",
							FoodMixBean.class)
					.setParameter("recid", rId).setParameter("classid", cId).setParameter("ingid", iId).list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 查料理法id
	@Override
	public List<FoodMixBean> findFoodByCookMethodById(Integer id) {
		try {
			return this.getSession().createQuery("from FoodMixBean where foodMixId.cookeryId=:cookid", FoodMixBean.class)
					.setParameter("cookid", id).list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	// 查食譜and分類and食材id and料理法id
	@Override
	public FoodMixBean findFoodByRecipeIdAndClassificationAndIngredientAndFoodByCookMethodById(Integer rId, Integer cId,
			Integer iId, Integer fid) {
		try {
			return this.getSession()
					.createQuery(
							"from FoodMixBean where foodMixId.recipeId=:recid and foodMixId.classificationId=:classid and foodMixId.ingredientId=:ingid and foodMixId.cookeryId=:cookid",
							FoodMixBean.class)
					.setParameter("recid", rId).setParameter("classid", cId).setParameter("ingid", iId).setParameter("cookid", fid).uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 更新
	@Override
	public boolean upDate(FoodMixBean bean) {
		try {
			this.getSession().update(bean);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//查食材id
		@Override
		public List<FoodMixBean> findFoodByIngredientId(Integer id) {
			try {
				return this.getSession().createQuery("from FoodMixBean where foodMixId.ingredientId=:iid", FoodMixBean.class)
						.setParameter("iid", id).list();

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
	public <S extends FoodMixBean> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends FoodMixBean> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<FoodMixBean> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	@Override
	public <S extends FoodMixBean> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends FoodMixBean> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends FoodMixBean> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FoodMixBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends FoodMixBean> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(FoodMixBean entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends FoodMixBean> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FoodMixBean> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FoodMixBean> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends FoodMixBean> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends FoodMixBean> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends FoodMixBean> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends FoodMixBean> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends FoodMixBean, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<FoodMixIdBean> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public FoodMixBean getOne(FoodMixIdBean id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodMixBean getById(FoodMixIdBean id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodMixBean getReferenceById(FoodMixIdBean id) {
		FoodMixBean bean = this.getSession().get(FoodMixBean.class, id);
		return bean;
	}

	@Override
	public List<FoodMixBean> findAllById(Iterable<FoodMixIdBean> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<FoodMixBean> findById(FoodMixIdBean id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(FoodMixIdBean id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteById(FoodMixIdBean id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllById(Iterable<? extends FoodMixIdBean> ids) {
		// TODO Auto-generated method stub

	}

	

	

}
