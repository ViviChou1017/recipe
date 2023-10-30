package tw.com.ispan.domain;

import java.util.Date;
import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipe")
public class RecipeBean {
	
	

	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "recipe_name", columnDefinition = "nvarchar")
	private String recipeName;
	
	@Column(name = "uploader_id")
	private Integer uploaderId;
	
	@Column(name = "upload_date")
	private Date uploadDate;
	
	@Column(name = "category")
	private Integer category;
	
	@Column(name = "vage_category")
	private Integer vageCategory;
	
	@Column(name = "prep_time")
	private Integer prepTime;
	
	@Column(name = "introduction", columnDefinition = "nvarchar")
	private String introduction;
	
	@Column(name = "rank")
	private Integer rank;
	
	@Column(name = "video_url", columnDefinition = "nvarchar")
	private String videoUrl;

	@Column(name="step", columnDefinition = "nvarchar")
	private String step;

	
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	public Integer getUploaderId() {
		return uploaderId;
	}
	public void setUploaderId(Integer uploaderId) {
		this.uploaderId = uploaderId;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getVageCategory() {
		return vageCategory;
	}
	public void setVageCategory(Integer vageCategory) {
		this.vageCategory = vageCategory;
	}
	public Integer getPrepTime() {
		return prepTime;
	}
	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	@Override
	public String toString() {
		return "RecipeBean [id=" + id + ", recipeName=" + recipeName + ", uploaderId=" + uploaderId + ", uploadDate="
				+ uploadDate + ", category=" + category + ", vageCategory=" + vageCategory + ", prepTime=" + prepTime
				+ ", introduction=" + introduction + ", rank=" + rank + ", videoUrl=" + videoUrl + "]";
	}
	

}

//CREATE TABLE [dbo].[recipe](
//[id] [int] IDENTITY(1,1) NOT NULL,
//[recipe_name] [nvarchar] (255) NOT NULL,
//[uploader_id] [int] NOT NULL,
//[upload_date] [datetime] NOT NULL,
//[category] [int] NOT NULL,
//[vage_category] [int] NOT NULL,
//[prep_time] [int] NOT NULL,
//[introduction] [nvarchar](255) NOT NULL,
//[rank] [int] NOT NULL,
//[video_url] [nvarchar](255) NULL,




