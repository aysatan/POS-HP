
public class Operator {

	private String operatorID, operatorUsername, operatorPassword, operatorName, operatorPhoneNo, operatorEmail, role, supervisorPassword;
	
	public String getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	public String getOperatorUsername() {
		return operatorUsername;
	}

	public void setOperatorUsername(String operatorUsername) {
		this.operatorUsername = operatorUsername;
	}

	public String getOperatorPassword() {
		return operatorPassword;
	}

	public void setOperatorPassword(String operatorPassword) {
		this.operatorPassword = operatorPassword;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOpeartorPhoneNo() {
		return operatorPhoneNo;
	}

	public void setOpeartorPhoneNo(String opeartorPhoneNo) {
		this.operatorPhoneNo = opeartorPhoneNo;
	}

	public String getOperatorEmail() {
		return operatorEmail;
	}

	public void setOperatorEmail(String operatorEmail) {
		this.operatorEmail = operatorEmail;
	}
	
	public String getRole() {
		return operatorEmail;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getSupervisorPassword() {
		return supervisorPassword;
	}

	public void setSupervisorPassword(String SupervisorPassword) {
		this.supervisorPassword = SupervisorPassword;
	}
	
	public Operator(String operatorID, String operatorUsername, String operatorPassword, String operatorName,
			String operatorPhoneNo, String operatorEmail, String role, String supervisorPassword) {
		super();
		this.operatorID = operatorID;
		this.operatorUsername = operatorUsername;
		this.operatorPassword = operatorPassword;
		this.operatorName = operatorName;
		this.operatorPhoneNo = operatorPhoneNo;
		this.operatorEmail = operatorEmail;
		this.role = role;
		this.supervisorPassword = supervisorPassword;
	}

}
