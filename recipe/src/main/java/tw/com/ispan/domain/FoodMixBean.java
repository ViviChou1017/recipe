package tw.com.ispan.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_mix")
public class FoodMixBean {
    
	@Id
	@EmbeddedId //指定keys为联合主键
	private FoodMixIdBean foodMixId;

	@Column(name = "ingredient_weight")
	private Integer ingredientWeight;
	

	public FoodMixIdBean getFoodMixId() {
		return foodMixId;
	}

	public void setFoodMixId(FoodMixIdBean foodMixId) {
		this.foodMixId = foodMixId;
	}

	public Integer getIngredientWeight() {
		return ingredientWeight;
	}

	public void setIngredientWeight(Integer ingredientWeight) {
		this.ingredientWeight = ingredientWeight;
	}


}
