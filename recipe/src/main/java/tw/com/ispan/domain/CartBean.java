package tw.com.ispan.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class CartBean {
//	[id] [int] IDENTITY(1,1) NOT NULL,
//	[user_acount_id] [int] NOT NULL,
//	[added_date] 
//	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "user_account_id")
	private Integer userAccountId;
	
	@Column(name = "added_date", columnDefinition = "date")
	private Date addedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	@Override
	public String toString() {
		return "CartBean [id=" + id + ", userAcountId=" + userAccountId + ", addedDate=" + addedDate + "]";
	}
	
	
}


