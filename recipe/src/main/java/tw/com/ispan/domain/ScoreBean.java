package tw.com.ispan.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "score")
public class ScoreBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rat_number")
	private Integer ratNumber;
	
	@Column(name = "rater_number")
	private Integer raterNumber;

	@Column(name = "recipe_number")
	private Integer recipeNumber;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "rat_date")
	private Date ratDate;
	
	@Column(name = "score")
	private Integer score;


	public Integer getRatNumber() {
		return ratNumber;
	}

	public void setRatNumber(Integer ratNumber) {
		this.ratNumber = ratNumber;
	}

	public Integer getRaterNumber() {
		return raterNumber;
	}

	public void setRaterNumber(Integer raterNumber) {
		this.raterNumber = raterNumber;
	}

	public Integer getRecipeNumber() {
		return recipeNumber;
	}

	public void setRecipeNumber(Integer recipeNumber) {
		this.recipeNumber = recipeNumber;
	}

	public Date getRatDate() {
		return ratDate;
	}

	public void setRatDate(Date ratDate) {
		this.ratDate = ratDate;
	}

	public Integer getScore() {
		return score;
	}
	
	public void setScore(Integer score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "score [ratNumber=" + ratNumber + ", raterNumber=" + raterNumber + ", recipeNumber=" + recipeNumber
				+ ", ratDate=" + ratDate + "]";
	}

}
