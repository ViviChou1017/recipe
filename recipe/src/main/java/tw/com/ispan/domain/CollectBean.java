package tw.com.ispan.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "collect")
public class CollectBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "collect_number")
	private Integer collectNumber;
	
	@Column(name = "collector_number")
	private Integer collectorNumber;
	
	@Column(name = "recipe_number")
	private Integer recipeNumber;
	
	@Column(name = "collect_date")
	private Date collectDate;

	public Integer getCollectNumber() {
		return collectNumber;
	}

	public void setCollectNumber(Integer collectNumber) {
		this.collectNumber = collectNumber;
	}

	public Integer getCollectorNumber() {
		return collectorNumber;
	}

	public void setCollectorNumber(Integer collectorNumber) {
		this.collectorNumber = collectorNumber;
	}

	public Integer getRecipeNumber() {
		return recipeNumber;
	}

	public void setRecipeNumber(Integer recipeNumber) {
		this.recipeNumber = recipeNumber;
	}

	public Date getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}

	@Override
	public String toString() {
		return "collect [collectNumber=" + collectNumber + ", collectorNumber=" + collectorNumber + ", recipeNumber="
				+ recipeNumber + ", collectDate=" + collectDate + "]";
	}

}
