package tw.com.ispan.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONObject;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.PersistenceContext;
import tw.com.ispan.domain.RecipeBean;
import tw.com.ispan.repository.RecipeRepository;

@Repository
@Transactional
public class RecipeDaoImpl implements RecipeDao {
	@PersistenceContext
	private Session session;
	
	
	public Session getSession() {
		return session;
	}

//	@Override
//	public Long count(JSONObject obj) {
//		String recipeName = obj.isNull("name") ? null : obj.getString("name");
//		Integer uploaderId = obj.isNull("memberNumber") ? null:obj.getInt("memberNumber");
//		String uploadDate = obj.isNull("uploadDate") ? null:obj.getString("uploadDate");
//		Integer category = obj.isNull("category") ? null:obj.getInt("category");
//		Integer vageCategory = obj.isNull("vageCategory") ? null:obj.getInt("vageCategory");
//		Integer prepTime = obj.isNull("prepTime") ? null:obj.getInt("prepTime");
//		String introduction = obj.isNull("introduction") ? null:obj.getString("introduction");
//		Integer rank = obj.isNull("rank") ? null:obj.getInt("rank");
//		String videoUrl = obj.isNull("videoUrl") ? null obj.getString(videoUrl);
//
//		return null;
//	}
//	
//	@Override
//	public List<RecipeBean> find(JSONObject obj) {
//		
//		return null;
//	}
	
	

	//查全部
	@Override
	public List<RecipeBean> findAll() {
		
		List<RecipeBean> Recipe = this.getSession().createQuery("from RecipeBean",RecipeBean.class).list();
		return Recipe;
	}

	//模糊查詢
	@Override
	public List<RecipeBean> findRecipeByName(String name) {
		List<RecipeBean> Recipe = this.getSession().createQuery("from RecipeBean where recipeName like '%"+name+"%'",RecipeBean.class).list();
		return Recipe;
	}

	//刪除
	@Override
	public boolean deleteRecipe(Integer id) {
		if(id != null) {
			RecipeBean temp = this.getSession().get(RecipeBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}

	//新增
//	@Override
//	public RecipeBean insertRecipe(RecipeBean bean) {
//		if(bean != null && bean.getRecipeName() != null)
//			this.getSession().persist(bean);
//			return null;
//	}

	
	//更新
	@Override
	public RecipeBean updateRecipe(RecipeBean bean) {
		if(bean != null && bean.getId() != null) {
			RecipeBean temp = this.getSession().get(RecipeBean.class, bean.getId());
			if(temp!=null) {
				return (RecipeBean) this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	
	//分類id搜尋
	public List<RecipeBean> findByRecipeCategory(Integer RecipeCategory) {
		Query<RecipeBean> query = this.getSession().createQuery("FROM RecipeBean WHERE category = :category",RecipeBean.class);
		query.setParameter("category",RecipeCategory );
		List<RecipeBean> result = query.list();
		return result;
	}

	
//	@Override
//	public void flush() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public <S extends RecipeBean> S saveAndFlush(S entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends RecipeBean> List<S> saveAllAndFlush(Iterable<S> entities) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deleteAllInBatch(Iterable<RecipeBean> entities) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAllInBatch() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public RecipeBean getOne(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public RecipeBean getById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public RecipeBean getReferenceById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends RecipeBean> List<S> findAll(Example<S> example) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends RecipeBean> List<S> findAll(Example<S> example, Sort sort) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends RecipeBean> List<S> saveAll(Iterable<S> entities) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<RecipeBean> findAllById(Iterable<Integer> ids) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends RecipeBean> S save(S entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Optional<RecipeBean> findById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean existsById(Integer id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public long count() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(RecipeBean entity) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAllById(Iterable<? extends Integer> ids) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAll(Iterable<? extends RecipeBean> entities) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAll() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<RecipeBean> findAll(Sort sort) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Page<RecipeBean> findAll(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends RecipeBean> Optional<S> findOne(Example<S> example) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends RecipeBean> Page<S> findAll(Example<S> example, Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends RecipeBean> long count(Example<S> example) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public <S extends RecipeBean> boolean exists(Example<S> example) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public <S extends RecipeBean, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
