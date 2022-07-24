package kr.co.hta.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.hta.annotation.LoginUser;
import kr.co.hta.exception.OnlineApplicationException;
import kr.co.hta.service.CourseService;
import kr.co.hta.service.UserService;
import kr.co.hta.vo.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/dashboard")
	public String dashboard(@LoginUser User loginUser, Model model) {
		model.addAttribute("user", userService.getUserById(loginUser.getId()));
		model.addAttribute("courses", courseService.getMyCourses(loginUser.getId()));
		return "/user/home";
	}

	@GetMapping("/confirmteacher")
	public String confirmTeacher(@LoginUser User loginUser, Model model) {
		try {
			userService.confirmTeacher(loginUser.getId());
		} catch (OnlineApplicationException e) {
			return "redirect:/user/dashboard?fail=invalid";
		}
		return "redirect:/user/dashboard";
	}
	
}
