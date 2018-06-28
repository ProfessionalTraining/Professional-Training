package com.newswebsite.newswebsite.service;

import com.newswebsite.newswebsite.bean.User_record;

import java.util.List;

public interface User_recordService {
    long getColNum();

    List<User_record> page(int p1, int p2);

    List<User_record> queryByuserID(Integer userid);

    List<User_record> findBynewsID(Integer newsid);

    List<User_record> findByTime(String time);
}
