package tw.com.ispan.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "classification")
public class ClassificationBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "classification_id")
	private Integer classificationId;
	
	@Column(name = "classification_name", columnDefinition = "nvarchar")
	private String classificationName;

	public Integer getClassificationId() {
		return classificationId;
	}

	public void setClassificationId(Integer classificationId) {
		this.classificationId = classificationId;
	}

	public String getClassificationName() {
		return classificationName;
	}

	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}

	@Override
	public String toString() {
		return "ClassificationBean [classificationId=" + classificationId + ", classificationName=" + classificationName
				+ "]";
	}


	
	
}
