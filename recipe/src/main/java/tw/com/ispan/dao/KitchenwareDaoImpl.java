package tw.com.ispan.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.json.JSONObject;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tw.com.ispan.domain.KitchenwareBean;




public class KitchenwareDaoImpl implements KitchenwareDao {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

//	@Override
//	public KitchenwareBean insert(KitchenwareBean bean) {
//		if(bean!=null && bean.getKitchenwareId()!=null) {
//			KitchenwareBean temp = this.getSession().get(KitchenwareBean.class, bean.getKitchenwareId());
//			if(temp==null) {
//				this.getSession().persist(bean);
//				return bean;
//			}
//		}
//		return null;
//	}
	
	
	
	@Override
	public KitchenwareBean update(KitchenwareBean bean) {
		if(bean!=null && bean.getKitchenwareId()!=null) {
			KitchenwareBean temp = this.getSession().get(KitchenwareBean.class, bean.getKitchenwareId());
			if(temp!=null) {
				return (KitchenwareBean) this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer KitchenwareId) {
		if(KitchenwareId!=null) {
			KitchenwareBean temp = this.getSession().get(KitchenwareBean.class, KitchenwareId);
			if(temp!=null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public KitchenwareBean findBykitchenwareId(Integer kitchenwareId) {
		if(kitchenwareId!=null) {
		 KitchenwareBean bean = this.getSession().get(KitchenwareBean.class, kitchenwareId);
		 return bean;
	}return null;
		}
	
	
	
	@Override
	public List<KitchenwareBean> findByName(String name) {
		String hql = "FROM KitchenwareBean WHERE kitchenwareName like :kitchenware_name";
		Query<KitchenwareBean> query = this.getSession().createQuery(hql, KitchenwareBean.class);
		query.setParameter("kitchenware_name", "%" + name + "%");
		List<KitchenwareBean> result = query.list();
		
		return result;
	}
	
	
	//查詢比數
	@Override
	public Long count(JSONObject obj) {
		Integer kitchenwareId = obj.isNull("kitchenwareId")? null : obj.getInt("kitchenwareId");
		String kitchenwareName = obj.isNull("kitchenwareName")? null : obj.getString("kitchenwareName");
		String kitchenwareCreateDate = obj.isNull("kitchenwareCreateDate")? null : obj.getString("kitchenwareCreateDate");
		String kitchenwareContent = obj.isNull("kitchenwareContent")? null : obj.getString("kitchenwareContent");
		Integer kitchenwarePrice = obj.isNull("kitchenwarePrice")? null : obj.getInt("kitchenwarePrice");
		String kitchenwareBrand = obj.isNull("kitchenwareBrand")? null : obj.getString("kitchenwareBrand");
		String kitchenwareMaterial = obj.isNull("kitchenwareMaterial")? null : obj.getString("kitchenwareMaterial");
		String kitchenwareOrigin = obj.isNull("kitchenwareOrigin")? null : obj.getString("kitchenwareOrigin");
		
		
//		  select count(*) from [kitchenware_img]
//					where  kitchenware_name like ? and kitchenware_price <= ? and kitchenware_brand like ? and kitchenware_material like ? and [kitchenware_origin] like ?

		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
//		from [kitchenware_img]
		 Root<KitchenwareBean> root = criteria.from(KitchenwareBean.class);

//		 select count(*)
		 criteria = criteria.select(builder.count(root));
		 
//		 where
		 List<Predicate> predicates = new ArrayList<>();
		
//		 kitchenware_name like ?
		 if(kitchenwareName!=null && kitchenwareName.length()!=0) {
			 predicates.add(builder.like(root.get("kitchenwareName"), "%"+kitchenwareName+"%"));
		 }
		
//		 kitchenware_price <= ?
		 if(kitchenwarePrice!=null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("kitchenwarePrice"), kitchenwarePrice));
			}
		
//		 kitchenware_brand like ?
		 if(kitchenwareBrand!=null && kitchenwareBrand.length()!=0) {
			 predicates.add(builder.like(root.get("kitchenwareBrand"), "%"+kitchenwareBrand+"%"));
		 }
		
//		 kitchenware_material like ?
		 if(kitchenwareMaterial!=null && kitchenwareMaterial.length()!=0) {
			 predicates.add(builder.like(root.get("kitchenwareMaterial"), "%"+kitchenwareMaterial+"%"));
		 }
		 
//		 [kitchenware_origin] like ?
		 if(kitchenwareOrigin!=null && kitchenwareOrigin.length()!=0) {
			 predicates.add(builder.like(root.get("kitchenwareOrigin"), "%"+kitchenwareOrigin+"%"));
		 }
		 
//		 where  kitchenware_name like ? and kitchenware_price <= ? and kitchenware_brand like ? and kitchenware_material like ? and [kitchenware_origin] like ?
		 if(predicates!=null && !predicates.isEmpty()) {
				Predicate[] array = predicates.toArray(new Predicate[1]);
				criteria = criteria.where(array);
			}
		 
		 TypedQuery<Long> typedQuery = this.getSession().createQuery(criteria);
			return typedQuery.getSingleResult();
	}
	
	
	//條件查詢(沒設條件就是查全部)
	@Override
	public List<KitchenwareBean> find(JSONObject obj){
		
		//查詢資料
		Integer kitchenwareId = obj.isNull("kitchenwareId")? null : obj.getInt("kitchenwareId");
		String kitchenwareName = obj.isNull("kitchenwareName")? null : obj.getString("kitchenwareName");
		String kitchenwareCreateDate = obj.isNull("kitchenwareCreateDate")? null : obj.getString("kitchenwareCreateDate");
		String kitchenwareContent = obj.isNull("kitchenwareContent")? null : obj.getString("kitchenwareContent");
		Integer kitchenwarePrice = obj.isNull("kitchenwarePrice")? null : obj.getInt("kitchenwarePrice");
		String kitchenwareBrand = obj.isNull("kitchenwareBrand")? null : obj.getString("kitchenwareBrand");
		String kitchenwareMaterial = obj.isNull("kitchenwareMaterial")? null : obj.getString("kitchenwareMaterial");
		String kitchenwareOrigin = obj.isNull("kitchenwareOrigin")? null : obj.getString("kitchenwareOrigin");
		
		
		//分頁
		String sort = obj.isNull("sort") ? "id" : obj.getString("sort"); //依什麼為排序依據
		String order = obj.isNull("order") ? "desc" : obj.getString("order"); //desc、asc
		Integer start = obj.isNull("start") ? 0 : obj.getInt("start"); //第幾筆資料開始
		Integer rows = obj.isNull("rows") ? null : obj.getInt("rows");	//一頁幾筆資料
		
		
		
//		  select count(*) from [kitchenware_img]
//		  where  kitchenware_name like ? and kitchenware_price <= ? and kitchenware_brand like ? and kitchenware_material like ? and [kitchenware_origin] like ?
//		  order by id desc
		
		
		
		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<KitchenwareBean> criteria = builder.createQuery(KitchenwareBean.class);

//		from [kitchenware_img]
		Root<KitchenwareBean> root = criteria.from(KitchenwareBean.class);
		
//		where
		List<Predicate> predicates = new ArrayList<>();
		
//		 kitchenware_name like ?
		 if(kitchenwareName!=null && kitchenwareName.length()!=0) {
			 predicates.add(builder.like(root.get("kitchenwareName"), "%"+kitchenwareName+"%"));
		 }
		
//		 kitchenware_price <= ?
		 if(kitchenwarePrice!=null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("kitchenwarePrice"), kitchenwarePrice));
			}
		
//		 kitchenware_brand like ?
		 if(kitchenwareBrand!=null && kitchenwareBrand.length()!=0) {
			 predicates.add(builder.like(root.get("kitchenwareBrand"), "%"+kitchenwareBrand+"%"));
		 }
		
//		 kitchenware_material like ?
		 if(kitchenwareMaterial!=null && kitchenwareMaterial.length()!=0) {
			 predicates.add(builder.like(root.get("kitchenwareMaterial"), "%"+kitchenwareMaterial+"%"));
		 }
		 
//		 [kitchenware_origin] like ?
		 if(kitchenwareOrigin!=null && kitchenwareOrigin.length()!=0) {
			 predicates.add(builder.like(root.get("kitchenwareOrigin"), "%"+kitchenwareOrigin+"%"));
		 }
		 
//		 where  kitchenware_name like ? and kitchenware_price <= ? and kitchenware_brand like ? and kitchenware_material like ? and [kitchenware_origin] like ?
		 if(predicates!=null && !predicates.isEmpty()) {
				Predicate[] array = predicates.toArray(new Predicate[1]);
				criteria = criteria.where(array);
			}
		 
//		 order by id desc
		 if("desc".equals(order)) {
				criteria = criteria.orderBy(builder.desc(root.get(sort)));
			} else {
				criteria = criteria.orderBy(builder.asc(root.get(sort)));
			}
		 
		 TypedQuery<KitchenwareBean> typedQuery = this.getSession().createQuery(criteria);
			if(rows!=null) {
				typedQuery = typedQuery.setFirstResult(start)
						.setMaxResults(rows);
			}
			
			List<KitchenwareBean> result = typedQuery.getResultList();
			if(result!=null && !result.isEmpty()) {
				return result;
			} else {
				return null;
			}	
	}

}
