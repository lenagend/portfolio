package model;

public class Icon {
/*
 * "ICON_ID" VARCHAR2(10 BYTE) DEFAULT 1 NOT NULL ENABLE, 
	"ICON_NAME" VARCHAR2(50 BYTE) NOT NULL ENABLE, 
	"ICON_IMAGE" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"ICON_DESC" VARCHAR2(300 BYTE) NOT NULL ENABLE, 
	"ICON_REGI_DATE" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"ADMIN_ID" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
 */
	private Integer icon_id;
	private String icon_name;
	private String icon_image;
	private String icon_desc;
	private String icon_regi_date;
	private Integer admin_id;

	public String getIcon_name() {
		return icon_name;
	}
	public void setIcon_name(String icon_name) {
		this.icon_name = icon_name;
	}
	public String getIcon_image() {
		return icon_image;
	}
	public void setIcon_image(String icon_image) {
		this.icon_image = icon_image;
	}
	public String getIcon_desc() {
		return icon_desc;
	}
	public void setIcon_desc(String icon_desc) {
		this.icon_desc = icon_desc;
	}
	public String getIcon_regi_date() {
		return icon_regi_date;
	}
	public void setIcon_regi_date(String icon_regi_date) {
		this.icon_regi_date = icon_regi_date;
	}
	public Integer getIcon_id() {
		return icon_id;
	}
	public void setIcon_id(Integer icon_id) {
		this.icon_id = icon_id;
	}
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}

	
	
}
