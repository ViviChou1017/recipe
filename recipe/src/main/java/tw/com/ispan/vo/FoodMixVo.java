package tw.com.ispan.vo;

public class FoodMixVo {
	
	/**
	 * 食譜
	 */
	private Integer recipeId;
	/**
	 * 分類
	 */
	private Integer classificationId;
	/**
	 * 食材
	 */
	private Integer ingredientId;
	/**
	 * 料理法
	 */
	private Integer cookeryId;
	
	/**
	 * 份量
	 */
	private Integer ingredientWeight;
	
	/**
	 * 更新食材
	 */
	private Integer newIngredientId;

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public Integer getClassificationId() {
		return classificationId;
	}

	public void setClassificationId(Integer classificationId) {
		this.classificationId = classificationId;
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

	public Integer getNewIngredientId() {
		return newIngredientId;
	}

	public void setNewIngredientId(Integer newIngredientId) {
		this.newIngredientId = newIngredientId;
	}

	public Integer getIngredientWeight() {
		return ingredientWeight;
	}

	public void setIngredientWeight(Integer ingredientWeight) {
		this.ingredientWeight = ingredientWeight;
	}
}