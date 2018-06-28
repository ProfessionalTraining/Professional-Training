package com.newswebsite.newswebsite.repository;

import com.newswebsite.newswebsite.bean.User_record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface User_recordRepository extends JpaRepository<User_record,Integer> {

    @Query(value = "select * from User_record a limit ?1, ?2", nativeQuery = true)
    List<User_record> page(int p1, int p2);

    @Query("select a from User_record a where a.userID = ?1")
    List<User_record> queryByuserID(Integer userid);

    @Query("select a from User_record a where a.newsID = ?1")
    List<User_record> findBynewsID(Integer newsid);

    @Query(value = "select * from User_record a where a.time = ?1", nativeQuery = true)
    List<User_record> findByTime(String time);
}
