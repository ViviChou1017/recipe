package tw.com.ispan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.TrackDao;
import tw.com.ispan.domain.MemberBean;
import tw.com.ispan.domain.TrackBean;

@Repository
public interface TrackRepository extends
JpaRepository<TrackBean, Integer>,TrackDao {
	
	Boolean existsByTrackerNumberAndTrackedNumber(Integer trackerNumber, Integer trackedNumber);
	
	List<TrackBean> findByTrackerNumber(Integer trackerNumber);
	Integer deleteByTrackNumber(Integer trackNumber);

}
