package tw.com.ispan.vo;

public class IngredientsVo {
	
	private String ingredientImage;
	
	private String ingredientName;
	
	private String tips;
	
	private int calorie;

	public String getIngredientImage() {
		return ingredientImage;
	}

	public void setIngredientImage(String ingredientImage) {
		this.ingredientImage = ingredientImage;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public int getCalorie() {
		return calorie;
	}

	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}

	@Override
	public String toString() {
		return "IngredientsVo [ingredientImage=" + ingredientImage + ", ingredientName=" + ingredientName + ", tips="
				+ tips + ", calorie=" + calorie + "]";
	}

	
	
}