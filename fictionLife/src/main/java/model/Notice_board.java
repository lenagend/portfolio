package model;

public class Notice_board {
/*
 *  CREATE TABLE "HR"."NOTICE_BOARD" 
   (	"BNO" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"ADMIN_ID" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"TITLE" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"CONTENT" VARCHAR2(4000 BYTE) NOT NULL ENABLE, 
	"REGI_DATE" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"BLIND" VARCHAR2(10 BYTE) DEFAULT 'no' NOT NULL ENABLE, 
	"NICKNAME" VARCHAR2(10 BYTE) NOT NULL ENABLE, 
 */
	private Integer bno;
	private Integer admin_id;
	private String title;
	private String content;
	private String regi_date;
	private String blind;
	private String nickname;
	private String icon;
	private String email;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegi_date() {
		return regi_date;
	}
	public void setRegi_date(String regi_date) {
		this.regi_date = regi_date;
	}
	public String getBlind() {
		return blind;
	}
	public void setBlind(String blind) {
		this.blind = blind;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
