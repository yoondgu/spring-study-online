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

import kr.co.hta.exception.OnlineApplicationException;
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

	@GetMapping(path = "/")
	public String home() {
		return "home";
	}
	
	/*
	 * 스프링 MVC에서는 같은 URL이어도 요청방식의 종류에 따라 다르게 매핑시킬 수 있다.
	 * 그래서 보통 로그인-로그인폼 / 회원가입-회원가입폼 과 같은 요청의 URL을 동일하게 하기도 한다.
	 */
	@GetMapping(path = "/register")
	public String registerform(Model model) {
		// 폼입력값을 담을 객체를 미리 생성해서 Model에 저장한다.
		// form 태그에서 아래의 값을 받아야 하기 때문이다.
		UserRegisterForm userRegisterForm = new UserRegisterForm();
		System.out.println("회원정보 등록폼 : " + userRegisterForm.hashCode());
		
		model.addAttribute("userRegisterForm", userRegisterForm);
		
		 return "registerform";
	}
	
	@PostMapping(path = "/register")
	public String register(@Valid UserRegisterForm userRegisterForm, BindingResult errors) throws Exception {
		System.out.println("회원정보 등록: " + userRegisterForm.hashCode());
		if (errors.hasErrors()) {
			// 회원가입폼으로 내부이동한다. form태그에 모델에 담긴 폼입력값이 전달된다.
			return "registerform";
		}

		// userService에서 발생한 예외(유효성 검사 실패)에 대한 처리
		try {
			userService.addNewUser(userRegisterForm);
		} catch (OnlineApplicationException e) {
			// BindingResult 객체에 오류내용을 수동으로 추가하기
			errors.rejectValue("email", null, e.getMessage());
			// 회원가입폼으로 내부이동한다. form태그에 모델에 담긴 폼입력값이 전달된다.
			return "registerform";
		}
		
		return "redirect:/completed";
	}
	
	@GetMapping(path = "/completed")
	public String completed() {
		return "completed";
	}
	
	@GetMapping(path = "/login")
	public String loginform() {
		return "loginform";
	}

	/**
	 * <p>UserService의 login(email, password)를 통해 로그인 사용자정보를 획득한다.
	 * <p>login(email, password) 메소드가 예외를 발생시키면 로그인 페이지를 재요청하는 URL을 응답으로 보낸다.
	 * <p>login(email, password) 메소드가 정상적으로 종료되면, 사용자정보를 Model객체에 "LOGIN_USER"라는 이름으로 저장시킨다.
	 * <p>		LoginController에 정의된 @SessionAttributes("LOGIN_USER")는
	 * <p>		Model 객체에 "LOGIN_USER"라는 이름으로 저장된 객체를 HttpSession 객체의 속성으로 저장시킨다.
	 * <p>		즉 인증이 완료된 사용자정보만 HttpSession 객체에 저장한다.
	 * @param email
	 * @param password
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
		
		try {
			User user = userService.login(email, password);
			model.addAttribute("LOGIN_USER", user);
		} catch (OnlineApplicationException e) {
			return "redirect:login?fail=invalid";
		}
		return "redirect:/";
	}
	
	/**
	 * SessionStatus 객체는 @SessionAttributes 어노테이션으로 HttpSession 객체에 저장시킨 속성을 삭제하는 기능을 제공한다.
	 * @param sessionStatus
	 * @return
	 */
	@GetMapping(path = "/logout")
	public String logout(SessionStatus sessionStatus) {
		// HttpSession객체의 모든 속성을 지우는 것이 아니고, 이 컨트롤러에서 담은 속성만 영향을 받는다.
		sessionStatus.setComplete();
		
		return "redirect:/";
	}
	
//	@PostMapping(path = "/login")
//	public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
//		// @RequestParam은 요청파라미터값을 가져올 때 쓰는 어노테이션으로, 요청파라미터명과 변수명이 같으면 생략해도 된다.
//		
//		try {
//			User user = userService.login(email, password);
//			session.setAttribute("LOGIN_USER", user);
//		} catch (OnlineApplicationException e) {
//			return "redirect:login?fail=invalid";
//		}
//		
//		return "redirect:/";
//	}
//	
//	@GetMapping(path = "/logout")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		
//		return "redirect:/";
//	}
	
}
