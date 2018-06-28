package com.newswebsite.newswebsite.service;

import com.newswebsite.newswebsite.bean.User_record;
import com.newswebsite.newswebsite.repository.User_recordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_recordServiceImpl implements User_recordService{
    @Autowired
    private User_recordRepository user_recordRepository;
    @Override
    public List<User_record> page(int p1, int p2){
        return user_recordRepository.page(p1, p2);
    }

    @Override
    public long getColNum(){
        return user_recordRepository.count();
    }

    @Override
    public List<User_record> findByTime(String time){
        return user_recordRepository.findByTime(time);
    }

    @Override
    public List<User_record> queryByuserID(Integer userid){
        return user_recordRepository.queryByuserID(userid);
    }

    @Override
    public List<User_record> findBynewsID(Integer newsid){
        return user_recordRepository.findBynewsID(newsid);
    }
}
