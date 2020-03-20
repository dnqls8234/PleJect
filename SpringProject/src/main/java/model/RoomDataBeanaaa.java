package model;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class RoomDataBeanaaa implements  Serializable{

	private int num;
	private int like_ca;
	private String meet_title;
	private String photo;
	private String content;
	private String host;
	private int membercnt;
	private String location;
	

	

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getMembercnt() {
		return membercnt;
	}

	public void setMembercnt(int membercnt) {
		this.membercnt = membercnt;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "RoomDataBeanaaa [num=" + num + ", like_ca=" + like_ca + ", meet_title=" + meet_title + ", photo="
				+ photo + ", content=" + content + ", host=" + host + ", membercnt=" + membercnt + ", location="
				+ location + "]";
	}

	
}
