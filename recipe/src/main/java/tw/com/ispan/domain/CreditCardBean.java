package tw.com.ispan.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "credit_card")
public class CreditCardBean {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "card_name", columnDefinition = "nvarchar")
	private String cardName;
	
	@Column(name = "expire", columnDefinition = "nvarchar")
	private String expire;
	
	@Column(name = "bank_number")
	private String bankNumber;
	
	@Column(name = "owner_name", columnDefinition = "nvarchar")
	private String ownerName;
	
	@Column(name = "user_account_id")
	private Integer userAccountId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

	@Override
	public String toString() {
		return "CreditCardBean [id=" + id + ", cardName=" + cardName + ", expire=" + expire + ", bankNumber="
				+ bankNumber + ", ownerName=" + ownerName + ", userAccountId=" + userAccountId + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
