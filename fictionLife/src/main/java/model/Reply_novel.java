package model;

public class Reply_novel {
/*
 *"RNO" NUMBER DEFAULT 1 NOT NULL ENABLE, 
	"BNO" NUMBER NOT NULL ENABLE, 
	"CONTENT" VARCHAR2(300 BYTE) NOT NULL ENABLE, 
	"NICKNAME" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"REGI_DATE" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"UPDATE_DATE" VARCHAR2(20 BYTE), 
	"BLIND" VARCHAR2(10 BYTE) DEFAULT 'no' NOT NULL ENABLE, 
	"R_POINT" NUMBER(20,0) NOT NULL ENABLE, 
	"PARENT_NO" NUMBER, 
	"ORDER_NO" NUMBER, 
 */
	
	private Integer rno;
	private Integer bno;
	private String content;
	private String nickname;
	private String regi_date;
	private String update_date;
	private String blind;
	private Integer r_point;
	private Integer parent_no;
	private Integer order_no;
	private Integer repl_cnt;
	
	private String r_icon_image;
	
	public String getR_icon_image() {
		return r_icon_image;
	}
	
	public void setR_icon_image(String r_icon_image) {
		this.r_icon_image = r_icon_image;
	}
	
	public Integer getRepl_cnt() {
		return repl_cnt;
	}
	
	public void setRepl_cnt(Integer repl_cnt) {
		this.repl_cnt = repl_cnt;
	}
	
	public Integer getRno() {
		return rno;
	}
	public void setRno(Integer rno) {
		this.rno = rno;
	}
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRegi_date() {
		return regi_date;
	}
	public void setRegi_date(String regi_date) {
		this.regi_date = regi_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public String getBlind() {
		return blind;
	}
	public void setBlind(String blind) {
		this.blind = blind;
	}
	public Integer getR_point() {
		return r_point;
	}
	public void setR_point(Integer r_point) {
		this.r_point = r_point;
	}
	public Integer getParent_no() {
		return parent_no;
	}
	public void setParent_no(Integer parent_no) {
		this.parent_no = parent_no;
	}
	public Integer getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}
	
	
}
