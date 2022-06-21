package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @GetMapping("/admin")
    public String userList(Model model){
        List<User> allUsers = userService.allUsers();
        model.addAttribute("allUsers", allUsers);
        return "admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam(required = true,defaultValue = "") User user,
                             @RequestParam(required = true, defaultValue = "") String action, Model model){
        if(action.equals("delete")){
            userService.delete(user);
        }
        return "redirect:/admin";
    }


}