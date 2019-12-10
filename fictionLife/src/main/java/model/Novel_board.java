package model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Novel_board {
/*
 * 
 *  CREATE TABLE "HR"."NOVEL_BOARD" 
   (	"BNO" VARCHAR2(20 BYTE) DEFAULT 1 NOT NULL ENABLE, 
	"EPI_TITLE" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"CONTENT" VARCHAR2(4000 BYTE) NOT NULL ENABLE, 
	"REGI_DATE" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"VIEW_CNT" VARCHAR2(5 BYTE) DEFAULT 0 NOT NULL ENABLE, 
	"RECO_CNT" VARCHAR2(5 BYTE) DEFAULT 0 NOT NULL ENABLE, 
	"REPL_CNT" VARCHAR2(5 BYTE) DEFAULT 0 NOT NULL ENABLE, 
	"BLIND" VARCHAR2(10 BYTE) DEFAULT 'no' NOT NULL ENABLE, 
	"NOVEL_ID" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
 */
	private Integer bno;
	private String epi_title;	
	private String content;	
	private String regi_date;
	private Integer view_cnt;
	private Integer reco_cnt;
	private Integer repl_cnt;
	private String blind;
	private Integer novel_id;
	private Integer epi_number;
	public void setEpi_number(Integer epi_number) {
		this.epi_number = epi_number;
	}
	public Integer getEpi_number() {
		return epi_number;
	}
	
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public Integer getNovel_id() {
		return novel_id;
	}
	public void setNovel_id(Integer novel_id) {
		this.novel_id = novel_id;
	}
	
	public String getEpi_title() {
		return epi_title;
	}
	public void setEpi_title(String epi_title) {
		this.epi_title = epi_title;
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

	public Integer getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(Integer view_cnt) {
		this.view_cnt = view_cnt;
	}
	public Integer getReco_cnt() {
		return reco_cnt;
	}
	public void setReco_cnt(Integer reco_cnt) {
		this.reco_cnt = reco_cnt;
	}
	public Integer getRepl_cnt() {
		return repl_cnt;
	}
	public void setRepl_cnt(Integer repl_cnt) {
		this.repl_cnt = repl_cnt;
	}
	public String getBlind() {
		return blind;
	}
	public void setBlind(String blind) {
		this.blind = blind;
	}
	
	
	
}
