package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import condition.LikeyCondition;
import condition.PointCondition;
import condition.RankCondition;
import logic.Service_Member;
import logic.Service_Novel;
import model.Favorite;
import model.Likey;
import model.Member;
import model.Novel;
import model.Novel_board;
import model.Report_novel;

@Controller
public class NovelController {

	@Autowired
	private Service_Novel sn;
	@Autowired
	private Service_Member sm;
	
	
	//등록
	@RequestMapping(value="/novel/registerNovel.html")
	public ModelAndView regiNovel( Novel novel, HttpSession session, BindingResult br
			) throws IOException {
		
		ModelAndView mav = new ModelAndView("main");
		
			if(novel.getTitle().equals("")) {
				FieldError fe = new FieldError("regiNovel.jsp", "title", "작품 제목을 입력해주세요");
				br.addError(fe);		
				mav.addObject("BODY", "regiNovel.jsp");
				return mav;
			}
			if(novel.getDescription().equals("")) {
				FieldError fe = new FieldError("regiNovel.jsp", "description", "작품 설명을 입력해주세요");
				br.addError(fe);		
				mav.addObject("BODY", "regiNovel.jsp");
				return mav;
			}
			
			Novel inputNovel = novel;
			String uploadFolder = "/lenagend/tomcat/webapps/ROOT/upload/";
			MultipartFile multiFile= inputNovel.getImageFile();
			File saveFile = new File(uploadFolder, multiFile.getOriginalFilename());
			try {
				multiFile.transferTo(saveFile);
			}catch(Exception e) {
				e.printStackTrace();
			}

			//선택안했을경우 기본표지로 하는처리해야한다
			
			Member loginMember = (Member)session.getAttribute("LOGINMEMBER");
			inputNovel.setEmail(loginMember.getEmail());
			inputNovel.setImage( multiFile.getOriginalFilename());
			
			sn.insertNovel(inputNovel);
			
			mav.setViewName("regiResultPage");
			
			return mav;
		
		
	}
	//수정
	@RequestMapping(value="/novel/modifyNovel.html")
	public ModelAndView modiNovel( Novel novel, HttpSession session, BindingResult br
			) throws IOException {
		
		ModelAndView mav = new ModelAndView("main");
		
			if(novel.getTitle().equals("")) {
				System.out.println("불러온 번호"+ novel.getId());
				FieldError fe = new FieldError("regiNovel.jsp", "title", "작품 제목을 입력해주세요");
				br.addError(fe);		
				mav.addObject("novel", novel);
				mav.addObject("BODY", "modiNovel.jsp");
				return mav;
			}
			if(novel.getDescription().equals("")) {
				FieldError fe = new FieldError("regiNovel.jsp", "description", "작품 설명을 입력해주세요");
				br.addError(fe);		
				mav.addObject("BODY", "modiNovel.jsp");
				return mav;
			}
			
			
			Novel inputNovel = novel;
			String uploadFolder = "/lenagend/tomcat/webapps/ROOT/upload/";
			MultipartFile multiFile= inputNovel.getImageFile();
			File saveFile = new File(uploadFolder, multiFile.getOriginalFilename());
			try {
				multiFile.transferTo(saveFile);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
				
			inputNovel.setImage( multiFile.getOriginalFilename());
			sn.modifyNovel(novel);
			mav.setViewName("modiResultPage");
		
			return mav;
		
		
	}
	
	//삭제(블라인드 처리)
	@RequestMapping(value="/novel/deleteNovel.html")
	public ModelAndView deleteNovel(Integer deleteNovelId) {
		sn.deleteNovel(deleteNovelId);
		ModelAndView mav = new ModelAndView("deleteResultPage");
				
		return mav;
		
	}
	
	
	@RequestMapping(value="/novel/regiEpi.html")
	public ModelAndView regiEpi(Novel_board novel_board, BindingResult br, String finish) {
		ModelAndView mav = new ModelAndView("main");
		Novel_board newEpi = novel_board;
		if(newEpi.getEpi_title().equals("")) {
			FieldError fe = new FieldError("episodeForm.jsp", "epi_title", "에피소드 제목을 입력해주세요");
			br.addError(fe);		
			mav.addObject("BODY", "episodeForm.jsp");
			return mav;
		}
		if(newEpi.getContent().equals("")) {
			FieldError fe = new FieldError("episodeForm.jsp", "content", "내용을 입력해주세요");
			br.addError(fe);		
			mav.addObject("BODY", "episodeForm.jsp");
			return mav;
		}

		
		
			
		sn.insertEpi(newEpi);
		
		
		if(finish.equals("finish")) {//완결처리 버튼을 누른 경우
			sn.novelFinish(newEpi.getNovel_id());
		}
		
		
		mav.addObject("novelId", newEpi.getNovel_id());
		mav.setViewName("regiEpiResult");
		return mav;
		
	}
	
	@RequestMapping(value = "/novel/ajaxLikey.html")
	@ResponseBody
	public String ajaxLikey(HttpSession session, String bno, String email) {
		
		Member loginMember = (Member) session.getAttribute("LOGINMEMBER");
		Integer likey_bno = Integer.parseInt(bno);
		Likey l = new Likey();
		l.setEmail(loginMember.getEmail());
		l.setLikey_bno(likey_bno);
		Integer likeyAlready = sn.likeyCheck(l);
		String result;
		if (likeyAlready == 0) {
		
			//likey테이블에인설트
			sn.likey(l);
			
			//novel게시판에 추천수 (reco_point)만큼 증가 -로그인 시 세션에 랭크객체가 저장되어있다-
			RankCondition rank = (RankCondition) session.getAttribute("memberRank");
			LikeyCondition lc = new LikeyCondition();
			lc.setBno(likey_bno);
			lc.setReco_point(rank.getUr().getReco_point());
			sn.addLikey(lc);

			// 추천(활동)했으니독자포인트 1증가
			PointCondition pc = new PointCondition();
			pc.setPoint(1); pc.setEmail(loginMember.getEmail());
			sm.AddR_point(pc);
			// 추천받았으니 작가포인트 1증가
			pc.setPoint(1);
			pc.setEmail(email);
			sm.AddW_point(pc);
			// 점수 새로고침을 위해 로그인 시처럼 세션에 계급정보

			sm.rankProcess(loginMember, session);
			result = "suc";

		}

		else {
		 result = "fail";
		}
	
		return result;

	}
	
	
	@RequestMapping(value="/novel/favorite.html")
	@ResponseBody
	public String favorite(HttpSession session, Integer novelId, String email) {

		
		Member loginuser = (Member)session.getAttribute("LOGINMEMBER");
		Favorite f = new Favorite();
		f.setEmail(loginuser.getEmail());
		f.setNovel_id(novelId);
		
		Integer favoriteAlready = sn.favorite(f);
		if(favoriteAlready==0) {
			
			
			
			
			sn.insertFavorite(f);
		
			//novel게시판에 선호작수 1회추가
			sn.plusFavorite(novelId);
			//작가 점수 올려주기(삭제했다 재등록하면서 점수 올리는것방지하기위해 검색후..블라인드에 예스되어있으면 하면 안됨-이건 나중에-)
			PointCondition pc = new PointCondition();
			pc.setPoint(5); pc.setEmail(email);
			sm.AddW_point(pc);
			
			
			return "favoSuc";
			
		}else {
			
			return "favoFail";
		}
		
	}
	
	
	@RequestMapping(value="/novel/deleteFavorite.html")
	public ModelAndView deleteFavorite(HttpSession session, Integer novelId) {

		ModelAndView mav = new ModelAndView("favoriteResult");
		Member loginuser = (Member)session.getAttribute("LOGINMEMBER");
		Favorite f = new Favorite();
		f.setEmail(loginuser.getEmail());
		f.setNovel_id(novelId);
		
			sn.deleteMyFavorite(f);
		
			//novel게시판에 선호작수 1회제거
			sn.minusFavorite(novelId);
			
			
			mav.addObject("favoriteResult","DELETE");
			return mav;
	
		
	}
	
	
//	@RequestMapping(value="/novel/modifyEpi.html")
//	public ModelAndView modifyEpi(Novel_board novel_board, BindingResult br) {
//		ModelAndView mav = new ModelAndView("main");
//		Novel_board modiEpi = novel_board;
//		if(modiEpi.getEpi_title().equals("")) {
//			FieldError fe = new FieldError("modifier.jsp", "epi_title", "에피소드 제목을 입력해주세요");
//			br.addError(fe);		
//			mav.addObject("BODY", "modifier.jsp");
//			return mav;
//		}
//		if(modiEpi.getContent().equals("")) {
//			FieldError fe = new FieldError("modifier.jsp", "content", "내용을 입력해주세요");
//			br.addError(fe);		
//			mav.addObject("BODY", "modifier.jsp");
//			return mav;
//		}
//		
//		sn.modifyEpi(modiEpi);
//		mav.setViewName("modiEpiResult");
//		mav.addObject("parentNovelId", modiEpi.getNovel_id());
//		return mav;
//	}
//	

	
	

//	
//	
	@RequestMapping(value="/novel/report.html")
	public ModelAndView report(HttpSession session, Integer bno, String reportTitle, String reportContent
			,Integer epi_number, 
			Integer pni) {

		ModelAndView mav = new ModelAndView("reportResult");
		mav.addObject("bno", bno);
		mav.addObject("epi_number",epi_number);
		mav.addObject("pni",pni);

		Member loginuser = (Member)session.getAttribute("LOGINMEMBER");
		Report_novel rn = new Report_novel();
		
		rn.setEmail(loginuser.getEmail());
		rn.setTitle(reportTitle);
		rn.setContent(reportContent);
		rn.setR_bno(bno);
		rn.setNovel_id(pni);
		rn.setEpi(epi_number);
		Integer check = sn.cherkReportAlready(rn);
		if(check==0) {
			//아직신고 안한글. 인설트
			sn.insertReport(rn);
			mav.addObject("reportResult", "OK");
			
		}else {
			//이미 신고 한 글
			
			mav.addObject("reportResult", "NOK");
		}
		
		
		return mav;
	
		
	}

	
	@RequestMapping(value="/novel/getReco.html")
	@ResponseBody
	public String getReco_cnt(Integer bno) {
		
		
		
		String result = sn.getRecoCnt(bno)+"";
		return result;
	}
	
	
	
	

}
