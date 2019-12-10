package controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import condition.EmailCondition;
import condition.RankCondition;
import logic.Service_Member;
import model.Icon;
import model.Member;
import model.User_rank;

@Controller
public class LoginController {
	@Autowired
	private Service_Member sm;
	@Autowired
	 JavaMailSender mailSender;  
//	
//	
	
//
	@RequestMapping(value="/login/login.html")
	public ModelAndView doLogin(@Valid Member member, BindingResult br, HttpSession session) {
		ModelAndView mav = new ModelAndView("main");
		
			String url = (String)session.getAttribute("redirectURI");
		
			//폼체크
			if(br.hasErrors()) {
				mav.addObject("BODY", "loginForm.jsp");
			
				return mav;
			}
			
			
			//여기서부터 로그인 성공 확인
			Member loginMember = sm.loginResult(member);
		
			
			return sm.loginProcess(loginMember, session, br, url);
			
			
		
	}

	
	@RequestMapping(value="/login/logout.html")
	public ModelAndView logout(HttpSession session) {
	
		session.invalidate();
		return new ModelAndView("logoutAfter");
		
	}
	
	
	
	@RequestMapping(value="/login/emailAuth.html")
	public ModelAndView checkEmail(@Valid EmailCondition emailCondition, BindingResult br, HttpSession session) {
		ModelAndView mav = new ModelAndView("main");
		//폼체크
		if(br.hasErrors()) {
			mav.addObject("BODY", "email.jsp");
			return mav;
		}
		
		//먼저 유저가 입력한 이메일이 이미 등록되어있는지 확인
		if(sm.checkEmail(emailCondition.getEmail())!=null) {
			//중복일경우
			FieldError fe = new FieldError("email.jsp", "email", "이미 등록되어있는 이메일 입니다");
			br.addError(fe);		
			mav.addObject("BODY", "email.jsp");
			return mav;
			
		}else {
			
			
		
			Random r = new Random();
			int dice = r.nextInt(4589362) + 49311;//이메일로 받는 인증코드 난수
			emailCondition.setDice(dice);
			String setfrom = "119lenagend119@gamil.com";
	        String tomail = emailCondition.getEmail(); // 받는 사람 이메일
	        String title = "회원가입 인증 이메일 입니다."; // 제목
	        String content =
	        		 System.getProperty("line.separator")+ //한줄씩 줄간격을 두기위해 작성
	                 
	                 System.getProperty("line.separator")+
	                         
	                 "안녕하세요 Fictiln Life를 찾아주셔서 감사합니다"
	                 
	                 +System.getProperty("line.separator")+
	                 
	                 System.getProperty("line.separator")+
	         
	                 " 인증번호는 " +dice+ " 입니다. "
	                 
	                 +System.getProperty("line.separator")+
	                 
	                 System.getProperty("line.separator")+
	                 
	                 "받으신 인증번호를 홈페이지에 입력해 주시면 다음으로 넘어갑니다."; // 내용
			
	        try {
	            MimeMessage message = mailSender.createMimeMessage();
	            MimeMessageHelper messageHelper = new MimeMessageHelper(message,
	                    true, "UTF-8");

	            messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
	            messageHelper.setTo(tomail); // 받는사람 이메일
	            messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
	            messageHelper.setText(content); // 메일 내용
	            
	            mailSender.send(message);
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        
	       
	        mav.addObject("BODY", "emailCheck.jsp");
	        
	        mav.addObject("emailCondition", emailCondition);
			return mav;
		}
	
	}
	
	@RequestMapping(value="/login/emailResult.html" ,method=RequestMethod.POST)
	public ModelAndView numberCheck(EmailCondition emailCondition, BindingResult br, HttpSession session) {
		
		ModelAndView mav = new ModelAndView("main");
		
		//이메일 발신성공 후 이메일의 숫자와 유저입력숫자가 맞는지(지금 일일이 입력해야되니까 나중에 활성ㅎ
		if(emailCondition.getDice().equals(emailCondition.getUserInput())) {
			//맞는경우 회원가입폼으로 인증완료한 이메일 가지고
			

			session.setAttribute("authEmail", emailCondition.getEmail());
			mav.addObject(new Member());
			mav.addObject("BODY", "register.jsp");
			return mav;
			
		}else {
			
			FieldError fe = new FieldError("emailCheck.jsp", "userInput", "인증번호가 다릅니다");
			br.addError(fe);		
			mav.addObject("BODY", "emailCheck.jsp");
			return mav;
			
			
		}
		
		
	}
	

	@RequestMapping(value="/login/doRegi.html" )
	public ModelAndView numberCheck(@Valid Member member, BindingResult br, HttpSession session) {
		ModelAndView mav = new ModelAndView("main");
		

			if(br.hasErrors()) { //단순폼체크
				mav.addObject("BODY", "register.jsp");
			
				return mav;
			}
			if(! member.getPassword().equals(member.getRePwd())) {
				//비밀번호 확인 결과 다름
				FieldError fe = new FieldError("register.jsp", "password", "비밀번호 값이 일치하지 않습니다");
				br.addError(fe);
				mav.addObject("BODY", "register.jsp");
				return mav;
			}
			
			if(member.getPhone().length()>11||member.getPhone().length()<9) {
				//전화번호 길이
				FieldError fe = new FieldError("register.jsp", "phone", "올바른 전화번호를 입력해 주세요");
				br.addError(fe);
				mav.addObject("BODY", "register.jsp");
				return mav;
			}
			if(member.getNickname().length()>8||member.getNickname().length()<2) {
				//닉네임 글자수 
				FieldError fe = new FieldError("register.jsp", "nickname", "닉네임의 길이는 2자이상 8자 이하입니다");
				br.addError(fe);
				mav.addObject("BODY", "register.jsp");
				return mav;
			}
			if(sm.checkNickname(member.getNickname())!=0) {
				//닉네임 중복됨
				FieldError fe = new FieldError("register.jsp", "nickname", "닉네임이 중복됩니다");
				br.addError(fe);
				mav.addObject("BODY", "register.jsp");
				return mav;
			}
			if(sm.findEmailByPhone(member.getPhone())!=null) {
				//전화번호 중복됨
				FieldError fe = new FieldError("register.jsp", "phone", "전화번호가 중복됩니다");
				br.addError(fe);
				mav.addObject("BODY", "register.jsp");
				return mav;
			}
			
			//회원가입 후 로그인 처리		
			sm.newRankAndinsert(member, session);
			
		
			
			
			return new ModelAndView("registerAfter");		
		
		
	}

	@RequestMapping(value="/login/findEmail.html")
	public ModelAndView findEmail(String phone, HttpSession session) {
		ModelAndView mav = new ModelAndView("main");
		
		if(phone!=null) {
			mav.addObject("BODY", "findEmailResult.jsp");
			Member resultMember = sm.findEmailByPhone(phone);
			if(resultMember==null) {//없으면
				
				mav.addObject("findEmailResult", "fail");
				return mav;
			}else {//있으면
				String findEmail = resultMember.getEmail();
				StringBuilder resultEmail = new StringBuilder(findEmail);
				resultEmail.setCharAt(1, '*');
				resultEmail.setCharAt(2, '*');
				resultEmail.setCharAt(3, '*');
				
				//이메일전송
				String setfrom = "119lenagend119@gamil.com";
		        String tomail = findEmail; // 받는 사람 이메일
		        String title = "Fiction Life 계정찾기 결과입니다."; // 제목
		        String content =
		        		 System.getProperty("line.separator")+ //한줄씩 줄간격을 두기위해 작성
		                 
		                 System.getProperty("line.separator")+
		                         
		                 "안녕하세요 Fiction Life를 찾아주셔서 감사합니다"
		                 
		                 +System.getProperty("line.separator")+
		                 
		                 System.getProperty("line.separator")+
		         
		                 " 비밀번호는 " +resultMember.getPassword()+ " 입니다. "
		                 
		                 +System.getProperty("line.separator")+
		                 
		                 System.getProperty("line.separator");
		        try {
		            MimeMessage message = mailSender.createMimeMessage();
		            MimeMessageHelper messageHelper = new MimeMessageHelper(message,
		                    true, "UTF-8");

		            messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
		            messageHelper.setTo(tomail); // 받는사람 이메일
		            messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
		            messageHelper.setText(content); // 메일 내용
		            
		            mailSender.send(message);
		        } catch (Exception e) {
		            System.out.println(e);
		        }
		        
		       
		   
				
				mav.addObject("findEmailResult", resultEmail);
				
				return mav;
			}
		}else {
			mav.addObject("BODY", "findEmail.jsp");
			return mav;
		}
		
	}
	
	
	
//	@RequestMapping(value="/login/doMemberModify.html")
//	public ModelAndView doMemberModify(@Valid Member member, BindingResult br) {
//		ModelAndView mav = new ModelAndView("main");
//		String checkPwd = member.getPassword();
//		Member dbMember = sm.checkEmail(member.getEmail());
//		if(br.hasErrors()) { //단순폼체크
//			mav.addObject("CONTENTNAME", "modifyForm");
//			mav.addObject("BODY", "myPage.jsp");
//		
//			return mav;
//		}
//		if(! member.getNewPassword().equals(member.getRePwd())) {
//			//비밀번호 확인 결과 다름
//			FieldError fe = new FieldError("modifyMemberForm.jsp", "newPassword", "비밀번호 값이 일치하지 않습니다");
//			br.addError(fe);
//			mav.addObject("CONTENTNAME", "modifyForm");
//			mav.addObject("BODY", "myPage.jsp");
//			return mav;
//		}
//	
//		if(!checkPwd.equals(dbMember.getPassword())) {
//			
//			FieldError fe = new FieldError("modifyMemberForm.jsp", "password", "비밀번호가 다릅니다");
//			br.addError(fe);	
//			mav.addObject("CONTENTNAME", "modifyForm");
//			mav.addObject("BODY", "myPage.jsp");
//			return mav;
//		}else {
//			if(member.getAct().equals("수정")) {
//				Member newMember = new Member();
//				newMember.setEmail(member.getEmail());
//				newMember.setPassword(member.getNewPassword());
//				sm.modifyMember(newMember);
//				mav.addObject("memberModifyResult", "modify");
//				mav.setViewName("memberModifyResult");
//			}else if(member.getAct().equals("탈퇴")) {
//				sm.quitMember(member.getEmail());
//				mav.addObject("memberModifyResult", "quit");
//				mav.setViewName("memberModifyResult");
//			}
//			
//			
//		}
//		
//		return mav;
//	}
//
//	
}
