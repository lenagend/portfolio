package model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class Novel {
/*
 "TITLE" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"EPISODE" NUMBER(5,0) DEFAULT 1 NOT NULL ENABLE, 
	"EMAIL" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"REGI_DATE" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"FINISH" VARCHAR2(10 BYTE) DEFAULT 'no' NOT NULL ENABLE, 
	"IMAGE" VARCHAR2(300 BYTE) NOT NULL ENABLE, 
	"ID" VARCHAR2(10 BYTE) DEFAULT 1 NOT NULL ENABLE, 
	"BLIND" VARCHAR2(20 BYTE) DEFAULT 'no' NOT NULL ENABLE, 
	"DESCRIPTION" VARCHAR2(500 BYTE), 
	 CONSTRAINT "NOVEL_PK" PRIMARY KEY ("ID")
 */
	private Integer id;
	private String title;
	private String email;
	private String regi_date;
	private String finish;
	private String image;
	private String blind;
	private String description;
	private Integer episode;
	private String type;
	private String epi_regi_date;
	private String nickname;
	
	private String w_icon_image;
	
	public String getW_icon_image() {
		return w_icon_image;
	}
	
	public void setW_icon_image(String w_icon_image) {
		this.w_icon_image = w_icon_image;
	}

	private Integer favorite_num;
	public Integer getFavorite_num() {
		return favorite_num;
	}
	public void setFavorite_num(Integer favorite_num) {
		this.favorite_num = favorite_num;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	public void setEpi_regi_date(String epi_regi_date) {
		this.epi_regi_date = epi_regi_date;
	}
	public String getEpi_regi_date() {
		return epi_regi_date;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	private MultipartFile imageFile;
	
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	
	public MultipartFile getImageFile() {
		return imageFile;
	}
	
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegi_date() {
		return regi_date;
	}
	public void setRegi_date(String regi_date) {
		this.regi_date = regi_date;
	}
	public String getFinish() {
		return finish;
	}
	public void setFinish(String finish) {
		this.finish = finish;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getBlind() {
		return blind;
	}
	public void setBlind(String blind) {
		this.blind = blind;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getEpisode() {
		return episode;
	}
	public void setEpisode(Integer episode) {
		this.episode = episode;
	}
	
}
