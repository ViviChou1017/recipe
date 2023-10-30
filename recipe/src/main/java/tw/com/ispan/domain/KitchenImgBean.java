package tw.com.ispan.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kitchen_img")
public class KitchenImgBean {

	@Id
	@Column(name = "kitchenware_img_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer kitchenwareImgId;
	
	@Column(name = "kitchenware_id")
	private Integer kitchenwareId;
	
	@Column(name = "kitchenware_img",columnDefinition = "nvarchar")
	private String kitchenwareImg;
	
	
	@Override
	public String toString() {
		return "KitchenImgBean [kitchenwareImgId=" + kitchenwareImgId + ", kitchenwareId=" + kitchenwareId
				+ ", kitchenwareImg=" + kitchenwareImg + "]";
	}

	public Integer getKitchenwareImgId() {
		return kitchenwareImgId;
	}

	public void setKitchenwareImgId(Integer kitchenwareImgId) {
		this.kitchenwareImgId = kitchenwareImgId;
	}

	public Integer getKitchenwareId() {
		return kitchenwareId;
	}

	public void setKitchenwareId(Integer kitchenwareId) {
		this.kitchenwareId = kitchenwareId;
	}

	public String getKitchenwareImg() {
		return kitchenwareImg;
	}

	public void setKitchenwareImg(String kitchenwareImg) {
		this.kitchenwareImg = kitchenwareImg;
	}
	
	
	
	
	
}


