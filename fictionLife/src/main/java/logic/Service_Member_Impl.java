package logic;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.ModelAndView;

import condition.RankCondition;
import dao.MemberDao;
import dao.RankDao;
import model.Admin;
import model.Icon;
import model.Member;
import model.User_rank;

@Service
public class Service_Member_Impl implements Service_Member {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private RankDao rankDao;
	
	public void newRankAndinsert(Member member, HttpSession session) {

		member.setBlind("no");
		
		
		//유저계급 만드는 처리
	
		User_rank ur = new User_rank();
		ur.setEmail(member.getEmail());
		ur.setW_point(1);
		ur.setR_point(1);
		
		
		ur.setReco_point(1);
		ur.setW_icon_id(1);
		ur.setR_icon_id(2);
		
		rankDao.createRank(ur);
		memberDao.putMember(member);
		
		//세션에 랭크 정보
		RankCondition rc = new RankCondition();
		rc.setUr(ur);
		Icon wicon = this.getIcon(ur.getW_icon_id());
		Icon ricon = this.getIcon(ur.getR_icon_id());
		rc.setWicon(wicon);
		rc.setRicon(ricon);
		
	
		
		
		session.setAttribute("memberRank", rc);
		session.setAttribute("LOGINMEMBER", member);//세션에 이메일 정보
		session.setAttribute("LOGINRESULT", "loginResult.jsp");
		session.setAttribute("authEmail", null);
	}
	
	
	
	
	
	
	
	
	
	

	public Member loginResult(Member member) {

		return memberDao.getMember(member);
	}



	public Member checkEmail(String inputEmail) {
		return memberDao.checkEmail(inputEmail);
	}
	
	public Integer checkNickname(String inputNickname) {
	return memberDao.checkNickname(inputNickname);
	}

	
	

	public Integer maxSeq() {
		return rankDao.maxSeq();
	}

	public User_rank getRank(String email) {

		return rankDao.getRank(email);
	}

	public Icon getIcon(Integer icon_id) {
		return rankDao.getIcon(icon_id);
	}

	public Member findEmailByPhone(String phone) {
		return memberDao.findEmailByPhone(phone);
	}

	public void updateReaders(User_rank ur) {
		rankDao.updateReaders(ur);

	}

	public void AddR_point(String Email) {
		rankDao.AddR_point(Email);

	}

	public void AddW_point(String Email) {
		rankDao.AddW_point(Email);
	}

	public void rankProcess(Member loginMember, HttpSession session) {
		RankCondition rc = new RankCondition();
		User_rank ur = getRank(loginMember.getEmail());
		
		//포인트에 따른 계급 인설트
		
		this.updateRank(ur);					
		ur = getRank(loginMember.getEmail());//업데이트후 재검색하고 세션에 박아줌
		rc.setUr(ur);
		Icon wicon = getIcon(ur.getW_icon_id());
		Icon ricon = getIcon(ur.getR_icon_id());
		rc.setWicon(wicon);
		rc.setRicon(ricon);
		
	
		
		
		session.setAttribute("memberRank", rc);

	}
	public ModelAndView loginProcess(Member loginMember, HttpSession session, BindingResult br, String url) {
		ModelAndView mav = new ModelAndView("main");
		if(loginMember!=null) {
			if(loginMember.getBlind().equals("yes")) {
				//블라인드 유저
				FieldError fe = new FieldError("loginForm.jsp", "email", "블라인드된 유저입니다");
				br.addError(fe);		
				mav.addObject("BODY", "loginForm.jsp");
				return mav;
			}
			
			
			//로그인 성공
			rankProcess(loginMember, session);
			
			
			session.setAttribute("LOGINMEMBER", loginMember);//세션에 멤버 정보
			//세션에 랭크 정보
			Admin ad = this.adminLogin(loginMember.getEmail());
			if(ad!=null) {
				//관리자
				session.setAttribute("ADMIN", ad);//세션에 관리자 정보
			}
			
			
			session.setAttribute("LOGINRESULT", "loginResult.jsp");
			mav.addObject("BODY", null);
			mav.setViewName("loginAfterPage");
		
			mav.addObject("url", url);
			return mav;

		} else {
			// 로그인 실패시

			FieldError fe = new FieldError("loginForm.jsp", "email", "이메일과 패스워드를 확인해 주세요");
			br.addError(fe);
			mav.addObject("BODY", "loginForm.jsp");
			return mav;
		}

	}

	public void updateRank(User_rank ur) {

		if (ur.getR_point() > 99 && ur.getR_point() < 500) {
			// 아이콘 업데이트
			ur.setR_icon_id(4);
			ur.setReco_point(2);

		} else if (ur.getR_point() >= 500 && ur.getR_point() < 10000) {
			// 아이콘 업데이트
			ur.setR_icon_id(6);
			ur.setReco_point(3);

		} else if (ur.getR_point() >= 10000 && ur.getR_point() < 100000) {
			// 아이콘 업데이트
			ur.setR_icon_id(8);
			ur.setReco_point(4);

		} else if (ur.getR_point() >= 100000 && ur.getR_point() < 1000000) {
			// 아이콘 업데이트
			ur.setR_icon_id(10);
			ur.setReco_point(5);

		} else if (ur.getR_point() >= 1000000 && ur.getR_point() < 10000000) {
			// 아이콘 업데이트
			ur.setR_icon_id(12);
			ur.setReco_point(6);

		} else if (ur.getR_point() >= 10000000) {
			// 아이콘 업데이트
			ur.setR_icon_id(14);
			ur.setReco_point(7);

		}
		if (ur.getW_point() > 99 && ur.getW_point() < 500) {
			// 아이콘 업데이트
			ur.setW_icon_id(3);

		} else if (ur.getW_point() >= 500 && ur.getW_point() < 10000) {
			// 아이콘 업데이트
			ur.setW_icon_id(5);

		} else if (ur.getW_point() >= 10000 && ur.getW_point() < 100000) {
			// 아이콘 업데이트
			ur.setW_icon_id(7);

		} else if (ur.getW_point() >= 100000 && ur.getW_point() < 1000000) {
			// 아이콘 업데이트
			ur.setW_icon_id(9);

		} else if (ur.getW_point() >= 1000000 && ur.getW_point() < 10000000) {
			// 아이콘 업데이트
			ur.setW_icon_id(11);

		} else if (ur.getW_point() >= 10000000) {
			// 아이콘 업데이트
			ur.setW_icon_id(13);

		}

		updateReaders(ur);

	}

	public Admin adminLogin(String email) {

		return memberDao.adminLogin(email);
	}

	public void AddW_point5(String Email) {
		rankDao.AddW_point5(Email);

	}

	public String getR_icon_ImageByNickname(String nickname) {

		return rankDao.getR_icon_ImageByNickname(nickname);
	}

	public String getW_icon_ImageByEmail(String email) {

		return rankDao.getW_icon_ImageByEmail(email);
	}

	public void modifyMember(Member member) {
		memberDao.modifyMember(member);

	}

	public void quitMember(String email) {
		memberDao.quitMember(email);

	}
	
	
	
	
	
	
	
}
