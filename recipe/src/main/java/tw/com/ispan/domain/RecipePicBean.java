package tw.com.ispan.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipe_pic")
public class RecipePicBean {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "recipe_id")
	private Integer recipeId;
	
	@Column(name = "pic_base64", columnDefinition = "nvarchar")
	private String picBase64;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}
	public String getPicBase64() {
		return picBase64;
	}
	public void setPicBase64(String picBase64) {
		this.picBase64 = picBase64;
	}
	@Override
	public String toString() {
		return "RecipePicBean [id=" + id + ", recipeId=" + recipeId + ", picBase64=" + picBase64 + "]";
	}
	
}
