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
@RequestMapping("/adminrecord")
public class Adm_recordController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;
    @Autowired
    private User_recordService user_recordService;
    @Autowired
    private Adm_recordService adm_recordService;

    int  id = 0;

    @GetMapping
    public String index(Model model){
        long colNum = adm_recordService.getColNum();
        model.addAttribute("colNum",colNum);

        List<Adm_record> list = adm_recordService.page(id, 10);
        model.addAttribute("adm_record", list);

        model.addAttribute("page",id/10+1);
        model.addAttribute("loginName", UserController.loginName);

        return "adminrecord";
    }

    @PostMapping("/pageNext")
    public String pageNext(@RequestParam("pageNum") String pageNum, Model model){
        id = Integer.parseInt(pageNum) - 1;
        id *= 10;

        long colNum = adm_recordService.getColNum();
        model.addAttribute("colNum",colNum);

        List<Adm_record> list = adm_recordService.page(id, 10);
        model.addAttribute("adm_record",list);

        model.addAttribute("page",id/10+1);

        return "adminrecord";
    }

    @PostMapping("/pagePrevious")
    public String pagePrevious(@RequestParam("pageNum") String pageNum, Model model){
        id = Integer.parseInt(pageNum) - 1;
        id *= 10;

        long colNum = adm_recordService.getColNum();
        model.addAttribute("colNum",colNum);

        List<Adm_record> list = adm_recordService.page(id, 10);
        model.addAttribute("adm_record",list);

        model.addAttribute("page",id/10+1);

        return "adminrecord";
    }

    @PostMapping("/find")
    public String findUser(@RequestParam(value = "adminID", defaultValue = "0") int id, @RequestParam(value = "time",defaultValue = "") String time, Model model){
        if(id > 0){
            List<Adm_record> user = adm_recordService.queryByID(id);
            model.addAttribute("adm_record",user);
            model.addAttribute("colNum", 3);
            id = 0;
            model.addAttribute("page",id/10+1);
            return "adminrecord";
        }
        if(!time.equals("")){
            List<Adm_record> user = adm_recordService.findByTime(time);
            model.addAttribute("adm_record",user);
            model.addAttribute("colNum",user.size());
            id = 0;
            model.addAttribute("page",id/10+1);
            return "adminrecord";
        }
        else {
            /*List<News> list = newsService.getAllUser();
            model.addAttribute("user", list);
            model.addAttribute("colNum",list.size());
            id = 0;
            model.addAttribute("page",id/10+1);*/
            return "adminrecord";
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

    @GetMapping("/user_record")
    public String userrecord(Model model){
        long colNum = user_recordService.getColNum();
        model.addAttribute("colNum",colNum);

        List<User_record> list = user_recordService.page(0, 10);
        model.addAttribute("user_record", list);
        model.addAttribute("page",1);

        return "redirect:/user_record";
    }*/
}
