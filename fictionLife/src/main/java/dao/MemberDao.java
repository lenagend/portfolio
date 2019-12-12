package dao;

import model.Admin;
import model.Member;

public interface MemberDao {
	Member getMember(Member member);
	
	void putMember(Member member);
	
	 Member checkEmail(String inputEmail);
	 
	 Integer checkNickname(String inputNickname);
	 
	 Member findEmailByPhone(String phone);

	 Admin adminLogin(String email);
	 
	 public void modifyMember(Member member);
	 
	 public void quitMember(String email);
}
