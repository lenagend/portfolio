package model;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Member {
/*
 "EMAIL" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"NICKNAME" VARCHAR2(10 BYTE) NOT NULL ENABLE, 
	"PHONE" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"REGI_DATE" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"PASSWORD" VARCHAR2(15 BYTE) NOT NULL ENABLE, 
	 CONSTRAINT "MEMBER_PK" PRIMARY KEY ("EMAIL")
 */
	@Email(message="이메일 형식으로 입력하세요")
	@NotEmpty(message="이메일을 입력하세요")
	private String email;
	private String nickname;
	private String phone;
	private String regi_date;
	@NotEmpty(message="비밀번호를 입력하세요")
	@Size(min=8, max=13, message="비밀번호는 8~13자입니다")
	private String password;
	private String newPassword;
	
	private String rePwd;
	
	private String blind;
	
	private String act;
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getBlind() {
		return blind;
	}
	public void setBlind(String blind) {
		this.blind = blind;
	}
	
	
	public String getRePwd() {
		return rePwd;
	}
	
	public void setRePwd(String rePwd) {
		this.rePwd = rePwd;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegi_date() {
		return regi_date;
	}
	public void setRegi_date(String regi_date) {
		this.regi_date = regi_date;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
