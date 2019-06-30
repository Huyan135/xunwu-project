package com.huyan.web.controller;

import com.huyan.base.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 胡琰
 * @date 2019/4/2 12:12
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("name","胡琰");
        return "index";
    }

    @GetMapping("/404")
    public String notFoundPage(){
        return "404";
    }
    @GetMapping("/403")
    public String accessError(){
        return "403";
    }
    @GetMapping("/500")
    public String internalError(){
        return "500";
    }
    @GetMapping("/logout/page")
    public String logoutPage(){
        return "logout";
    }
}