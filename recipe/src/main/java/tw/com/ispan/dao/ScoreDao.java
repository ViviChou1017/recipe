package tw.com.ispan.dao;

import java.util.List;

import tw.com.ispan.domain.ScoreBean;

public interface ScoreDao {

	public abstract List<ScoreBean> findAll(); //查全部
	
	public abstract ScoreBean findByRatNumber(Integer ratNumber);
	
	public abstract boolean deleteScore(Integer id); //刪除資料
	
	public abstract ScoreBean insertScore(ScoreBean bean); //新增資料
	
	public abstract ScoreBean updateScore(ScoreBean bean); //更新資料
}
