package com.dong.music.repository;

import com.dong.music.beans.SongBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SongRepository extends JpaRepository<SongBean,Integer> {
    @Query(value = "from SongBean as m ,SongListBean as u where u.id=m.u_l_id AND u.id=?1 AND u.u_id=?2")
    List<SongBean> findById(int listId,int userId);
}
