package myproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/home")
    public String homeAbsolute(Model model) {
        return home(model);
    }

    @PostMapping("/home")
    public String homePost() {
        return "redirect:/home";
    }

    @GetMapping("/home/contact")
    public String contact(Model model) {

        return "contact";
    }
}
