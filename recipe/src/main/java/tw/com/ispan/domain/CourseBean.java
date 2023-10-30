package tw.com.ispan.domain;

import java.util.Date;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course")
public class CourseBean {
	
	@Id
	@Column(name = "course_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseId ;
	
	@Column(name = "course_name",columnDefinition = "nvarchar")
	private String  courseName;
	
	@Column(name = "recipe_id")
	private Integer recipeId;
	
	@Column(name = "course_create_date")
	private Date courseCreateDate;
	
	@Column(name = "course_content",columnDefinition = "nvarchar")
	private String courseContent;
	
	@Column(name = "course_img",columnDefinition = "nvarchar")
	private String courseImg;
	
	@Column(name = "course_video",columnDefinition = "nvarchar")
	private String courseVideo;
	
	@Column(name = "course_price")
	private Integer coursePrice;
	
	@Column(name = "chef",columnDefinition = "nvarchar")
	private String chef;

	
	@Override
	public String toString() {
		return "CourseBean [courseId=" + courseId + ", courseName=" + courseName + ", recipeId=" + recipeId
				+ ", courseCreateDate=" + courseCreateDate + ", courseContent=" + courseContent + ", courseImg="
				+ courseImg + ", courseVideo=" + courseVideo + ", coursePrice=" + coursePrice + ", chef=" + chef + "]";
	}

	
	
	
	
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public Date getCourseCreateDate() {
		return courseCreateDate;
	}

	public void setCourseCreateDate(Date courseCreateDate) {
		this.courseCreateDate = courseCreateDate;
	}

	public String getCourseContent() {
		return courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}

	public String getCourseImg() {
		return courseImg;
	}

	public void setCourseImg(String courseImg) {
		this.courseImg = courseImg;
	}

	public String getCourseVideo() {
		return courseVideo;
	}

	public void setCourseVideo(String courseVideo) {
		this.courseVideo = courseVideo;
	}

	public Integer getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(Integer coursePrice) {
		this.coursePrice = coursePrice;
	}

	public String getChef() {
		return chef;
	}

	public void setChef(String chef) {
		this.chef = chef;
	}
	
	
	
	
}
