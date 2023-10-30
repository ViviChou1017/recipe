package tw.com.ispan.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cooking_method")
public class CookingMethodBean {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "method_name", columnDefinition = "nvarchar")
	private String methodName;
	@Column(name = "m_uploader_id")
	private Integer mUploaderId;
	@Column(name = "upload_date")
	private Date uploadDate;
	@Column(name = "pic", columnDefinition = "nvarchar")
	private String pic;
	@Column(name = "video_url", columnDefinition = "nvarchar")
	private String videoUrl;
	@Column(name = "introduction", columnDefinition = "nvarchar")
	private String introduction;
	
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Integer getmUploaderId() {
		return mUploaderId;
	}
	public void setmUploaderId(Integer mUploaderId) {
		this.mUploaderId = mUploaderId;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@Override
	public String toString() {
		return "CookingMethodBean [id=" + id + ", methodName=" + methodName + ", mUploaderId=" + mUploaderId
				+ ", uploadDate=" + uploadDate + ", pic=" + pic + ", videoUrl=" + videoUrl + ", introduction="
				+ introduction + "]";
	}
	
	

}

//CREATE TABLE [dbo].[cooking_method](
//[id] [int] IDENTITY(1,1) NOT NULL,
//[method_name] [nvarchar] (255) NOT NULL,
//[m_uploader_id] [int] NOT NULL,
//[upload_date] [datetime] NOT NULL,
//[pic] [nvarchar] (255) NULL,
//[video_url] [nvarchar](255) NULL,
//[introduction] [nvarchar](255) NULL,

