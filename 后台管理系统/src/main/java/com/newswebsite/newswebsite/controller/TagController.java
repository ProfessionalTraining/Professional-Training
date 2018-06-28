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
@RequestMapping("/tags")
public class TagController {
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
        List<Tags> list = tagService.page(id, 10);
        model.addAttribute("tags",list);

        long colNum = tagService.getColNum();
        model.addAttribute("colNum", colNum);

        model.addAttribute("page",id/10+1);
        model.addAttribute("loginName", UserController.loginName);

        return "tag";
    }

    @PostMapping("/add")
    public String addTags(@RequestParam("name") String name){
        long colNum = tagService.getColNum();
        tagService.addTags((int)(colNum + 1), name);

        colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "add to Tag");

        return "redirect:/tags";
    }

    @PostMapping("/update")
    public String updateNews(@RequestParam("id") int id, @RequestParam("name") String name){
        tagService.updateTags(id, name);

        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "update to Tag");

        return "redirect:/tags";
    }

    @PostMapping("/find")
    public String findUser(@RequestParam(value = "id", defaultValue = "0") int id, @RequestParam(value = "name",defaultValue = "") String name, Model model){
        if(id > 0){
            List<Tags> user = tagService.queryByID(id);
            model.addAttribute("tags",user);
            model.addAttribute("colNum", user.size());
            id = 0;
            model.addAttribute("page",id/10+1);
            return "tag";
        }
        if(!name.equals("")){
            List<Tags> user = tagService.findByName(name);
            model.addAttribute("news",user);
            model.addAttribute("colNum",user.size());
            id = 0;
            model.addAttribute("page",id/10+1);
            return "redirect:/tag";
        }
        else {
            /*List<News> list = newsService.getAllUser();
            model.addAttribute("user", list);
            model.addAttribute("colNum",list.size());
            id = 0;
            model.addAttribute("page",id/10+1);*/
            return "redirect:/tag";
        }
    }

    @PostMapping("/pageNext")
    public String pageNext(@RequestParam("pageNum") String pageNum, Model model){
        id = Integer.parseInt(pageNum) - 1;
        id *= 10;

        long colNum = tagService.getColNum();
        model.addAttribute("colNum",colNum);

        List<Tags> list = tagService.page(id, 10);
        model.addAttribute("tags",list);

        model.addAttribute("page",id/10+1);

        return "tag";
    }

    @PostMapping("/pagePrevious")
    public String pagePrevious(@RequestParam("pageNum") String pageNum, Model model){
        id = Integer.parseInt(pageNum) - 1;
        id *= 10;

        long colNum = tagService.getColNum();
        model.addAttribute("colNum",colNum);

        List<Tags> list = tagService.page(id, 10);
        model.addAttribute("tags",list);

        model.addAttribute("page",id/10+1);

        return "tag";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id){
        Optional<Tags> tags = tagService.findByID(id);
        tagService.deleteTags(tags.get());
        tagService.updateDelete(id);

        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "delete to Tag");

        return "redirect:/tags";
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

    @GetMapping("/adm_record")
    public String adminrecord(Model model){

        List<Adm_record> list = adm_recordService.page(0, 10);
        model.addAttribute("adm_record", list);

        long colNum = adm_recordService.getColNum();
        model.addAttribute("colNum", colNum);

        model.addAttribute("page",1);

        return "redirect:/adm_record";
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
