package khuong.com.ex1_lab8;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(@RequestParam String name, @RequestParam String email, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        return "response";
    }

    @GetMapping("/about")
    @ResponseStatus(HttpStatus.OK)
    public String about() {
        return "about";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @GetMapping("/**")
    public String handleNotFound(Model model) {
        model.addAttribute("message", "Không tìm thấy trang yêu cầu.");
        return "error";
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @PostMapping("/about")
    public String handleMethodNotAllowed(Model model) {
        model.addAttribute("message", "Phương thức không được hỗ trợ cho đường dẫn này.");
        return "error";
    }
}