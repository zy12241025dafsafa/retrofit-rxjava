package com.meiya.cunnar.elecsignner;

public class UserGroup {

	int status;//状态 0:未审核 1:审核通过 2:审核不通过
	String groupName;
	String remark;//审核不通过原因
	//义务交警队相关
	String traffic;//义务交警队
	String trafficCode;
	String email;
	String emergency;//紧急联系人电话
	//义务反扒队相关
	String busStation;
	String busRoute;

	String code;

	public String getTrafficCode() {
		return trafficCode;
	}

	public void setTrafficCode(String trafficCode) {
		this.trafficCode = trafficCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String getBusStation() {
		return busStation;
	}

	public void setBusStation(String busStation) {
		this.busStation = busStation;
	}

	public String getBusRoute() {
		return busRoute;
	}

	public void setBusRoute(String busRoute) {
		this.busRoute = busRoute;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	@Override
	public String toString() {
		return "UserGroup [status=" + status + ", groupName=" + groupName
				+ ", remark=" + remark + ", busStation=" + busStation
				+ ", busRoute=" + busRoute + ", traffic=" + traffic
				+ ", email=" + email + ", emergency=" + emergency + "]";
	}
}
