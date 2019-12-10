package model;

public class User_rank {
/*
 "EMAIL" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"W_POINT" VARCHAR2(20 BYTE) DEFAULT 1 NOT NULL ENABLE, 
	"R_POINT" VARCHAR2(20 BYTE) DEFAULT 1 NOT NULL ENABLE, 
	"SEQNO" VARCHAR2(10 BYTE) DEFAULT 1 NOT NULL ENABLE, 
	"RECO_POINT" NUMBER(2,0) DEFAULT 1 NOT NULL ENABLE, 
	"W_ICON_ID" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"R_ICON_ID" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	 CONSTRAINT "USER_RANK_PK" PRIMARY KEY ("SEQNO")
 */
	private String email;
	private Integer w_point;
	private Integer r_point;
	private Integer seq_no;
	private Integer reco_point;
	private Integer w_icon_id;
	private Integer r_icon_id;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getR_point() {
		return r_point;
	}
	public Integer getW_point() {
		return w_point;
	}
	public void setR_point(Integer r_point) {
		this.r_point = r_point;
	}
	public void setW_point(Integer w_point) {
		this.w_point = w_point;
	}
	public Integer getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(Integer seq_no) {
		this.seq_no = seq_no;
	}
	public Integer getReco_point() {
		return reco_point;
	}
	public void setReco_point(Integer reco_point) {
		this.reco_point = reco_point;
	}
	public Integer getW_icon_id() {
		return w_icon_id;
	}
	public void setW_icon_id(Integer w_icon_id) {
		this.w_icon_id = w_icon_id;
	}
	public Integer getR_icon_id() {
		return r_icon_id;
	}
	public void setR_icon_id(Integer r_icon_id) {
		this.r_icon_id = r_icon_id;
	}

	
}
