package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MyPageDataBean implements  Serializable{

	private int like_ca;
	private String meet_title;
	private String email;
	private String gender;
	private String name;
	private int classnum; 
	private String birthday;
	private int membercnt;
	private int status;
	private Timestamp regi_date;
	
	
	
	
	public int getLike_ca() {
		return like_ca;
	}
	public void setLike_ca(int like_ca) {
		this.like_ca = like_ca;
	}
	public String getMeet_title() {
		return meet_title;
	}
	public void setMeet_title(String meet_title) {
		this.meet_title = meet_title;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getClassnum() {
		return classnum;
	}
	public void setClassnum(int classnum) {
		this.classnum = classnum;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getMembercnt() {
		return membercnt;
	}
	public void setMembercnt(int membercnt) {
		this.membercnt = membercnt;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getRegi_date() {
		return regi_date;
	}
	public void setRegi_date(Timestamp regi_date) {
		this.regi_date = regi_date;
	}
}
