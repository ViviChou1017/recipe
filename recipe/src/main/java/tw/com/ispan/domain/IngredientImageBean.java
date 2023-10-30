package tw.com.ispan.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingredient_image")
public class IngredientImageBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ingredient_image_id")
	private Integer ingredientImageId;

	@Column(name = "ingredient_id")
	private Integer ingredientId;

	@Column(name = "ingredient_image", columnDefinition = "nvarchar")
	private String ingredientImage;

	public Integer getIngredientImageId() {
		return ingredientImageId;
	}

	public void setIngredientImageId(Integer ingredientImageId) {
		this.ingredientImageId = ingredientImageId;
	}

	public Integer getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Integer ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getIngredientImage() {
		return ingredientImage;
	}

	public void setIngredientImage(String ingredientImage) {
		this.ingredientImage = ingredientImage;
	}

	@Override
	public String toString() {
		return "IngredientImageBean [ingredientImageId=" + ingredientImageId + ", ingredientId=" + ingredientId
				+ ", ingredientImage=" + ingredientImage + "]";
	}

}
