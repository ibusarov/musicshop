package myproject.web;

import myproject.model.binding.UserRegisterBindingModel;
import myproject.model.service.UserService;
import myproject.model.view.UserProfileViewModel;
import myproject.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/profile")
    public String profile(Model model, @RequestParam("id") String id){

        model.addAttribute("user",this.modelMapper.
                map(this.userService.findById(id), UserProfileViewModel.class));
        return "profile";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }



    @GetMapping("/registration")
    public String register(Model model) {

        if (!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }

        return "registration";
    }

    @PostMapping("/registration")
    public String registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel")
                                              UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()||!userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getRepeatPassword())) {
            return "registration";
        }

        if (userService.existsUser(userRegisterBindingModel.getEmail())) {
            bindingResult.rejectValue("email",
                    "error.email",
                    "An account with this email already exists.");
            return "registration";
        }

        userService.createAndLoginUser(userRegisterBindingModel.getEmail(), userRegisterBindingModel.getPassword());

        return "redirect:home";
    }




}
