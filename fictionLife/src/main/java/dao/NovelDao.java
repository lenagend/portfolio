package dao;

import java.util.List;

import condition.LikeyCondition;
import condition.PagingCondition;
import model.Favorite;
import model.Likey;
import model.Novel;
import model.Novel_board;
import model.Reply_novel;
import model.Report_novel;

public interface NovelDao {
	public Integer max_NovelId();

	public void insertNovel(Novel novel);

	List<Novel> findMyNovel(PagingCondition p);

	public void modifyNovel(Novel novel);

	public void deleteNovel(Integer id);

	public void insertEpi(Novel_board novel_board);

	public Integer maxEpiBno();

	public Integer maxEpiNum(Integer id);

	public void addEpi(Integer novelId);

	public Integer getEpiCount(Integer novelId);

	public List<Novel_board> getEpiList(PagingCondition pc);

	public Integer getMaxMyNovel(String email);

	public Novel findParentNovel(Integer id);

	public Novel_board getEpiContent(Novel_board novel_board);

	List<Novel> findAllNovel(PagingCondition p);

	public Integer countNovelList();

	public Integer favorite(Favorite f );

	public void insertFavorite(Favorite f);
	
	public Integer maxFavoriteSeqno();
	
	public List<Novel> myFavoriteNovels(PagingCondition f);
	
	public Integer countMyFavorite(String email);
	
	public void plusFavorite(Integer novel_id);
	
	public void minusFavorite(Integer novel_id);
	
	public void deleteMyFavorite(Favorite f);
	
	public void plusViewCnt(Integer bno);
	
	public void modifyEpi(Novel_board nb);
	
	public List<Novel> findNovelByType(PagingCondition pc);
	
	public Integer countTypeNovelList(String type);
	
	public Integer likeyCheck(Likey likey);
	
	public void likey(Likey likey);
	
	public void addLikey(LikeyCondition lc);
	
	public Integer maxLikeySeqno();
	
	public Integer maxReportSeqno();
	
	public void insertReport(Report_novel rn);
	
	public Integer cherkReportAlready(Report_novel rn);
	
	public Integer countNotFinished(String email);
	
	public void novelFinish(Integer id);
	
	public Integer thisNovelFinished(Integer id);
	
	public Integer maxRno();

	public void insertReply(Reply_novel rn);

	public List<Reply_novel> getReplyByBno(PagingCondition pc);

	public Integer countReplyByBno(Integer bno);

	public void addReplCnt(Integer bno);

	public Integer getOrder_no(Reply_novel rn);

	public void addReplCnt2(Integer rno);

	public List<Reply_novel> getReREply(PagingCondition pc);
	
	public void deleteReply(Reply_novel rn);
	
	public Novel findTop1Novel();
	public Novel findTop2Novel();
	public Novel findTop3Novel();
	public Novel findTop4Novel();
	public Novel findTop5Novel();
	
	
	public List<Novel> findTop10NovelByReco_cnt();
	
	public Integer countSearchNovel(String search);
	
	public List<Novel> getSearchNovel(PagingCondition pc);
	
	
	
	
	
	
	
	
}
