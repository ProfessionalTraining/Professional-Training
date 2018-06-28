package com.newswebsite.newswebsite.controller;

import com.newswebsite.newswebsite.bean.*;
import com.newswebsite.newswebsite.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/userrecord")
public class User_recordController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;
    @Autowired
    private Adm_recordService adm_recordService;
    @Autowired
    private User_recordService user_recordService;

    int id=0;

    @GetMapping
    public String index(Model model){
        long colNum = user_recordService.getColNum();
        model.addAttribute("colNum",colNum);

        List<User_record> list = user_recordService.page(id, 10);
        model.addAttribute("user_record", list);
        model.addAttribute("page",id/10+1);

        model.addAttribute("loginName", UserController.loginName);

        return "userrecord";
    }

    @PostMapping("/pageNext")
    public String pageNext(@RequestParam("pageNum") String pageNum, Model model){
        id = Integer.parseInt(pageNum) - 1;
        id *= 10;

        long colNum = user_recordService.getColNum();
        model.addAttribute("colNum",colNum);

        List<User_record> list = user_recordService.page(id, 10);
        model.addAttribute("user_record",list);

        model.addAttribute("page",id/10+1);

        return "userrecord";
    }

    @PostMapping("/pagePrevious")
    public String pagePrevious(@RequestParam("pageNum") String pageNum, Model model){
        id = Integer.parseInt(pageNum) - 1;
        id *= 10;

        long colNum = user_recordService.getColNum();
        model.addAttribute("colNum",colNum);

        List<User_record> list = user_recordService.page(id, 10);
        model.addAttribute("user_record",list);

        model.addAttribute("page",id/10+1);

        return "userrecord";
    }

    @PostMapping("/find")
    public String findUser(@RequestParam(value = "userID", defaultValue = "0") int userid,@RequestParam(value = "newsID", defaultValue = "0") int newsid, @RequestParam(value = "time",defaultValue = "") String time, Model model){
        if(userid > 0){
            List<User_record> user = user_recordService.queryByuserID(userid);
            model.addAttribute("user_record",user);
            model.addAttribute("colNum", 3);
            id = 0;
            model.addAttribute("page",id/10+1);
            return "userrecord";
        }
        if(newsid > 0){
            List<User_record> user = user_recordService.findBynewsID(newsid);
            model.addAttribute("user_record",user);
            model.addAttribute("colNum", 3);
            id = 0;
            model.addAttribute("page",id/10+1);
            return "userrecord";
        }
        if(!time.equals("")){
            List<User_record> user = user_recordService.findByTime(time);
            model.addAttribute("user_record",user);
            model.addAttribute("colNum",user.size());
            id = 0;
            model.addAttribute("page",id/10+1);
            return "userrecord";
        }
        else {
            /*List<News> list = newsService.getAllUser();
            model.addAttribute("user", list);
            model.addAttribute("colNum",list.size());
            id = 0;
            model.addAttribute("page",id/10+1);*/
            return "userrecord";
        }
    }

    /*@GetMapping("/user")
    public String user(Model model){

        List<User> list = userService.page(0, 10);
        model.addAttribute("news", list);

        long colNum = userService.getColNum();
        model.addAttribute("colNum", colNum);

        model.addAttribute("page",1);

        return "user";
    }

    @GetMapping("/news")
    public String news(Model model){

        List<News> list = newsService.page(0, 10);
        model.addAttribute("news", list);

        long colNum = newsService.getColNum();
        model.addAttribute("colNum", colNum);

        model.addAttribute("page",1);

        return "news";
    }

    @GetMapping("/tags")
    public String tags(Model model){
        List<Tags> list = tagService.page(0, 10);
        model.addAttribute("tags", list);

        long colNum = tagService.getColNum();
        model.addAttribute("colNum", colNum);

        model.addAttribute("page",1);

        return "tag";
    }

    @GetMapping("/adm_record")
    public String adminrecord(Model model){

        List<Adm_record> list = adm_recordService.page(0, 10);
        model.addAttribute("adm_record", list);

        long colNum = adm_recordService.getColNum();
        model.addAttribute("colNum", colNum);

        model.addAttribute("page",1);

        return "redirect:/adm_record";
    }*/
}
