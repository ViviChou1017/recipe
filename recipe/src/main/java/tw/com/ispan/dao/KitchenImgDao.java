package tw.com.ispan.dao;

import java.util.List;

import tw.com.ispan.domain.KitchenImgBean;

public interface KitchenImgDao {
	public KitchenImgBean update(KitchenImgBean bean);
	
	public List<KitchenImgBean> findBykitchenwareId(Integer kitchenwareId);
}
