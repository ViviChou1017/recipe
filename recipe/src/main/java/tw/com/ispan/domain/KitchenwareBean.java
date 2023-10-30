package tw.com.ispan.domain;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "kitchenware")
public class KitchenwareBean {
	
	@Id
	@Column(name = "kitchenware_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer kitchenwareId;
	
	@Column(name = "kitchenware_name",columnDefinition = "nvarchar")
	private String kitchenwareName;
	
	@Column(name = "kitchenware_create_date")
	private Date kitchenwareCreateDate;
	
	@Column(name = "kitchenware_content",columnDefinition = "nvarchar")
	private String kitchenwareContent;
	
	@Column(name = "kitchenware_price")
	private Integer kitchenwarePrice;
	
	@Column(name = "kitchenware_brand",columnDefinition = "nvarchar")
	private String kitchenwareBrand;
	
	@Column(name = "kitchenware_material",columnDefinition = "nvarchar")
	private String kitchenwareMaterial;
	
	@Column(name = "kitchenware_origin",columnDefinition = "nvarchar")
	private String kitchenwareOrigin;
	
	
	@Override
	public String toString() {
		return "KitchenwareBean [kitchenwareId=" + kitchenwareId + ", kitchenwareName=" + kitchenwareName
				+ ", kitchenwareCreateDate=" + kitchenwareCreateDate + ", kitchenwareContent=" + kitchenwareContent
				+ ", kitchenwarePrice=" + kitchenwarePrice + ", kitchenwareBrand=" + kitchenwareBrand
				+ ", kitchenwareMaterial=" + kitchenwareMaterial + ", kitchenwareOrigin=" + kitchenwareOrigin + "]";
	}

	
	
	public Integer getKitchenwareId() {
		return kitchenwareId;
	}

	public void setKitchenwareId(Integer kitchenwareId) {
		this.kitchenwareId = kitchenwareId;
	}

	public String getKitchenwareName() {
		return kitchenwareName;
	}

	public void setKitchenwareName(String kitchenwareName) {
		this.kitchenwareName = kitchenwareName;
	}

	public Date getKitchenwareCreateDate() {
		return kitchenwareCreateDate;
	}

	public void setKitchenwareCreateDate(Date kitchenwareCreateDate) {
		this.kitchenwareCreateDate = kitchenwareCreateDate;
	}

	public String getKitchenwareContent() {
		return kitchenwareContent;
	}

	public void setKitchenwareContent(String kitchenwareContent) {
		this.kitchenwareContent = kitchenwareContent;
	}

	public Integer getKitchenwarePrice() {
		return kitchenwarePrice;
	}

	public void setKitchenwarePrice(Integer kitchenwarePrice) {
		this.kitchenwarePrice = kitchenwarePrice;
	}

	public String getKitchenwareBrand() {
		return kitchenwareBrand;
	}

	public void setKitchenwareBrand(String kitchenwareBrand) {
		this.kitchenwareBrand = kitchenwareBrand;
	}

	public String getKitchenwareMaterial() {
		return kitchenwareMaterial;
	}

	public void setKitchenwareMaterial(String kitchenwareMaterial) {
		this.kitchenwareMaterial = kitchenwareMaterial;
	}

	public String getKitchenwareOrigin() {
		return kitchenwareOrigin;
	}

	public void setKitchenwareOrigin(String kitchenwareOrigin) {
		this.kitchenwareOrigin = kitchenwareOrigin;
	}
	
	
	

}
