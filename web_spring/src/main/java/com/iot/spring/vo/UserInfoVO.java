package com.iot.spring.vo;

public class UserInfoVO {
	
	private int uiNo;
	private String uiName;
	private String uiId;
	private String uiPwd;
	private String uiAddress;
	public int getUiNo() {
		return uiNo;
	}
	public void setUiNo(int uiNo) {
		this.uiNo = uiNo;
	}
	public String getUiName() {
		return uiName;
	}
	public void setUiName(String uiName) {
		this.uiName = uiName;
	}
	public String getUiId() {
		return uiId;
	}
	public void setUiId(String uiId) {
		this.uiId = uiId;
	}
	public String getUiPwd() {
		return uiPwd;
	}
	public void setUiPwd(String uiPwd) {
		this.uiPwd = uiPwd;
	}
	public String getUiAddress() {
		return uiAddress;
	}
	public void setUiAddress(String uiAddress) {
		this.uiAddress = uiAddress;
	}
	@Override
	public String toString() {
		return "UserInfoVO [uiNo=" + uiNo + ", uiName=" + uiName + ", uiId=" + uiId + ", uiPwd=" + uiPwd
				+ ", uiAddress=" + uiAddress + "]";
	}
	
	

}
