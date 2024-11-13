package khuong.com.lab8_javatech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/contact", method = {RequestMethod.GET, RequestMethod.POST})
    public String contact(@RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "email", required = false) String email,
                          Model model,
                          @RequestParam(value = "submitted", required = false) boolean submitted) {
        if (submitted) {
            model.addAttribute("name", name);
            model.addAttribute("email", email);
            return "contact-confirm";
        }
        return "contact";
    }

    @GetMapping("/about")
    public String about() {
        return "About this site";
    }
}
