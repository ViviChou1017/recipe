package tw.com.ispan.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class ItemBean {

	@Id
	@Column(name = "item_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemId;

	@Column(name = "kitchenware_id")
	private Integer kitchenwareId;

	@Column(name = "course_id")
	private Integer courseId;

//	@Column(name = "item_sort")
//	private Integer itemSort;
//	
//	@Column(name = "item_number")
//	private Integer itemNumber;

	public Integer getItemId() {
		return itemId;
	}

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

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "ItemBean [itemId=" + itemId + ", kitchenwareId=" + kitchenwareId + ", courseId=" + courseId + "]";
//	public Integer getKitchenwareId() {
//		return kitchenwareId;
//	}
//
//	public void setKitchenwareId(Integer kitchenwareId) {
//		this.kitchenwareId = kitchenwareId;
//	}
//
//	public Integer getCourseId() {
//		return courseId;
//	}
//
//	public void setCourseId(Integer courseId) {
//		this.courseId = courseId;
//	}

//	public Integer getItemSort() {
//		return itemSort;
//	}
//
//	public void setItemSort(Integer itemSort) {
//		this.itemSort = itemSort;
//	}
//
//	public Integer getItemNumber() {
//		return itemNumber;
//	}
//
//	public void setItemNumber(Integer itemNumber) {
//		this.itemNumber = itemNumber;
//	}
//	@Override
//	public String toString() {
//		return "ItemBean [itemId=" + itemId + ", itemSort=" + itemSort + ", itemNumber=" + itemNumber + "]";
//	}

	}
}
