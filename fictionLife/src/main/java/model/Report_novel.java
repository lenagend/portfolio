package model;

public class Report_novel {
/*
 * 	"SEQNO" NUMBER, 
	"EMAIL" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"R_BNO" NUMBER NOT NULL ENABLE, 
	"TITLE" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"CONTENT" VARCHAR2(4000 BYTE) NOT NULL ENABLE, 
	"REGI_DATE" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
 */
	private Integer seqno;
	private String email;
	private Integer r_bno;
	private String title;
	private String content;
	private String regi_date;
	private Integer novel_id;
	private Integer epi;
	
	public Integer getEpi() {
		return epi;
	}
	public void setEpi(Integer epi) {
		this.epi = epi;
	}
	
	public Integer getNovel_id() {
		return novel_id;
	}
	public void setNovel_id(Integer novel_id) {
		this.novel_id = novel_id;
	}
	
	public Integer getSeqno() {
		return seqno;
	}
	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getR_bno() {
		return r_bno;
	}
	public void setR_bno(Integer r_bno) {
		this.r_bno = r_bno;
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
	
	
	
}
