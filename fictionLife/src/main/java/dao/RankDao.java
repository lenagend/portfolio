package dao;

import condition.PointCondition;
import model.Icon;
import model.User_rank;

public interface RankDao {
public void createRank(User_rank ur);
public Integer maxSeq();
public User_rank getRank(String email);
public Icon getIcon(Integer icon_id);
public void updateReaders(User_rank ur);
public void AddR_point(PointCondition pc);
public void AddW_point(PointCondition pc);

public String getW_icon_ImageByEmail(String email);
public String getR_icon_ImageByNickname(String nickname);
}
