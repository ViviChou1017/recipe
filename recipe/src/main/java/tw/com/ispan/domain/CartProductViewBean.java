package tw.com.ispan.domain;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_product_view")
public class CartProductViewBean {
	
	@EmbeddedId 
	CartProductViewId id;
//	@Column(name = "member_number")
//	private Integer memberNumber;
	@Column(name = "id")
	private Integer cartProductId;
	@Column(name = "quantity")
	private Integer quantity;
//	@Column(name = "item_id")
//	private Integer itemId;
	@Column(name = "kitchenware_id")
	private Integer kitchenwareId;
	@Column(name = "course_id")
	private Integer courseId;
	
	@Column(name = "kitchenware_name", columnDefinition = "nvarchar")
	private String kitchenwareName;
	@Column(name = "kitchenware_price")
	private Integer kitchenwarePrice;
	@Column(name = "kitchenware_img", columnDefinition = "nvarchar")
	private String kitchenwareImg;
	@Column(name = "course_name", columnDefinition = "nvarchar")
	private String courseName;
	@Column(name = "course_price")
	private Integer coursePrice;
	@Column(name = "course_img", columnDefinition = "nvarchar")
	private String courseImg;
	
	

	
	public CartProductViewId getId() {
		return id;
	}
	public void setId(CartProductViewId id) {
		this.id = id;
	}
	public String getKitchenwareName() {
		return kitchenwareName;
	}
	public void setKitchenwareName(String kitchenwareName) {
		this.kitchenwareName = kitchenwareName;
	}
	public Integer getKitchenwarePrice() {
		return kitchenwarePrice;
	}
	public void setKitchenwarePrice(Integer kitchenwarePrice) {
		this.kitchenwarePrice = kitchenwarePrice;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(Integer coursePrice) {
		this.coursePrice = coursePrice;
	}
	//	public Integer getMemberNumber() {
//		return memberNumber;
//	}
//	public void setMemberNumber(Integer memberNumber) {
//		this.memberNumber = memberNumber;
//	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
//	public Integer getItemId() {
//		return itemId;
//	}
//	public void setItemId(Integer itemId) {
//		this.itemId = itemId;
//	}
	public Integer getKitchenwareId() {
		return kitchenwareId;
	}
	public void setKitchenwareId(Integer kitchenwareId) {
		this.kitchenwareId = kitchenwareId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getCartProductId() {
		return cartProductId;
	}
	public void setCartProductId(Integer cartProductId) {
		this.cartProductId = cartProductId;
	}
	public String getKitchenwareImg() {
		return kitchenwareImg;
	}
	public void setKitchenwareImg(String kitchenwareImg) {
		this.kitchenwareImg = kitchenwareImg;
	}
	public String getCourseImg() {
		return courseImg;
	}
	public void setCourseImg(String courseImg) {
		this.courseImg = courseImg;
	}
	@Override
	public String toString() {
		return "CartProductViewBean [id=" + id + ", cartProductId=" + cartProductId + ", quantity=" + quantity
				+ ", kitchenwareId=" + kitchenwareId + ", courseId=" + courseId + ", kitchenwareName=" + kitchenwareName
				+ ", kitchenwarePrice=" + kitchenwarePrice + ", kitchenwareImg=" + kitchenwareImg + ", courseName="
				+ courseName + ", coursePrice=" + coursePrice + ", courseImg=" + courseImg + "]";
	}

	
	
	
	
	
	

}



