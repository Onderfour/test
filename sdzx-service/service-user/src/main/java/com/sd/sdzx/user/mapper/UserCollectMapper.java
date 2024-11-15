package com.sd.sdzx.user.mapper;

import com.sd.sdzx.model.entity.user.UserBrowseHistory;
import com.sd.sdzx.model.entity.user.UserCollect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserCollectMapper {
    void saveUserCollect(UserCollect userCollect);

    List<UserCollect> findUserBrowseHistoryPage(Long id);

    List<UserBrowseHistory> findUserCollectPage(Long id);
}
