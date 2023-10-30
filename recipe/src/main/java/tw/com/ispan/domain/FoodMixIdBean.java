package tw.com.ispan.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

public class FoodMixIdBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "recipe_id")
	private Integer recipeId;

	@Column(name = "ingredient_id")
	private Integer ingredientId;
	
	@Column(name = "cookery_id")
	private Integer cookeryId;
	
	@Column(name = "classification_id")
	private Integer classificationId;

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public Integer getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Integer ingredientId) {
		this.ingredientId = ingredientId;
	}

	public Integer getCookeryId() {
		return cookeryId;
	}

	public void setCookeryId(Integer cookeryId) {
		this.cookeryId = cookeryId;
	}

	public Integer getClassificationId() {
		return classificationId;
	}

	public void setClassificationId(Integer classificationId) {
		this.classificationId = classificationId;
	}
}
