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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private Adm_recordService adm_recordService;

    @Autowired
    private User_recordService user_recordService;

    @Autowired
    private TagService tagService;

    private int id = 0;
    public static String loginName;
    public static int loginID;

    @GetMapping
    public String indexGet(Model model){
        long colNum = userService.getColNum();
        model.addAttribute("colNum",colNum);

        List<User> list = userService.page(id, 10);
        model.addAttribute("user",list);

        model.addAttribute("page",id/10+1);
        model.addAttribute("loginName",loginName);

        return "user";
    }

    @PostMapping
    public String index(@RequestParam(value = "username", defaultValue = "null") String name, @RequestParam(value = "password", defaultValue = "null") String password, Model model){
        List<Administrator> adm = userService.login(name, password);

        if(adm.size() == 0){
            return "redirect:/";
        }

        loginID = adm.get(0).getAdmID();

        model.addAttribute("loginName",adm.get(0).getName());
        loginName = adm.get(0).getName();

        long colNum = userService.getColNum();
        model.addAttribute("colNum",colNum);

        List<User> list = userService.page(id, 10);
        model.addAttribute("user",list);

        model.addAttribute("page",id/10+1);

        return "user";
    }

    @PostMapping("/pageNext")
    public String pageNext(@RequestParam("pageNum") String pageNum, Model model){
        id = Integer.parseInt(pageNum) - 1;
        id *= 10;

        long colNum = userService.getColNum();
        model.addAttribute("colNum",colNum);

        List<User> list = userService.page(id, 10);
        model.addAttribute("user",list);

        model.addAttribute("page",id/10+1);

        return "user";
    }

    @PostMapping("/pagePrevious")
    public String pagePrevious(@RequestParam("pageNum") String pageNum, Model model){
        id = Integer.parseInt(pageNum) - 1;
        id *= 10;

        long colNum = userService.getColNum();
        model.addAttribute("colNum",colNum);

        List<User> list = userService.page(id, 10);
        model.addAttribute("user",list);

        model.addAttribute("page",id/10+1);

        return "user";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("password") String password){
        if(name.equals("")){
            return "redirect:/user";
        }

        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "insert to User");

        colNum = userService.getColNum();
        userService.addUser((int)(colNum + 1), name, phone, password);
        return "redirect:/user";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("password") String password){
        userService.updateUser(id, name, phone, password);

        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "update to User");

        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id){
        Optional<User> user = userService.findByID(id);
        userService.deleteUser(user.get());
        userService.updateDelete(id);

        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "delete to User");

        return "redirect:/user";
    }

    @PostMapping("/find")
    public String findUser(@RequestParam(value = "id", defaultValue = "0") int id, @RequestParam(value = "name",defaultValue = "") String name, @RequestParam(value = "phone",defaultValue = "") String phone, Model model){
        if(id > 0){
            List<User> user = userService.queryByID(id);
            model.addAttribute("user",user);
            model.addAttribute("colNum",user.size());
            id = 0;
            model.addAttribute("page",id/10+1);
            return "user";
        }

        if(!name.equals("")){
            List<User> user = userService.findByName(name);
            model.addAttribute("user",user);
            model.addAttribute("colNum",user.size());
            id = 0;
            model.addAttribute("page",id/10+1);
            return "user";
        }
        else if(!phone.equals("")){
            List<User> user = userService.findByPhone(phone);
            model.addAttribute("user",user);
            model.addAttribute("colNum",user.size());
            id = 0;
            model.addAttribute("page",id/10+1);
            return "user";
        }
        else {
            List<User> list = userService.getAllUser();
            model.addAttribute("user", list);
            model.addAttribute("colNum",list.size());
            id = 0;
            model.addAttribute("page",id/10+1);
            return "user";
        }
    }

    /*@PostMapping("/login")
    public String login(@RequestParam(value = "username", defaultValue = "null") String name, @RequestParam(value = "password", defaultValue = "null") String password, Model model){
        List<Administrator> adm = userService.login(name, password);

        if(adm.size() == 0){
            return "redirect:/";
        }

        state = 1;
        model.addAttribute("state",state);
        model.addAttribute("admName",adm.get(0).getName());

        return "user";
    }*/

    /*@GetMapping("/news")
    public String news(Model model){

        List<News> list = newsService.page(0, 10);
        model.addAttribute("news", list);

        long colNum = newsService.getColNum();
        model.addAttribute("colNum", colNum);

        model.addAttribute("page",1);
        model.addAttribute("loginName",loginName);

        return "news";
    }

    @GetMapping("/adminrecord")
    public String adminrecord(Model model){

        List<Adm_record> list = adm_recordService.page(0, 10);
        model.addAttribute("adm_record", list);

        long colNum = adm_recordService.getColNum();
        model.addAttribute("colNum", colNum);

        model.addAttribute("page",1);
        model.addAttribute("loginName",loginName);

        return "adminrecord";
    }

    @GetMapping("/tags")
    public String tags(Model model){
        List<Tags> list = tagService.page(0, 10);
        model.addAttribute("tags", list);

        long colNum = tagService.getColNum();
        model.addAttribute("colNum", colNum);

        model.addAttribute("page",1);
        model.addAttribute("loginName",loginName);

        return "tag";
    }

    @GetMapping("/userrecord")
    public String index(Model model){
        long colNum = user_recordService.getColNum();
        model.addAttribute("colNum",colNum);

        List<User_record> list = user_recordService.page(0, 10);
        model.addAttribute("user_record", list);
        model.addAttribute("page",1);
        model.addAttribute("loginName",loginName);

        return "userrecord";
    }*/
}
