package tw.com.ispan.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "member")
public class MemberBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_number")
	private Integer memberNumber;
	
	@Column(name = "account",columnDefinition = "varchar")
	private String account;
	
	@Column(name = "member_password",columnDefinition = "varchar")
	private String memberPassword;
	
	@Column(name = "avatar",columnDefinition = "varchar")
	private String avatar;
	
	@Column(name = "member_name",columnDefinition = "varchar")
	private String memberName;
	
	@Column(name = "birthday",columnDefinition = "date")
	private Date birthday;
	
	@Column(name = "member_level")
	private Integer memberLevel;
	
	@Column(name = "view_count")
	private Integer viewCount;
	
	@Column(name = "phone" ,columnDefinition = "varchar")
	private String phone;
	
	@Column(name = "introduction",columnDefinition = "varchar")
	private String introduction;
	
	@Column(name = "gender", columnDefinition = "nvarchar")
	private String gender;
	
	@Column(name = "email", columnDefinition = "nvarchar")
	private String email;
	
	@Column(name = "address", columnDefinition = "nvarchar")
	private String address;
	
	@Column(name = "cellphone", columnDefinition = "nvarchar")
	private String cellphone;
	
	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Integer getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(Integer memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Override
	public String toString() {
		return "MemberBean [memberNumber=" + memberNumber + ", account=" + account + ", memberPassword="
				+ memberPassword + ", avatar=" + avatar + ", memberName=" + memberName + ", birthday=" + birthday
				+ ", memberLevel=" + memberLevel + ", viewCount=" + viewCount + ", phone=" + phone + ", introduction="
				+ introduction + "]";
	}
	
	

}
