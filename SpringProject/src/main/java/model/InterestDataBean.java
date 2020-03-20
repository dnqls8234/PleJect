package model;

import java.io.Serializable;

public class InterestDataBean implements  Serializable {
	
	private String interNum; // 관심사cate id
	private String intersubNum; // 관심사sub id
	private String interestName; // 관심사tit
	
	
	public String getInterNum() {
		return interNum;
	}
	public void setInterNum(String interNum) {
		this.interNum = interNum;
	}
	public String getIntersubNum() {
		return intersubNum;
	}
	public void setIntersubNum(String intersubNum) {
		this.intersubNum = intersubNum;
	}
	public String getInterestName() {
		return interestName;
	}
	public void setInterestName(String interestName) {
		this.interestName = interestName;
	}
	
	
	@Override
	public String toString() {
		return "InterestDataBean [interNum=" + interNum + ", intersubNum=" + intersubNum + ", interestName="
				+ interestName + "]";
	}

	
	

}
