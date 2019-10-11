package com.owen.bean;

/**
 * Created by tengj on 2017/3/29.
 */
public class User {
    private String FUserName;
    private String FPassword;
    private Integer FUserId;
    private String FCreateTime ;
    private Integer FIsAdmin = 0;
	public String getFUserName() {
		return FUserName;
	}
	public void setFUserName(String fUserName) {
		FUserName = fUserName;
	}
	public String getFPassword() {
		return FPassword;
	}
	public void setFPassword(String fPassword) {
		FPassword = fPassword;
	}
	public Integer getFUserId() {
		return FUserId;
	}
	public void setFUserId(Integer fUserId) {
		FUserId = fUserId;
	}
	public String getFCreateTime() {
		return FCreateTime;
	}
	public void setFCreateTime(String fCreateTime) {
		FCreateTime = fCreateTime;
	}
	public Integer getFIsAdmin() {
		return FIsAdmin;
	}
	public void setFIsAdmin(Integer fIsAdmin) {
		FIsAdmin = fIsAdmin;
	}
	@Override
	public String toString() {
		return "User [FUserName=" + FUserName + ", FPassword=" + FPassword + ", FUserId=" + FUserId + ", FCreateTime="
				+ FCreateTime + ", FIsAdmin=" + FIsAdmin + "]";
	}

    
}