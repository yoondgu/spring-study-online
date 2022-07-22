package kr.co.hta.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.hta.annotation.LoginUser;
import kr.co.hta.service.CourseService;
import kr.co.hta.service.UserService;
import kr.co.hta.vo.Course;
import kr.co.hta.vo.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/dashboard")
	public String home(@LoginUser User loginUser, Model model) {
		// 최신의 사용자정보를 조회해서 model에 담는다.
		User user = userService.getUserDetail(loginUser.getId());
		model.addAttribute("user", user);
		// 최근 등록한 강의정보를 조회해서 model에 담는다.
		List<Course> courses = courseService.getRecentAddedCourses(user.getId());
		model.addAttribute("courses", courses);
		return "user/home";
	}
	
	@GetMapping("/courses")
	public String courses(@LoginUser User loginUser, Model model) {
		List<Course> courses = courseService.getMyCourses(loginUser.getId());
		model.addAttribute("courses", courses);
		return "user/courses";
	}
	
	@GetMapping("/confirmteacher")
	public String confirmTeacher(@LoginUser User loginUser, Model model) {
		// 로그인 사용자정보를 이용해 사용자정보를 업데이트한다.
		userService.confirmTeacher(loginUser.getId());
		
		// 최신의 사용자 정보를 model에 담아서 뷰에 전달한다.
		User user = userService.getUserDetail(loginUser.getId());
		model.addAttribute("user", user);
		return "redirect:dashboard";
	}

}
