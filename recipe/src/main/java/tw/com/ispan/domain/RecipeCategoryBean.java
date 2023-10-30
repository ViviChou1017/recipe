package tw.com.ispan.domain;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipe_category")
public class RecipeCategoryBean {

	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "recipe_category", columnDefinition = "nvarchar")
	private String recipeCategory;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRecipeCategory() {
		return recipeCategory;
	}
	public void setRecipeCategory(String recipeCategory) {
		this.recipeCategory = recipeCategory;
	}
	@Override
	public String toString() {
		return "RecipeCategoryBean [id=" + id + ", recipeCategory=" + recipeCategory + "]";
	}
	
	
	

}

//CREATE TABLE [dbo].[recipe_category](
//		[id] [int] IDENTITY(1,1) NOT NULL,
//		[recipe_category] [nvarchar] (255) NOT NULL,
