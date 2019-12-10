package dao;

import model.Icon;
import model.User_rank;

public interface RankDao {
public void createRank(User_rank ur);
public Integer maxSeq();
public User_rank getRank(String email);
public Icon getIcon(Integer icon_id);
public void updateReaders(User_rank ur);
public void AddR_point(String Email);
public void AddW_point(String Email);
public void AddW_point5(String Email);
public String getW_icon_ImageByNickname(String nickname);
public String getR_icon_ImageByNickname(String nickname);
}
