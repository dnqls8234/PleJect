package model;

import java.io.Serializable;

public class LogonDataBean implements  Serializable{
	
	private int memid;
	public int getMemid() {
		return memid;
	}
	public void setMemid(int memid) {
		this.memid = memid;
	}

	private String email; // 회원이메일
	private String gender; // 성별
	private String profileImg; // 파일이미지s
	private String name; // 닉네임
	private String password; // 비밀번호
	private String phone; // 전화번호
	private String birthday; // 생일
	private String address; // 내지역
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "LogonDataBean [memid=" + memid + ", email=" + email + ", gender=" + gender + ", profileImg="
				+ profileImg + ", name=" + name + ", password=" + password + ", phone=" + phone + ", birthday="
				+ birthday + ", address=" + address + "]";
	}

	
	
}
