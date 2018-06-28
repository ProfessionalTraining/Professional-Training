package com.newswebsite.newswebsite.controller;

import com.newswebsite.newswebsite.bean.*;
import com.newswebsite.newswebsite.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/news")
public class NewsController {
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

    int id = 0;

    @GetMapping
    public String index(Model model){
        List<News> list = newsService.page(id, 10);
        model.addAttribute("news",list);

        long colNum = newsService.getColNum();
        model.addAttribute("colNum", colNum);

        model.addAttribute("page",id/10+1);
        model.addAttribute("loginName", UserController.loginName);

        return "news";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam("time") String time, @RequestParam("title") String title,
                          @RequestParam("summary") String summary, @RequestParam("news_address") String news_address,
                          @RequestParam("img_address") String img_address, @RequestParam("heat") int heat, @RequestParam("tag") int tagID){
        long colNum = newsService.getColNum();
        newsService.addNews((int)(colNum + 1), time, title, summary, news_address, img_address, heat, tagID);

        colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "insert to News");

        return "redirect:/news";
    }

    @PostMapping("/update")
    public String updateNews(@RequestParam("id") int id, @RequestParam("time") String time, @RequestParam("title") String title,
                          @RequestParam("summary") String summary, @RequestParam("news_address") String news_address,
                          @RequestParam("img_address") String img_address, @RequestParam("heat") int heat, @RequestParam("tag") int tagID){
        newsService.updateNews(id, time, title, summary, news_address, img_address, heat, tagID);

        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "update to News");

        return "redirect:/news";
    }

    @PostMapping("/pageNext")
    public String pageNext(@RequestParam("pageNum") String pageNum, Model model){
        id = Integer.parseInt(pageNum) - 1;
        id *= 10;

        long colNum = newsService.getColNum();
        model.addAttribute("colNum",colNum);

        List<News> list = newsService.page(id, 10);
        model.addAttribute("news",list);

        model.addAttribute("page",id/10+1);

        return "redirect:/news";
    }

    @PostMapping("/pagePrevious")
    public String pagePrevious(@RequestParam("pageNum") String pageNum, Model model){
        id = Integer.parseInt(pageNum) - 1;
        id *= 10;

        long colNum = newsService.getColNum();
        model.addAttribute("colNum",colNum);

        List<News> list = newsService.page(id, 10);
        model.addAttribute("news",list);

        model.addAttribute("page",id/10+1);

        return "redirect:/news";
    }

    @PostMapping("/find")
    public String findUser(@RequestParam(value = "newsID", defaultValue = "0") int id, @RequestParam(value = "time",defaultValue = "") String time, @RequestParam(value = "title",defaultValue = "") String title, Model model){
        if(id > 0){
            List<News> user = newsService.queryByID(id);
            model.addAttribute("news",user);
            model.addAttribute("colNum", user.size());
            id = 0;
            model.addAttribute("page",id/10+1);
            return "news";
        }
        if(!time.equals("")){
            List<News> user = newsService.findByTime(time);
            model.addAttribute("news",user);
            model.addAttribute("colNum",user.size());
            id = 0;
            model.addAttribute("page",id/10+1);
            return "redirect:/news";
        }
        else if(!title.equals("")){
            List<News> user = newsService.findByTitle(title);
            model.addAttribute("news",user);
            model.addAttribute("colNum",user.size());
            id = 0;
            model.addAttribute("page",id/10+1);
            return "redirect:/news";
        }
        else {
            /*List<News> list = newsService.getAllUser();
            model.addAttribute("user", list);
            model.addAttribute("colNum",list.size());
            id = 0;
            model.addAttribute("page",id/10+1);*/
            return "redirect:/news";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id){
        Optional<News> news = newsService.findByID(id);
        newsService.deleteNews(news.get());
        newsService.updateDelete(id);

        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "delete to News");

        return "redirect:/news";
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

    @GetMapping("/adm_record")
    public String adminrecord(Model model){

        List<Adm_record> list = adm_recordService.page(0, 10);
        model.addAttribute("adm_record", list);

        long colNum = adm_recordService.getColNum();
        model.addAttribute("colNum", colNum);

        model.addAttribute("page",1);

        return "redirect:/adm_record";
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
