package kr.co.hta.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.hta.annotation.LoginUser;
import kr.co.hta.exception.OnlineApplicationException;
import kr.co.hta.service.CourseService;
import kr.co.hta.service.UserService;
import kr.co.hta.vo.User;
import kr.co.hta.web.form.UserRegisterForm;

@Controller
/*
 * @SessionAttributes
 * 이 컨트롤러에서 Model 객체에 저장되는 객체 중에서 지정된 속성명으로 저장되는 것만 HttpSession 객체에 저장시킨다.
 */
@SessionAttributes("LOGIN_USER")
public class HomeController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
	
	@GetMapping(path = "/")
	public String home() {
		return "home";
	}
	
	@GetMapping(path = "/courses")
	public String courses(@RequestParam(name = "cat", required = false) String categoryId, Model model) {
		model.addAttribute("categories", courseService.getAllCategories());
		model.addAttribute("tags", courseService.getAllTags());
		model.addAttribute("courses", courseService.getCoursesByCategoryId(categoryId));
		return "course/home";
	}
	
	@GetMapping(path = "/login")
	public String loginform() {
		return "loginform";
	}
	
	@PostMapping(path = "/login")
	public String login(String email, String password, Model model) {
		try {
			User user = userService.loginUser(email, password);
			model.addAttribute("LOGIN_USER", user);
		} catch (OnlineApplicationException e) {
			return "redirect:/login?fail=invalid";
		}
		return "redirect:/";
	}
	
	@GetMapping(path = "/logout")
	public String logout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/";
	}
	
	@GetMapping(path = "/register")
	public String registerform(Model model) {
		UserRegisterForm userRegisterForm = new UserRegisterForm();
		model.addAttribute("userRegisterForm", userRegisterForm);
		return "registerform";
	}
	
	@PostMapping(path = "/register")
	public String register(@Valid UserRegisterForm userRegisterForm, BindingResult errors, Model model) {
		if (errors.hasErrors()) {
			return "registerform";
		}
		
		try {
			userService.addNewUser(userRegisterForm);
		} catch (OnlineApplicationException e) {
			errors.rejectValue("email", null, e.getMessage());
			return "registerform";
		}
		
		// 회원가입이 완료되면 자동 로그인 시키기
		User user = userService.loginUser(userRegisterForm.getEmail(), userRegisterForm.getPassword());
		model.addAttribute("LOGIN_USER", user);
		return "redirect:/completed";
	}
	
	@GetMapping(path = "/completed")
	public String completed(@LoginUser User loginUser) {
		return "completed";
	}
}
