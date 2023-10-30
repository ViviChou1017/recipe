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
@Table(name = "ingredient")
public class IngredientBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ingredient_id")
	private Integer ingredientId;

	@Column(name = "ingredient_name", columnDefinition = "nvarchar")
	private String ingredientName;

	@Column(name = "vegan")
	private Integer vegan;

	@Column(name = "ingredient_url", columnDefinition = "nvarchar")
	private String ingredientUrl;

	@Column(name = "tips", columnDefinition = "nvarchar")
	private String tips;

	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "price")
	private Integer price;

	@Column(name = "calorie")
	private Integer calorie;

	@Column(name = "classification_id")
	private Integer classificationId;

	public Integer getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Integer ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public Integer getVegan() {
		return vegan;
	}

	public void setVegan(Integer vegan) {
		this.vegan = vegan;
	}

	public String getIngredientUrl() {
		return ingredientUrl;
	}

	public void setIngredientUrl(String ingredientUrl) {
		this.ingredientUrl = ingredientUrl;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getCalorie() {
		return calorie;
	}

	public void setCalorie(Integer calorie) {
		this.calorie = calorie;
	}

	public Integer getClassificationId() {
		return classificationId;
	}

	public void setClassificationId(Integer classificationId) {
		this.classificationId = classificationId;
	}

	@Override
	public String toString() {
		return "IngredientBean [ingredientId=" + ingredientId + ", ingredientName=" + ingredientName + ", vegan="
				+ vegan + ", ingredientUrl=" + ingredientUrl + ", tips=" + tips + ", productId=" + productId
				+ ", price=" + price + ", calorie=" + calorie + ", classificationId=" + classificationId + "]";
	}

}
