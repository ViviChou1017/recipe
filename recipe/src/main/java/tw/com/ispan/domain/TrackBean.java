package tw.com.ispan.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "track")
public class TrackBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "track_number")
	private Integer trackNumber ;
	
	@Column(name = "tracked_number")
	private Integer trackedNumber;
	
	@Column(name = "tracker_number")
	private Integer trackerNumber;
	
	@Column(name = "track_date", insertable = false, updatable = false)
	private Date trackDate;

	public Integer getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(Integer trackNumber) {
		this.trackNumber = trackNumber;
	}

	public Integer getTrackedNumber() {
		return trackedNumber;
	}

	public void setTrackedNumber(Integer trackedNumber) {
		this.trackedNumber = trackedNumber;
	}

	public Integer getTrackerNumber() {
		return trackerNumber;
	}

	public void setTrackerNumber(Integer trackerNumber) {
		this.trackerNumber = trackerNumber;
	}


	public Date getTrackDate() {
		return trackDate;
	}

	public void setTrackDate(Date trackDate) {
		this.trackDate = trackDate;
	}

	@Override
	public String toString() {
		return "track [trackNumber=" + trackNumber + ", trackedNumber=" + trackedNumber + ", trackerNumber="
				+ trackerNumber + ", trackDate=" + trackDate + "]";
	}
	
	
	

}
