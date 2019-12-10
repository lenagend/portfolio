package condition;

import model.Icon;
import model.User_rank;

public class RankCondition {
private User_rank ur;
private Icon wicon;
private Icon ricon;


public User_rank getUr() {
	return ur;
}
public void setUr(User_rank ur) {
	this.ur = ur;
}
public void setWicon(Icon wicon) {
	this.wicon = wicon;
}
public Icon getWicon() {
	return wicon;
}

public Icon getRicon() {
	return ricon;
}
public void setRicon(Icon ricon) {
	this.ricon = ricon;
}



}
