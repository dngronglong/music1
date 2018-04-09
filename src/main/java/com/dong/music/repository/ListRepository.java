package com.dong.music.repository;

import com.dong.music.beans.SongListBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListRepository extends JpaRepository<SongListBean,Integer> {
    @Query(value = "from SongListBean as l where l.u_id=?1")
    List<SongListBean> findAllByU_id(int userId);
}
