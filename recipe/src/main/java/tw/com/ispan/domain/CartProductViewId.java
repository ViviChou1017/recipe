package tw.com.ispan.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;


@Embeddable
public class CartProductViewId {
	@Column(name = "member_number")
	private Integer memberNumber;
	@Column(name = "item_id")
	private Integer itemId;
	@Override
	public String toString() {
		return "CartProductViewId [memberNumber=" + memberNumber + ", itemId=" + itemId + "]";
	}
	public Integer getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(Integer memberNumber) {
		this.memberNumber = memberNumber;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	
	
	

}
