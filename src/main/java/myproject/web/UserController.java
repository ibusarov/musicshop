package myproject.web;

import myproject.model.service.UserService;
import myproject.model.view.UserProfileViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

//    @GetMapping("/profile")
//    public String profile(Model model, @RequestParam("id") String id){
//
//        model.addAttribute("user",this.modelMapper.
//                map(this.userService.findById(id), UserProfileViewModel.class));
//        return "profile";
//    }

}
