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
@Table(name = "orderlist")
public class OrderBean {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "order_date")
	private Date orderDate;
	
	@Column(name = "note", columnDefinition = "nvarchar")
	private String note;
	
	@Column(name = "delivery_id", columnDefinition = "nvarchar")
	private String deliveryId;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "credit_card_id")
	private Integer creditCardId;
	
	@Column(name = "member_id")
	private Integer memberId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
//	public List<String> getNote() {
//		return note;
//	}
//	
//	public void setNote(List<String> note) {
//		this.note = note;
//	}
//	public List<Object> getNote() {
//		return note;
//	}
//	
//	public void setNote(List<Object> note) {
//		this.note = note;
//	}

	public String getDeliveryId() {
		return deliveryId;
	}



	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCreditCardId() {
		return creditCardId;
	}

	public void setCreditCardId(Integer creditCardId) {
		this.creditCardId = creditCardId;
	}
	
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "OrderBean [id=" + id + ", orderDate=" + orderDate + ", note=" + note + ", deliveryId=" + deliveryId
				+ ", status=" + status + ", creditCardId=" + creditCardId + ", memberId=" + memberId + "]";
	}

	
	
	
	
	

}
