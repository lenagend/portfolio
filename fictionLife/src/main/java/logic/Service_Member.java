package logic;

import javax.servlet.http.HttpSession;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import condition.PointCondition;
import model.Admin;
import model.Icon;
import model.Member;
import model.User_rank;

public interface Service_Member {
	// 로그인 회원가입 계정찾기
	Member loginResult(Member member);

	
	//랭크생성하고 가입까지
	public void newRankAndinsert(Member member,HttpSession session);
	

	Member checkEmail(String inputEmail);

	Integer checkNickname(String inputNickname);

	Member findEmailByPhone(String phone);

	public ModelAndView loginProcess(Member loginMember, HttpSession session, BindingResult br, String url);

	public void modifyMember(Member member);

	public void quitMember(String email);

	

	public Integer maxSeq();

	// 계급관련
	public User_rank getRank(String email);

	public Icon getIcon(Integer icon_id);

	public void updateReaders(User_rank ur);

	public void AddR_point(PointCondition pc);

	public void AddW_point(PointCondition pc);

	public void rankProcess(Member loginMember, HttpSession session);



	public String getW_icon_ImageByEmail(String email);

	public String getR_icon_ImageByNickname(String nickname);

	// 관리자
	Admin adminLogin(String email);

}
