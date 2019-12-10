package logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import condition.LikeyCondition;
import condition.PagingCondition;
import dao.NovelDao;
import model.Favorite;
import model.Likey;
import model.Novel;
import model.Novel_board;
import model.Reply_novel;
import model.Report_novel;

@Service
public class Service_Novel_impl implements Service_Novel {

	@Autowired
	private NovelDao nv;
	
	
	
	public Integer max_NovelId() {
		
		return nv.max_NovelId();
	}
	
	public void insertNovel(Novel novel) {
		nv.insertNovel(novel);
	}
	
	public List<Novel> findMyNovel(PagingCondition p) {
		return nv.findMyNovel(p);
	}
	
	public void modifyNovel(Novel novel) {
		nv.modifyNovel(novel);

	}

	public void deleteNovel(Integer id) {
		
		nv.deleteNovel(id);
		nv.novelFinish(id);
		//완결처리해야됨여기서
	}

	public void insertEpi(Novel_board novel_board) {
		nv.insertEpi(novel_board);
		nv.addEpi(novel_board.getNovel_id());
	}

	public Integer maxEpiBno() {
		
		return nv.maxEpiBno();
	}
	public Integer maxEpiNum(Integer id) {
		// TODO Auto-generated method stub
		return nv.maxEpiNum(id);
	}
	
	public void addEpi(Integer novelId) {
		nv.addEpi(novelId);
		
	}
	public Integer getEpiCount(Integer novelId) {
	
		return nv.getEpiCount(novelId);
	}
	public List<Novel_board> getEpiList(PagingCondition pc) {
		
		return nv.getEpiList(pc);
	}
	public Integer getMaxMyNovel(String email) {
		
		return nv.getMaxMyNovel(email);
	}
	public Novel findParentNovel(Integer id) {
		return nv.findParentNovel(id);
	}
	public Novel_board getEpiContent(Novel_board novel_board) {
		
		return nv.getEpiContent(novel_board);
	}
	
	public List<Novel> findAllNovel(PagingCondition p) {
		
		return nv.findAllNovel(p);
	}
	public Integer countNovelList() {

		return nv.countNovelList();
	}
	
	public Integer favorite(Favorite f) {
		
		return nv.favorite(f);
	}
	
	public void insertFavorite(Favorite f) {
		nv.insertFavorite(f);
	}
	public Integer maxFavoriteSeqno() {
	
		return nv.maxFavoriteSeqno();
	}
	
	public List<Novel> myFavoriteNovels(PagingCondition f) {

		return nv.myFavoriteNovels(f);
	}
	
	public Integer countMyFavorite(String email) {
		
		return nv.countMyFavorite(email);
	}
	
	public void plusFavorite(Integer novel_id) {
		nv.plusFavorite(novel_id);
	}

	public void minusFavorite(Integer novel_id) {
	nv.minusFavorite(novel_id);
		
	}
	
	public void deleteMyFavorite(Favorite f) {
	nv.deleteMyFavorite(f);
	}
	
	public void plusViewCnt(Integer bno) {
	nv.plusViewCnt(bno);
		
	}
	public void modifyEpi(Novel_board nb) {
	nv.modifyEpi(nb);
		
	}
	
	public List<Novel> findNovelByType(PagingCondition pc) {
		
		return nv.findNovelByType(pc);
	}
	
	public Integer countTypeNovelList(String type) {
		
		return nv.countTypeNovelList(type);
	}

	public Integer likeyCheck(Likey likey) {
		
		return nv.likeyCheck(likey);
	}

	public void likey(Likey likey) {
		nv.likey(likey);
	}

	public void addLikey(LikeyCondition lc) {
		nv.addLikey(lc);
	}

	public Integer maxLikeySeqno() {
		return nv.maxLikeySeqno();
	}
	
	public void insertReport(Report_novel rn) {
		nv.insertReport(rn);
		
	}
	
	public Integer maxReportSeqno() {
		Integer maxSeq = nv.maxReportSeqno();
		if(maxSeq==null) maxSeq=0;
		
		return maxSeq;
	}
	
	public Integer cherkReportAlready(Report_novel rn) {
	
		return nv.cherkReportAlready(rn);
	}
	
	public Integer countNotFinished(String email) {
	
		return nv.countNotFinished(email);
	}
	
	public void novelFinish(Integer id) {
		nv.novelFinish(id);
		
	}
	
	public Integer thisNovelFinished(Integer id) {
		
		return nv.thisNovelFinished(id);
	}
	
	public Integer maxRno() {
		
		return nv.maxRno();
	}
	
	public void insertReply(Reply_novel rn) {
		nv.insertReply(rn);
		
	}
	public List<Reply_novel> getReplyList(PagingCondition pc) {
		return nv.getReplyByBno(pc);

	}
	
	public Integer countReplyByBno(Integer bno) {
		// TODO Auto-generated method stub
		return nv.countReplyByBno(bno);
	}
	
	public void addReplCnt(Integer bno) {
		nv.addReplCnt(bno);
		
	}
	public Integer getOrder_no(Reply_novel rn) {
		Integer order_no = nv.getOrder_no(rn);
		if(order_no==null) order_no=0;
		
		return order_no+1;
	}
	
	public void addReplCnt2(Integer rno) {
		nv.addReplCnt2(rno);
		
	}
	
	public List<Reply_novel> getReREply(PagingCondition pc) {
		
		return nv.getReREply(pc);
	}

	public void deleteReply(Reply_novel rn) {
		nv.deleteReply(rn);

	}

	public Novel findTop1Novel() {

		return nv.findTop1Novel();
	}

	public Novel findTop2Novel() {

		return nv.findTop2Novel();
	}

	public Novel findTop3Novel() {

		return nv.findTop3Novel();
	}

	public Novel findTop4Novel() {

		return nv.findTop4Novel();
	}

	public Novel findTop5Novel() {

		return nv.findTop5Novel();
	}
	
	public List<Novel> findTop10NovelByReco_cnt() {
	
		return nv.findTop10NovelByReco_cnt();
	}
	
	public Integer countSearchNovel(String search) {
		return nv.countSearchNovel(search);
	}
	
	public List<Novel> getSearchNovel(PagingCondition pc) {
		// TODO Auto-generated method stub
		return nv.getSearchNovel(pc);
	}
}
